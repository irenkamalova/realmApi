package com.kamalova.realm.service;

import com.kamalova.realm.dao.model.Realm;

import java.util.Optional;

public interface RealmService {

    Optional<Realm> getRealm(Long id);

    Realm createRealm(String name, String description);
}
