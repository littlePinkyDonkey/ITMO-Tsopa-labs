package andrei.teplyh.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MailScheduler {
    private final Cache cache;

    @Autowired
    public MailScheduler(Cache cache) {
        this.cache = cache;
    }

    @Scheduled(cron = "*/60 * * * * *")
    public void schedule() {

    }
}
