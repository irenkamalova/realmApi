package com.kamalova.realm.service;

import com.kamalova.realm.dao.RealmRepository;
import com.kamalova.realm.dao.model.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RealmServiceImpl implements RealmService {

    @Autowired
    private RealmRepository realmRepository;

    public Optional<Realm> getRealm(Long id) {
        return realmRepository.findById(id);
    }

    /*
    It should be better to handle exceptions from data base,
    and make a error-code, depends on error of working with DB:
    is it duplicated name or problem with DB
    As far as in task have only one error - duplicated name - I don't do this
    But better to say that there possible "500 Internal server error" for example,
    if we have a problem with DB.
     */
    public Realm createRealm(String name, String description) {
        try {
            Realm realm = new Realm(name,
                    description,
                    "secretKey");
            return realmRepository.save(realm);
        } catch (Exception e) {
            return null;
        }
    }
}
