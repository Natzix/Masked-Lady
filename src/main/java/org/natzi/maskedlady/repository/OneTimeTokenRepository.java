package org.natzi.maskedlady.repository;

import org.natzi.maskedlady.entity.OneTimeTokenLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface OneTimeTokenRepository extends JpaRepository<OneTimeTokenLogin, Long> {

    Optional<OneTimeTokenLogin> findByOtt(String ott);
    void deleteByExpiresAtBefore(LocalDateTime dateTime);
}
