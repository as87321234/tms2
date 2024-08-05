package ca.mgisinc.tms2.config.config;


import ca.mgisinc.tms2.config.JwtRequestFilter;
import ca.mgisinc.tms2.model.Privilege;
import ca.mgisinc.tms2.model.Role;
import ca.mgisinc.tms2.model.User;
import ca.mgisinc.tms2.repository.PrivilegeRepository;
import ca.mgisinc.tms2.repository.RoleRepository;
import ca.mgisinc.tms2.repository.UserRepository;
import ca.mgisinc.tms2.service.CustomUserDetailsService;
import ca.mgisinc.tms2.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.*;

@Configuration
@ComponentScan

public class SecurityConfig {

    final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PrivilegeRepository privilegeRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    public User createUsers() {

        // Load Privileges
        Privilege p = new Privilege("CREATE");
        privilegeRepository.save(p);
        privilegeRepository.flush();

        p = new Privilege("READ");
        privilegeRepository.save(p);
        privilegeRepository.flush();

        p = new Privilege("UPDATE");
        privilegeRepository.save(p);
        privilegeRepository.flush();

        p = new Privilege("DELETE");
        privilegeRepository.save(p);
        privilegeRepository.flush();


        // Load Roles
        Role r = new Role("ADMIN");
        r.setPrivileges(new ArrayList<Privilege>(Arrays.asList(privilegeRepository.findByName("CREATE"), privilegeRepository.findByName("READ"), privilegeRepository.findByName("UPDATE"), privilegeRepository.findByName("DELETE"))));
        roleRepository.saveAndFlush(r);

        r = new Role("OPERATOR");
        r.setPrivileges(new ArrayList<Privilege>(Arrays.asList(privilegeRepository.findByName("CREATE"), privilegeRepository.findByName("READ"), privilegeRepository.findByName("UPDATE"))));
        roleRepository.save(r);

        r = new Role("USER");
        r.setPrivileges(new ArrayList<Privilege>(Arrays.asList(privilegeRepository.findByName("READ"))));
        roleRepository.save(r);

        // Load Users
        User u = new User();
        u.setUsername("admin");
        u.setPassword(this.passwordEncoder().encode("Password1"));
        u.setEmail("admin@admin.com");
        u.setFirstName("admin");
        u.setLastName("admin");
        u.setEnabled(true);
        u.setRoles(new ArrayList<Role>(Arrays.asList(roleRepository.findByName("ADMIN"))));

        u = userRepository.saveAndFlush(u);

        u = new User();
        u.setUsername("stlouisa");
        u.setPassword(this.passwordEncoder().encode("Password1"));
        u.setEmail("as87321234@gmail.com");
        u.setFirstName("André");
        u.setLastName("St-Louis");
        u.setEnabled(true);
        u.setRoles(new ArrayList<Role>(Arrays.asList(roleRepository.findByName("OPERATOR"))));
        u = userRepository.save(u);

        u = new User();
        u.setUsername("begins");
        u.setPassword(this.passwordEncoder().encode("Password1"));
        u.setEmail("sb87321234@gmail.com");
        u.setFirstName("Sonia");
        u.setLastName("Bégin");
        u.setEnabled(true);
        u.setRoles(new ArrayList<Role>(Arrays.asList(roleRepository.findByName("USER"))));
        u = userRepository.save(u);

        return null;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        log.info("Set SecurityFilterChain");
        httpSecurity
                .headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .authorizeHttpRequests(x -> x
                        .requestMatchers("/", "/index", "/public/**", "/h2-console/**", "/static/**", "/api/login").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated()
                )
        ;

        httpSecurity.addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public JwtRequestFilter jwtRequestFilter() {

        log.info("Set JwtRequestFilter");
        return new JwtRequestFilter(jwtUtil, customUserDetailsService);

    }

}
