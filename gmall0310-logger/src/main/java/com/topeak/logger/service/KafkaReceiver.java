package com.topeak.logger.service;

import com.toppeak.gmall0310.common.constant.GmallConstans;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KafkaReceiver {

    @KafkaListener(topics = {GmallConstans.KAFKA_TOPIC_EVENT})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            System.out.println("record =" + record);
            System.out.println("message =" + message);

        }
    }

}
