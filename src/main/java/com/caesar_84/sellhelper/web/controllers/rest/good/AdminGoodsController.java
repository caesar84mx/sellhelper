package com.caesar_84.sellhelper.web.controllers.rest.good;

import com.caesar_84.sellhelper.domain.Good;
import com.caesar_84.sellhelper.service.CommonCrudService;
import com.caesar_84.sellhelper.web.controllers.AbstractCommonCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = AdminGoodsController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminGoodsController extends AbstractCommonCrudController<Good> {
    static final String REST_URL = AbstractCommonCrudController.BASE_URL + "/admin/{user}/goods";

    @Autowired
    public AdminGoodsController(CommonCrudService<Good> commonCrudService) {
        super(commonCrudService);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public Good get(@RequestParam(value = "id") int id, @PathVariable("user") int userId) {
        return super.get(id, userId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public List<Good> getAll(@PathVariable("user") int userId) {
        return super.getAll(userId);
    }
}
