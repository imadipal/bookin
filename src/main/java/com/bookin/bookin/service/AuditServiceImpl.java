package com.bookin.bookin.service;
import com.bookin.bookin.audit.AuditDao;
import com.bookin.bookin.util.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService{


    @Autowired
    AuditDao auditAdd;

    //ObjectMapper mapper = new ObjectMapper();
    @KafkaListener(topics="topic", groupId="myGroup")
    public void consume(String message) throws JsonProcessingException {
        auditAdd.addAudit(JsonParser.parser(message));
    }

}