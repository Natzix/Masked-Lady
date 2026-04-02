package org.natzi.maskedlady.service.ott;

import org.natzi.maskedlady.entity.EmailConfirmationUUID;
import org.natzi.maskedlady.repository.EmailConfirmationRepository;
import org.springframework.stereotype.Service;

@Service
public class OneTimeTokenValidation implements TokenManagerStrategy<EmailConfirmationUUID> {

    private final EmailConfirmationRepository repository;

    public OneTimeTokenValidation(EmailConfirmationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void consume(EmailConfirmationUUID ott) {
        verifyLifeOtt(ott.getExpiresAt(), ott.isUsed());
        ott.setUsed(true);
        repository.save(ott);
    }
}
