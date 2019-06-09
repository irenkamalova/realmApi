package com.kamalova.realm.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "realm")
@ApiModel(description = "Realm")
public class Realm {

    /**
     * Unique ID. Primary key. System-generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Realm name (alias for ID). Must be unique.
     */
    // @ Unique ?
    private String name;
    /**
     * Realm description. Up to 255 chars.
     */
    private String description;
    /**
     * Realm encryption key. Fixed length 32 chars.
     */
    private String key;

    public Realm(String name,
                 String description,
                 String key) {
        this.name = name;
        this.description = description;
        this.key = key;
    }
}
