package andrei.teplyh.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.event.ContextRefreshedEvent;

public interface KafkaService {
    public void receive(ContextRefreshedEvent event) throws JsonProcessingException;
}
