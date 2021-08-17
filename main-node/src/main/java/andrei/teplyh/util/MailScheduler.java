package andrei.teplyh.util;

import andrei.teplyh.dto.MailDto;
import andrei.teplyh.mappers.MailMapper;
import andrei.teplyh.services.KafkaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MailScheduler {
    private final Cache cache;
    private final KafkaService kafkaService;
    private final MailMapper mailMapper;

    @Autowired
    public MailScheduler(Cache cache, KafkaService kafkaService, MailMapper mailMapper) {
        this.cache = cache;
        this.kafkaService = kafkaService;
        this.mailMapper = mailMapper;
    }

    @Scheduled(cron = "*/60 * * * * *")
    public void schedule() {
        List<MailDto> admins = mailMapper.toDto(cache);
        for(MailDto mailDto : admins) {
            try {
                kafkaService.send(mailDto);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}
