package org.natzi.maskedlady.service.auth.tasks;

import jakarta.transaction.Transactional;
import org.natzi.maskedlady.repository.EmailConfirmationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class ScheduledLogTasksRepoImpl implements ScheduledLogTasksRepo {

    private final EmailConfirmationRepository emailConfirmationRepo;
    private final Logger lg = Logger.getLogger(getClass().getName());

    public ScheduledLogTasksRepoImpl(EmailConfirmationRepository emailConfirmationRepo) {
        this.emailConfirmationRepo = emailConfirmationRepo;
    }

    @Override
    @Transactional
    public void removeUUIDRecord() {
        emailConfirmationRepo.deleteByExpiresAtBefore(LocalDateTime.now());
    }
}
