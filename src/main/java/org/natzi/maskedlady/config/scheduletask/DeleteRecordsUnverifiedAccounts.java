package org.natzi.maskedlady.config.scheduletask;

import org.natzi.maskedlady.service.auth.tasks.ScheduledLogTasksRepoImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class DeleteRecordsUnverifiedAccounts {

    private final ScheduledLogTasksRepoImpl tasksRepo;
    private final Logger lg = Logger.getLogger(getClass().getName());

    public DeleteRecordsUnverifiedAccounts(ScheduledLogTasksRepoImpl tasksRepo) {
        this.tasksRepo = tasksRepo;
    }

    @Scheduled(cron = "0 0 0 * * SUN", zone = "America/Lima")
    public void removeUUIRecords() {
        tasksRepo.removeUUIDRecord();
    }
}