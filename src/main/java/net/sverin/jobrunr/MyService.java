package net.sverin.jobrunr;

import jakarta.annotation.PostConstruct;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static org.jobrunr.scheduling.RecurringJobBuilder.aRecurringJob;

@Service
public class MyService {

    private static Logger log = LoggerFactory.getLogger(MyService.class);

    private final JobScheduler jobScheduler;

    public MyService(JobScheduler jobScheduler) {
        this.jobScheduler = jobScheduler;
    }

    @PostConstruct
    public void init() {
        jobScheduler.createRecurrently(aRecurringJob()
                .withId("my-job-id")
                .withName("Recurring Job test")
                .withCron(Cron.every5minutes())
                .withDetails(() -> log.info("Running recurring job."))
        );
    }

}
