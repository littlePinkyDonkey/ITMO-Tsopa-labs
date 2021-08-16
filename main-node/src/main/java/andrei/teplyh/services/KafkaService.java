package andrei.teplyh.services;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface KafkaService {
    void send(Object o) throws JsonProcessingException;
}
