package com.example.kafkaconsumer.config;


import com.example.kafkaconsumer.dto.MessageDTO2;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {


    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.consumer.group-id}")
    private String groupID;


    @Bean
    public ConsumerFactory<String, MessageDTO2> messageConsumerFactory(){
//
//        JsonDeserializer<MessageDTO> deserializer = new JsonDeserializer<MessageDTO>(MessageDTO.class);
//        deserializer.setRemoveTypeHeaders(false);
//        deserializer.addTrustedPackages("com.example.producerappliction.dtos.*");
//        deserializer.setUseTypeMapperForKey(true);

        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupID);
//        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
////        configProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
//        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//        configProps.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JsonDeserializer.class);
//        configProps.put(JsonDeserializer.KEY_DEFAULT_TYPE, "java.lang.String");
//        configProps.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
//        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.example.kafkaconsumer.dto.MessageDTO2");
//        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
//        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

//        configProps.put("serializer.class", "kafka.serializer.DefaultEncoder");
//        configProps.put("sasl.mechanism", "PLAIN");
//        configProps.put("security.protocol", "SASL_SSL");
//        configProps.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"$ConnectionString\" password=\"Endpoint=sb://dis-fbo-fkaso.servicebus.windows.net/;SharedAccessKeyName=log-send;SharedAccessKey=YX+vzVqExH7GFFz5l0hV0+vpCPb7RnhbVco/7R9gil4=;EntityPath=logs\";");

        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), new JsonDeserializer<>(MessageDTO2.class, false));
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessageDTO2> messageDTOConcurrentKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, MessageDTO2> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(messageConsumerFactory());
        return factory;
    }


}
