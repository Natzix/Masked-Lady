package org.natzi.maskedlady.repository;

import org.natzi.maskedlady.entity.OneTimeToken;
import org.natzi.maskedlady.entity.OttTimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface OneTimeTokenRepository extends JpaRepository<OneTimeToken, Long> {

    Optional<OneTimeToken> findByOtt(String ott);
    void deleteByExpiresAtBefore(LocalDateTime dateTime);
}
