package andrei.teplyh.services.impl;

import andrei.teplyh.config.KafkaProperty;
import andrei.teplyh.services.KafkaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class KafkaServiceImpl implements KafkaService {
    private final Producer<String, String> producer;
    private final ObjectMapper objectMapper;
    private final KafkaProperty property;

    @Autowired
    public KafkaServiceImpl(
            Producer<String, String> producer,
            ObjectMapper objectMapper,
            KafkaProperty property
    ) {
        this.producer = producer;
        this.objectMapper = objectMapper;
        this.property = property;
    }

    @Override
    public void send(Object o) throws JsonProcessingException {
        String msg = objectMapper.writeValueAsString(o);
        try {
            ProducerRecord<String, String> record = new ProducerRecord<>(property.getTopic(), msg);
            RecordMetadata metadata = producer.send(record).get();
//            System.out.printf("value = %s\n", record.value());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
