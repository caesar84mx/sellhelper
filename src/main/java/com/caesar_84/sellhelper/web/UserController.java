/*
package com.caesar_84.sellhelper.web;

import com.caesar_84.sellhelper.domain.User;
import com.caesar_84.sellhelper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest-api/v1")
public class UserController {
    @Autowired
    UserRepository repository;

    @RequestMapping(value = "/users", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> post(@RequestBody User user) {
        return new ResponseEntity<>(repository.save(user), HttpStatus.OK);
    }
}
*/
