package com.caesar_84.sellhelper.web.controllers.rest.user;

import com.caesar_84.sellhelper.repository.UserRepository;
import com.caesar_84.sellhelper.web.controllers.AbstractCommonCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = AdminUserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUserController {
    static final String REST_URL = AbstractCommonCrudController.BASE_URL + "/admin";

    @Autowired
    private UserRepository repository;


}
