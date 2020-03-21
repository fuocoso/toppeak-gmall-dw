package com.topeak.logger;

import com.topeak.logger.service.KafkaReceiver;
import com.topeak.logger.service.KafkaSender;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LoggerCollectApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =SpringApplication.run(LoggerCollectApplication.class, args);

		KafkaReceiver getter = context.getBean(KafkaReceiver.class);

	}

}
