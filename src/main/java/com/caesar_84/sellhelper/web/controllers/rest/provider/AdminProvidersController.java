package com.caesar_84.sellhelper.web.controllers.rest.provider;

import com.caesar_84.sellhelper.domain.GoodsProvider;
import com.caesar_84.sellhelper.service.CommonCrudService;
import com.caesar_84.sellhelper.web.controllers.AbstractCommonCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = AdminProvidersController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminProvidersController extends AbstractCommonCrudController<GoodsProvider> {
    static final String REST_URL = AbstractCommonCrudController.BASE_URL + "/admin/{user}/providers";

    @Autowired
    public AdminProvidersController(CommonCrudService<GoodsProvider> commonCrudService) {
        super(commonCrudService);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public GoodsProvider get(@RequestParam(value = "id") int id,
                             @PathVariable("user") int userId) {
        return super.get(id, userId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public List<GoodsProvider> getAll(@PathVariable("user") int userId) {
        return super.getAll(userId);
    }
}
