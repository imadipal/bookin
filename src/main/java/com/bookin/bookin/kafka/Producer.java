package com.bookin.bookin.kafka;

import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    //private static final org.slf4j.Logger LOGGER =  LoggerFactory.getLogger(Producer.class);
    private static KafkaTemplate<String, String> kafkaTemplate;

    public Producer(KafkaTemplate<String, String> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public static void sendMessage(String message) {
        //LOGGER.info(String.format("message sent %s",message));
        kafkaTemplate.send("topic", message);
    }
}
