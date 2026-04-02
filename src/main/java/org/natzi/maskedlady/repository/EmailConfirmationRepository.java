package org.natzi.maskedlady.repository;

import org.natzi.maskedlady.entity.EmailConfirmationUUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmailConfirmationRepository extends JpaRepository<EmailConfirmationUUID, Integer> {

    Optional<EmailConfirmationUUID> findByCodConfirmation(String codConfirmation);
}
