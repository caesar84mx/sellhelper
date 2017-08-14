package com.caesar_84.sellhelper.web.controllers.rest.order;


import com.caesar_84.sellhelper.domain.Order;
import com.caesar_84.sellhelper.service.CommonCrudService;
import com.caesar_84.sellhelper.web.controllers.AbstractCommonCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = AdminOrdersController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminOrdersController extends AbstractCommonCrudController<Order> {
    static final String REST_URL = AbstractCommonCrudController.BASE_URL + "/admin/{user}/orders";

    @Autowired
    public AdminOrdersController(CommonCrudService<Order> commonCrudService) {
        super(commonCrudService);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public Order get(@RequestParam(value = "id") int id,
                     @PathVariable("user") int userId) {
        return super.get(id, userId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<Order> getAll(@PathVariable("user") int userId) {
        return super.getAll(userId);
    }
}
