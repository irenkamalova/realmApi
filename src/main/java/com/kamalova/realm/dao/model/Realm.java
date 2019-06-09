package com.kamalova.realm.dao.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;

@Data
@NoArgsConstructor
@Entity
@Table(name = "realm")
@ApiModel(description = "realm")
@JacksonXmlRootElement(localName = "realm")
public class Realm {

    /**
     * Unique ID. Primary key. System-generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JacksonXmlProperty(isAttribute = true)
    @XmlAttribute
    private Long id;
    /**
     * Realm name (alias for ID). Must be unique.
     */
    @JacksonXmlProperty(isAttribute = true)
    @XmlAttribute
    @Column(unique = true)
    private String name;
    /**
     * Realm description. Up to 255 chars.
     */
    @JacksonXmlProperty
    @Column
    private String description;
    /**
     * Realm encryption key. Fixed length 32 chars.
     */
    @JacksonXmlProperty
    @Column
    private String key;

    public Realm(String name,
                 String description,
                 String key) {
        this.name = name;
        this.description = description;
        this.key = key;
    }
}
