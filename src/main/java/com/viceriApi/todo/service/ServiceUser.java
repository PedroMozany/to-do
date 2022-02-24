package com.viceriApi.todo.service;

import com.viceriApi.todo.entities.User;
import com.viceriApi.todo.repository.RepositoryUser;
import com.viceriApi.todo.settings.SettingsToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class ServiceUser implements  UserDetailsService{

    private final RepositoryUser repositoryUser;

    public ServiceUser(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = repositoryUser.findByEmail(email);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User [" + email + "] not found");
        }
        return new SettingsToken(user);
    }
}

