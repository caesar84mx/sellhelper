package com.caesar_84.sellhelper.web.controllers.rest.order;


import com.caesar_84.sellhelper.LoggedUser;
import com.caesar_84.sellhelper.domain.Order;
import com.caesar_84.sellhelper.domain.auxclasses.OrderStatus;
import com.caesar_84.sellhelper.service.CommonCrudService;
import com.caesar_84.sellhelper.service.order.OrderService;
import com.caesar_84.sellhelper.web.controllers.AbstractCommonCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = AdminOrdersController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminOrdersController extends AbstractCommonCrudController<Order> {
    static final String REST_URL = AbstractCommonCrudController.BASE_URL + "/admin/{user}/orders";

    @Autowired
    private OrderService service;

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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/filtered/by-status")
    public List<Order> getByStatus(@RequestParam(value = "status") OrderStatus status,
                                   @PathVariable("user") int userId) {
        return service.getByStatus(status, userId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/filtered/by-client/{id}")
    public List<Order> getByClient(@PathVariable("id") int clientId,
                                   @PathVariable("user") int userId) {
        return service.getByClient(clientId, userId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/filtered/by-date-and-time")
    public List<Order> getBetween(@RequestParam(value = "startDate", required = false) LocalDate startDate,
                                  @RequestParam(value = "startTime", required = false) LocalTime startTime,
                                  @RequestParam(value = "endDate", required = false) LocalDate endDate,
                                  @RequestParam(value = "endTime", required = false) LocalTime endTime,
                                  @PathVariable("user") int userId) {
        LocalDateTime start = LocalDateTime.of(startDate, startTime);
        LocalDateTime end = LocalDateTime.of(endDate, endTime);

        return service.getBetween(start, end, userId);
    }
}