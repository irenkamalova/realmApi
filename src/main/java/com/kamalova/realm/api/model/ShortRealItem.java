package com.kamalova.realm.api.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "Realm")
public class ShortRealItem {
    @JacksonXmlProperty
    String name;
    @JacksonXmlProperty
    String description;
}
