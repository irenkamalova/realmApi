package com.kamalova.realm.dao;

import com.kamalova.realm.dao.model.Realm;
import org.springframework.data.repository.CrudRepository;

public interface RealmRepository extends CrudRepository<Realm, Long> {
}