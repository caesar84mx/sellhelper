package com.caesar_84.sellhelper.web.controllers.rest.user;

import com.caesar_84.sellhelper.LoggedUser;
import com.caesar_84.sellhelper.domain.User;
import com.caesar_84.sellhelper.repository.UserRepository;
import com.caesar_84.sellhelper.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ProfileUserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileUserController {
    @Autowired
    private UserRepository repository;

    static final String REST_URL = "/rest-api/v1/profile/self";

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> post(@RequestBody User user, @AuthenticationPrincipal LoggedUser loggedUser) {
        CheckUtil.checkUserIdConsistent(user.getId(), loggedUser.getId());

        return ResponseEntity.accepted().body(repository.save(user));
    }

    @GetMapping
    public User get(@AuthenticationPrincipal LoggedUser loggedUser) {
        return repository.findOne(loggedUser.getId());
    }

    @DeleteMapping
    public void delete(@AuthenticationPrincipal LoggedUser loggedUser) {
        repository.delete(loggedUser.getId());
    }
}