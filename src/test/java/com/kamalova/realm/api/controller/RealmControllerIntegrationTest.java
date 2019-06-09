package com.kamalova.realm.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamalova.realm.api.model.RealmRequest;
import com.kamalova.realm.dao.model.Realm;
import com.kamalova.realm.service.RealmService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RealmController.class)
public class RealmControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RealmService service;

    @Test
    public void givenRealm_whenGetRealm_thenReturnRealm()
            throws Exception {

        Realm realm = new Realm(
                "testName",
                "testDescription",
                "testKey");

        given(service.getRealm(1L)).willReturn(Optional.of(realm));

        mvc.perform(get("/service/user/realm/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(realm.getName())))
                .andExpect(jsonPath("description", is(realm.getDescription())))
                .andExpect(jsonPath("key", is(realm.getKey())));
    }

    @Test
    public void givenNoRealm_whenGetRealm_thenReturnError()
            throws Exception {

        mvc.perform(get("/service/user/realm/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("code", is("RealmNotFound")));
    }

    @Test
    public void whenGetRealmIncorrectId_thenReturnError()
            throws Exception {

        mvc.perform(get("/service/user/realm/y")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("code", is("InvalidArgument")));
    }

    @Test
    public void givenRealm_whenPostRealm_thenCreated()
            throws Exception {

        Realm realm = new Realm(
                "testName",
                "testDescription",
                "testKey");

        given(service.createRealm(realm.getName(), realm.getDescription())).willReturn(realm);
        realm.setId(1L);

        RealmRequest realmRequest = new RealmRequest();
        realmRequest.setName(realm.getName());
        realmRequest.setDescription(realm.getDescription());

        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(realmRequest);
        mvc.perform(post("/service/user/realm")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name", is(realm.getName())))
                .andExpect(jsonPath("description", is(realm.getDescription())))
                .andExpect(jsonPath("key", is(realm.getKey())));
    }

    @Test
    public void givenRealmWithouName_whenPostRealm_thenError()
            throws Exception {


        RealmRequest realmRequest = new RealmRequest();

        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(realmRequest);
        mvc.perform(post("/service/user/realm")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("code", is("InvalidRealmName")));
    }

    /*
    Integration test with data base
     */
    @Test
    public void givenTwoEqualsName_whenPostRealm_thenError()
            throws Exception {

        Realm realm = new Realm(
                "testName",
                "testDescription",
                "testKey");
        given(service.createRealm(realm.getName(), realm.getDescription())).willReturn(null);

        RealmRequest realmRequest = new RealmRequest();
        realmRequest.setName(realm.getName());
        realmRequest.setDescription(realm.getDescription());

        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(realmRequest);
        mvc.perform(post("/service/user/realm")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("code", is("DuplicateRealmName")));
    }

}