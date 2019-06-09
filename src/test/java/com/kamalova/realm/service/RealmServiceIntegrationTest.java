package com.kamalova.realm.service;

import com.kamalova.realm.dao.RealmRepository;
import com.kamalova.realm.dao.model.Realm;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
public class RealmServiceIntegrationTest {

    @MockBean
    RealmRepository realmRepository;
    @Autowired
    private RealmService service;

    @Test
    public void givenRealm_whenGetRealm_thenReturnRealm()
            throws Exception {

        Realm realm = new Realm(
                "testName",
                "testDescription",
                "testKey");
        realm.setId(1L);

        given(realmRepository.findById(1L)).willReturn(Optional.of(realm));

        Optional<Realm> byId = service.getRealm(1L);

        Assert.assertTrue(byId.isPresent());
        Assert.assertEquals(byId.get().getName(),
                realm.getName());
        Assert.assertEquals(byId.get().getDescription(),
                realm.getDescription());
        Assert.assertEquals(byId.get().getKey(),
                realm.getKey());

    }

    @Test
    public void givenRealm_whenGreateRealm_thenReturnRealm()
            throws Exception {

        Realm realm = new Realm(
                "testName",
                "testDescription",
                "secretKey");

        given(realmRepository.save(realm)).willReturn(realm);

        Realm realmResult = service.createRealm(realm.getName(), realm.getDescription());

        Assert.assertEquals(realmResult.getName(),
                realm.getName());
        Assert.assertEquals(realmResult.getDescription(),
                realm.getDescription());
        Assert.assertEquals(realmResult.getKey(),
                realm.getKey());


    }

    @TestConfiguration
    static class RealmServiceTestConfiguration {

        @Bean
        public RealmService realmService() {
            return new RealmServiceImpl();
        }
    }
}