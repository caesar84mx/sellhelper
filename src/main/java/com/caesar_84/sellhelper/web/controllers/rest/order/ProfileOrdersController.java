package com.caesar_84.sellhelper.web.controllers.rest.order;

import com.caesar_84.sellhelper.LoggedUser;
import com.caesar_84.sellhelper.domain.Order;
import com.caesar_84.sellhelper.domain.auxclasses.OrderStatus;
import com.caesar_84.sellhelper.service.CommonCrudService;
import com.caesar_84.sellhelper.service.order.OrderService;
import com.caesar_84.sellhelper.web.controllers.AbstractCommonCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = ProfileOrdersController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileOrdersController extends AbstractCommonCrudController<Order> {
    static final String REST_URL = "/rest-api/v1/profile/orders";

    @Autowired
    private OrderService service;

    @Autowired
    public ProfileOrdersController(CommonCrudService<Order> commonCrudService) {
        super(commonCrudService);
    }

    @GetMapping("/{id}")
    public Order get(@PathVariable("id") int id) {
        return super.get(id, LoggedUser.id());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> post(@RequestBody Order entity) {
        Order saved = super.post(entity, LoggedUser.id());

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(uri).body(saved);
    }

    @GetMapping
    public List<Order> getAll() {
        return super.getAll(LoggedUser.id());
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id) {
        return super.delete(id, LoggedUser.id());
    }

    @GetMapping("/filtered/by-status")
    public List<Order> getByStatus(@RequestParam(value = "status") OrderStatus status) {
        return service.getByStatus(status, LoggedUser.id());
    }

    @GetMapping("/filtered/by-client/{id}")
    public List<Order> getByClient(@PathVariable("id") int clientId) {
        return service.getByClient(clientId, LoggedUser.id());
    }

    @GetMapping("/filtered/by-date-and-time")
    public List<Order> getBetween(@RequestParam(value = "startDate", required = false) LocalDate startDate,
                                  @RequestParam(value = "startTime", required = false) LocalTime startTime,
                                  @RequestParam(value = "endDate", required = false) LocalDate endDate,
                                  @RequestParam(value = "endTime", required = false) LocalTime endTime) {
        LocalDateTime start = LocalDateTime.of(startDate, startTime);
        LocalDateTime end = LocalDateTime.of(endDate, endTime);

        return service.getBetween(start, end, LoggedUser.id());
    }
}