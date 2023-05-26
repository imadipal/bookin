package com.bookin.bookin.util;


import com.bookin.bookin.audit.AuditEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class JsonParser {

    static ObjectMapper mapper = new ObjectMapper();

    public String stringify(AuditEntity audit) throws JsonProcessingException {

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(audit);

    }
    public static AuditEntity parser(String jsonString) throws JsonProcessingException {

        return mapper.readValue(jsonString, AuditEntity.class);

    }
}