package com.caesar_84.sellhelper.web.controllers.rest.user;

import com.caesar_84.sellhelper.domain.User;
import com.caesar_84.sellhelper.repository.UserRepository;
import com.caesar_84.sellhelper.web.controllers.AbstractCommonCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = AdminUserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUserController {
    static final String REST_URL = AbstractCommonCrudController.BASE_URL + "/admin";

    @Autowired
    private UserRepository repository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/{user}")
    public User get(@PathVariable("user") int id) {
        return repository.findOne(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<User> getAll() {
        return repository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{user}")
    public ResponseEntity enable(@PathVariable("user") int userId,
                                 @RequestParam(value = "enable") boolean enable) {

        return repository.setEnabled(userId, enable) != 0 ?
                ResponseEntity.status(HttpStatus.OK).header("User disabled").build() :
                ResponseEntity.status(HttpStatus.NOT_MODIFIED).header("Failed disabling user").build();
    }
}
