package com.example.kafkaconsumer.consumer;

import com.example.kafkaconsumer.dto.MessageDTO2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @Value(value = "${spring.kafka.consumer.group-id}")
    private String groupID;

    @Value(value = "${topic.name}")
    private String topic;

    private static final Logger log = LoggerFactory.getLogger(MessageConsumer.class);

    @KafkaListener(topics = "${topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenTopicLogs(ConsumerRecord<String, MessageDTO2> record){
        log.info("Received message partition: "+record.partition());
        log.info("Received message: "+record.value());
        Object valor = record.value();
        MessageDTO2 mensagem = (MessageDTO2) valor;
//        log.info("Received message: "+((MessageDTO) record.value()).getMessage());
        log.info("SEGUNDA MENSAGEM NESSE CARALHO: "+mensagem.getSecondMessage());
    }

}
