package andrei.teplyh.util;

import andrei.teplyh.dto.MailDto;
import andrei.teplyh.mappers.MailMapper;
import andrei.teplyh.services.JmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import java.util.List;

@Component
public class MailScheduler {
    private final Cache cache;
    private final JmsService jmsService;
    private final MailMapper mailMapper;

    @Autowired
    public MailScheduler(Cache cache, JmsService jmsService, MailMapper mailMapper) {
        this.cache = cache;
        this.jmsService = jmsService;
        this.mailMapper = mailMapper;
    }

    @Scheduled(cron = "*/60 * * * * *")
    public void sendEmail() {
        List<MailDto> admins = mailMapper.toDto(cache);
        for(MailDto mailDto : admins) {
            try {
                jmsService.send(mailDto);
            } catch (JsonProcessingException | JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
