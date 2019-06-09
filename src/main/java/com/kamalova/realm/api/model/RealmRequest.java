package com.kamalova.realm.api.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@JacksonXmlRootElement(localName = "realm")
@XmlRootElement
public class RealmRequest {
    @JacksonXmlProperty(isAttribute = true)
    @XmlAttribute
    String name;
    @JacksonXmlProperty
    String description;
}
