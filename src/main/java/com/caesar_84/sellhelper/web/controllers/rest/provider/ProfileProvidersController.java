package com.caesar_84.sellhelper.web.controllers.rest.provider;

import com.caesar_84.sellhelper.LoggedUser;
import com.caesar_84.sellhelper.domain.GoodsProvider;
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
@RequestMapping(value = ProfileProvidersController.REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileProvidersController extends AbstractCommonCrudController<GoodsProvider> {
    static final String REST_URL = AbstractCommonCrudController.BASE_URL + "/providers";

    @Autowired
    public ProfileProvidersController(CommonCrudService<GoodsProvider> commonCrudService) {
        super(commonCrudService);
    }

    @GetMapping("/{id}")
    public GoodsProvider get(@PathVariable("id") int id) {
        return super.get(id, LoggedUser.id());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GoodsProvider> post(@RequestBody GoodsProvider entity) {
        GoodsProvider saved = super.post(entity, LoggedUser.id());

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(uri).body(saved);
    }

    @GetMapping
    public List<GoodsProvider> getAll() {
        return super.getAll(LoggedUser.id());
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id) {
        return super.delete(id, LoggedUser.id());
    }
}
