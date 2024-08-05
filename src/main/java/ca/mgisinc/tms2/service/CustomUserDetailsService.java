package ca.mgisinc.tms2.service;


import ca.mgisinc.tms2.model.User;
import ca.mgisinc.tms2.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@EnableJpaRepositories
public class CustomUserDetailsService implements UserDetailsService {

    final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info(String.format("Loading user by username: %s" ,username));

        User user = userRepository.findByUsername(username);

        if (user == null) {
            UsernameNotFoundException e = new UsernameNotFoundException("User not found");

            log.error(e.getMessage(), e);

            throw e;
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
