package ca.mgisinc.tms2.config;

import ca.mgisinc.tms2.service.CustomUserDetailsService;
import ca.mgisinc.tms2.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.Collection;
import java.util.List;


@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final Logger log = LoggerFactory.getLogger(JwtRequestFilter.class);

        final String authorizationHeader = request.getHeader("Authorization");
        final String jSessionId = request.getSession().getId();

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Basic ") && jSessionId != null) {

            String basicAuthorization = authorizationHeader.substring(6);
            String decodeAuthorization = new String(Base64.getDecoder().decode(basicAuthorization));

            UserDetails userDetails = new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return List.of();
                }

                @Override
                public String getPassword() {
                    return decodeAuthorization.split(":")[1];
                }

                @Override
                public String getUsername() {
                    return decodeAuthorization.split(":")[0];
                }
            };


            String jwtToken = jwtUtil.generateToken(userDetails, jSessionId);
            response.addHeader("JWT", jwtToken);

            log.info(String.format("Genereate JWT Token: %s based on sessionID: %s", jwtToken, jSessionId));

            Cookie cookie = new Cookie("JWT", jwtToken);
            response.addCookie(cookie);

        }

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt, jSessionId);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
