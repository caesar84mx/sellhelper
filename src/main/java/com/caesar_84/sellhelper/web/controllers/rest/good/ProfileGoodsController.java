package com.caesar_84.sellhelper.web.controllers.rest.good;

import com.caesar_84.sellhelper.LoggedUser;
import com.caesar_84.sellhelper.domain.Good;
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
@RequestMapping(value = ProfileGoodsController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileGoodsController extends AbstractCommonCrudController<Good> {
    static final String REST_URL = "/rest-api/v1/profile/goods";

    @Autowired
    public ProfileGoodsController(CommonCrudService<Good> commonCrudService) {
        super(commonCrudService);
    }

    @GetMapping("/{id}")
    public Good get(@PathVariable("id") int id) {
        return super.get(id, LoggedUser.id());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Good> post(@RequestBody Good entity) {
        Good saved = super.post(entity, LoggedUser.id());

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(uri).body(saved);
    }

    @GetMapping
    public List<Good> getAll() {
        return super.getAll(LoggedUser.id());
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id) {
        return super.delete(id, LoggedUser.id());
    }
}
