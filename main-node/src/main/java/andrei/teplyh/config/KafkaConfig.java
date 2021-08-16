package andrei.teplyh.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaConfig {
    private final KafkaProperty kafkaProperty;

    @Autowired
    public KafkaConfig(KafkaProperty kafkaProperty) {
        this.kafkaProperty = kafkaProperty;
    }

    @Bean
    public Producer<String, String> kafkaProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", kafkaProperty.getServer());
        props.put("acks", "all");
        props.put("linger.ms", 1);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return new KafkaProducer<>(props);
    }
}
