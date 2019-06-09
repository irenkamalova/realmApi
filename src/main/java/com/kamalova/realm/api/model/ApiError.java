package com.kamalova.realm.api.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "error")
public class ApiError {

    private String code;

    public ApiError(String code) {
        this.code = code;
    }
}
