package com.parrino.riccardo.inventory.config;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parrino.riccardo.dto.InventoryRequest;

@Configuration
public class KafkaConfig {
    
    @Bean
    public JsonSerde<InventoryRequest> entityJsonSerde(
            ObjectMapper objectMapper,
            KafkaProperties kafkaProperties) {

        JsonSerde<InventoryRequest> serde = new JsonSerde<>(InventoryRequest.class, objectMapper);
        serde.deserializer().configure(kafkaProperties.buildConsumerProperties(), false);
        serde.serializer().configure(kafkaProperties.buildProducerProperties(), false);
        return serde;
    }

}
