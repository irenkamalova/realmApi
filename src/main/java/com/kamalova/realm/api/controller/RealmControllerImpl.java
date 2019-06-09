package com.kamalova.realm.api.controller;

import com.kamalova.realm.api.model.ApiError;
import com.kamalova.realm.api.model.RealmRequest;
import com.kamalova.realm.dao.model.Realm;
import com.kamalova.realm.service.RealmService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(name = "realm", path = "/service/user")
@Api(value = "Realm Management System")
public class RealmControllerImpl implements RealmController {

    @Autowired
    private RealmService realmService;

    public ResponseEntity<Object> create(RealmRequest realmRequest) {
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

    public ResponseEntity<Object> get(String realmId) {
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