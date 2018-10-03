package demo.run.run.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private RunService runService;

    @Scheduled(cron = "0 1 1 * * ?" , zone="Europe/Amsterdam")
    public void updateOverdue(){
        this.runService.updateOverdue();
    }
}
