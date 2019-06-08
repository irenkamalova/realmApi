package com.kamalova.realm.service;

import com.kamalova.realm.dto.Realm;
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

    public boolean createRealm(String name, String description) {
        try {
            String secretKey = Double.valueOf(Math.random()).toString();
            Realm realm = new Realm(name,
                    description,
                    secretKey);
            realmRepository.save(realm);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}