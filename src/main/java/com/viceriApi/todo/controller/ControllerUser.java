package com.viceriApi.todo.controller;

import com.viceriApi.todo.entities.User;
import com.viceriApi.todo.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ControllerUser {

    @Autowired
    private RepositoryUser repositoryUser;


    @GetMapping
    public List<User> list() {
        return repositoryUser.findAll();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return repositoryUser.save(user);

    }

    @GetMapping(path = "{id}")
    public ResponseEntity<User> findById(@PathVariable long id) {
        return repositoryUser.findById(id)
                .map(User -> ResponseEntity.ok().body(User))
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping(path = "{id}")
    public ResponseEntity<User> delete(@PathVariable long id) {
        return repositoryUser.findById(id).map(User -> {
            repositoryUser.deleteById(id);
            return ResponseEntity.ok().body(User);
        }).orElse(ResponseEntity.notFound().build());
    }

}
