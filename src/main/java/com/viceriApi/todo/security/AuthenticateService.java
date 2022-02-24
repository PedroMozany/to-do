package com.viceriApi.todo.security;

import com.viceriApi.todo.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthenticateService implements UserDetailsService {

        @Autowired
        private RepositoryUser repositoryUser;

        @Override
        public UserDetails loadUserByUsername(String email) {

           com.viceriApi.todo.entities.User user = repositoryUser.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário inexistente"));
            String[] roles = new String[]{"ADMIN"};
            return User
                    .builder()
                    .authorities(new SimpleGrantedAuthority("ADMIN"))
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .disabled(false)
                    .roles(roles)
                    .build();

        }


}
