package com.kamalova.realm.repository;

import com.kamalova.realm.dao.RealmRepository;
import com.kamalova.realm.dao.model.Realm;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RealmRepositoryIntegrationTest {

    @Autowired
    RealmRepository realmRepository;

    @Test
    public void whenSave_thenFindById() {
        Realm realm = new Realm(
                "testName",
                "testDescription",
                "testKey");

        Realm savedRealm = realmRepository.save(realm);
        Optional<Realm> byId = realmRepository.findById(savedRealm.getId());

        Assert.assertTrue(byId.isPresent());
        Assert.assertEquals(byId.get().getName(),
                realm.getName());
        Assert.assertEquals(byId.get().getDescription(),
                realm.getDescription());
        Assert.assertEquals(byId.get().getKey(),
                realm.getKey());

    }

}