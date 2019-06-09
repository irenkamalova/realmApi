package com.kamalova.realm.api.controller;

import com.kamalova.realm.api.model.RealmRequest;
import com.kamalova.realm.dao.model.Realm;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface RealmController {
    @ApiOperation(value = "View realm by id", response = Realm.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Invalid argument"),
            @ApiResponse(code = 404, message = "If the requested realm id does not identify an existing realm.")
    })
    @PostMapping(value = "/realm",
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    ResponseEntity<Object> create(@RequestBody RealmRequest realmRequest);

    @ApiOperation(value = "View realm by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 400, message = "Invalid argument"),
            @ApiResponse(code = 404, message = "Bad Request")
    })
    @GetMapping(value = "/realm/{id}",
            produces = {"application/json", "application/xml"})
    ResponseEntity<Object> get(@PathVariable(value = "id") String realmId);

}
