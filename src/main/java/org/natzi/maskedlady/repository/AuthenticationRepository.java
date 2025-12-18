package org.natzi.maskedlady.repository;

import org.natzi.maskedlady.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String account);
    Optional<Account> findByEmail(String email);
}