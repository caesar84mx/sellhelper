package com.caesar_84.sellhelper.web.controllers.rest.stock;

import com.caesar_84.sellhelper.LoggedUser;
import com.caesar_84.sellhelper.domain.StockItem;
import com.caesar_84.sellhelper.service.CommonCrudService;
import com.caesar_84.sellhelper.web.controllers.AbstractCommonCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = ProfileStockController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileStockController extends AbstractCommonCrudController<StockItem> {
    static final String REST_URL = "/rest-api/v1/profile/stockItems";

    @Autowired
    public ProfileStockController(CommonCrudService<StockItem> commonCrudService) {
        super(commonCrudService);
    }

    @GetMapping("/{id}")
    public StockItem get(@PathVariable("id") int id) {
        return super.get(id, LoggedUser.id());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockItem> post(@RequestBody StockItem entity) {
        StockItem saved = super.post(entity, LoggedUser.id());

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(uri).body(saved);
    }

    @GetMapping
    public List<StockItem> getAll() {
        return super.getAll(LoggedUser.id());
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id) {
        return super.delete(id, LoggedUser.id());
    }
}
