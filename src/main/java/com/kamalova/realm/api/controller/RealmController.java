package com.kamalova.realm.api.controller;

import com.kamalova.realm.api.model.ApiError;
import com.kamalova.realm.api.model.RealmRequest;
import com.kamalova.realm.dao.Realm;
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
@RequestMapping(name = "realm", path = "/service/user")
@Api(value = "Realm Management System")
public class RealmController {

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
    public ResponseEntity<Object> createRealm(@RequestBody RealmRequest realmRequest) {
        if (StringUtils.isEmpty(realmRequest.getName())) {
            return ResponseEntity.badRequest().body(new ApiError("InvalidRealmName"));
        }
        Realm realm = realmService.createRealm(realmRequest.getName(), realmRequest.getDescription());
        if (realm != null) {
            return ResponseEntity.status(201).body(realm);
        } else {
            return ResponseEntity.badRequest().body(new ApiError("DuplicateRealmName"));
        }
    }

    /*
    GET method - get realm
     */
    @ApiOperation(value = "View realm by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 400, message = "Invalid argument"),
            @ApiResponse(code = 404, message = "Bad Request")
    })
    @GetMapping(value = "/realm/{id}",
            produces = {"application/json", "application/xml"})
    public ResponseEntity<Object> getRealmById(@PathVariable(value = "id") String realmId) {
        Long id;
        try {
            id = Long.valueOf(realmId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.badRequest()
                    .body(new ApiError("InvalidArgument"));
        }

        Optional<Realm> realm = realmService.getRealm(id);
        if (realm.isPresent()) {
            return ResponseEntity.ok().header("OK").body((realm.get()));
        } else {
            return ResponseEntity.status(404)
                    .body(new ApiError("RealmNotFound"));
        }
    }
}