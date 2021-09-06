package andrei.teplyh.service;

import andrei.teplyh.config.KafkaProperty;
import andrei.teplyh.dto.MailDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;

@Service
public class KafkaServiceImpl implements KafkaService {

    @Value("${mail.subject}")
    private String subject;

    @Value("${mail.message}")
    private String message;

    private final Consumer<String, String> consumer;
    private final ObjectMapper objectMapper;
    private final KafkaProperty kafkaProperty;
    private final MailService mailService;

    @Autowired
    public KafkaServiceImpl(
            Consumer<String, String> consumer,
            ObjectMapper objectMapper,
            KafkaProperty kafkaProperty,
            MailService mailService
    ) {
        this.consumer = consumer;
        this.objectMapper = objectMapper;
        this.kafkaProperty = kafkaProperty;
        this.mailService = mailService;
    }

    @Override
    @EventListener
    public void receive(ContextRefreshedEvent event) throws JsonProcessingException {
        consumer.subscribe(Collections.singleton(kafkaProperty.getTopic()));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                MailDto mailDto;
                try {
                    mailDto = objectMapper.readValue(record.value(), MailDto.class);
                    mailService.send(mailDto.getEmail(), subject, String.format(message, mailDto.getMessage()));
                    System.out.println(mailDto);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
