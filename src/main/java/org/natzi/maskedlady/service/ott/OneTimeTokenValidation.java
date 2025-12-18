package org.natzi.maskedlady.service.ott;

import org.natzi.maskedlady.entity.OttTimeTable;
import org.natzi.maskedlady.repository.OttTimeTableRepository;
import org.springframework.stereotype.Service;

@Service
public class OneTimeTokenValidation implements TokenManagerStrategy<OttTimeTable> {

    private final OttTimeTableRepository repository;

    public OneTimeTokenValidation(OttTimeTableRepository repository) {
        this.repository = repository;
    }

    @Override
    public void consume(OttTimeTable ott) {
        verifyLifeOtt(ott.getExpiresAt(), ott.isUsed());
        ott.setUsed(true);
        repository.save(ott);
    }
}
