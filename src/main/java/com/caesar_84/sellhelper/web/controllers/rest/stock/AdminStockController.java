package com.caesar_84.sellhelper.web.controllers.rest.stock;

import com.caesar_84.sellhelper.domain.StockItem;
import com.caesar_84.sellhelper.service.CommonCrudService;
import com.caesar_84.sellhelper.web.controllers.AbstractCommonCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = AdminStockController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminStockController extends AbstractCommonCrudController<StockItem> {
    static final String REST_URL = AbstractCommonCrudController.BASE_URL + "/admin/{user}/stock";

    @Autowired
    public AdminStockController(CommonCrudService<StockItem> commonCrudService) {
        super(commonCrudService);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public StockItem get(@RequestParam(value = "id") int id, @PathVariable("user") int userId) {
        return super.get(id, userId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public List<StockItem> getAll(@PathVariable("user") int userId) {
        return super.getAll(userId);
    }
}
