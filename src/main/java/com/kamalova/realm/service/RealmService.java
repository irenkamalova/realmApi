package com.kamalova.realm.service;

import com.kamalova.realm.dao.Realm;
import com.kamalova.realm.repository.RealmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RealmService {

    @Autowired
    private RealmRepository realmRepository;

    public Optional<Realm> getRealm(Long id) {
        return realmRepository.findById(id);
    }

    public Realm createRealm(String name, String description) {
        try {
            Realm realm = new Realm(name,
                    description,
                    "secretKey");
            return realmRepository.save(realm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
