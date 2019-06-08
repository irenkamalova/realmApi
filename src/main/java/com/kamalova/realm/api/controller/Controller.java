package com.kamalova.realm.api.controller;

import com.kamalova.realm.api.model.ShortRealItem;
import com.kamalova.realm.dto.Realm;
import com.kamalova.realm.service.RealmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/service/user")
@Api(value = "Realm Management System")
public class Controller {

    @Autowired
    private RealmService realmService;

    /*
    POST method - create realm
     */
    @ApiOperation(value = "View realm by id", response = Realm.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Invalid argument"),
            @ApiResponse(code = 404, message = "If the requested realm id does not identify an existing realm.")
    })
    @PostMapping(value = "/realm",
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public ResponseEntity<String> createRealm(@RequestBody ShortRealItem shortRealItem) {
        if (StringUtils.isEmpty(shortRealItem.getName())) {
            ResponseEntity.badRequest().body("InvalidRealmName");
        }
        if (realmService.createRealm(shortRealItem.getName(), shortRealItem.getDescription())) {
            return ResponseEntity.status(201).body("Created");
        } else {
            return ResponseEntity.badRequest().body("DuplicateRealmName");
        }
    }

    /*
    GET method - get realm
     */
    @ApiOperation(value = "View realm by id", response = Realm.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 400, message = "Invalid argument"),
            @ApiResponse(code = 404, message = "Bad Request")
    })
    @GetMapping(value = "/realm/{id}",
            produces = {"application/json", "application/xml"})
    public ResponseEntity<Realm> getRealmById(@PathVariable(value = "id") Long realmId) {
        Optional<Realm> realm = realmService.getRealm(realmId);
        if (realm.isPresent()) {
            return ResponseEntity.ok().body(realm.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}