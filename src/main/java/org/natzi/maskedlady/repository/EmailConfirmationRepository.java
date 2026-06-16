package org.natzi.maskedlady.repository;

import org.natzi.maskedlady.entity.EmailConfirmationUUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface EmailConfirmationRepository extends JpaRepository<EmailConfirmationUUID, Integer> {
    void deleteByExpiresAtBefore(LocalDateTime date);
    Optional<EmailConfirmationUUID> findByCodConfirmation(String codConfirmation);
}
