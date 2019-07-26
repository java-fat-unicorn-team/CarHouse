package com.carhouse.rest;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

    private ObjectMapper objectMapper;

    public JsonConverter() {
        this.objectMapper = new ObjectMapper();
    }

    public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
