package org.natzi.maskedlady.repository;

import org.natzi.maskedlady.entity.OttTimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OttTimeTableRepository extends JpaRepository<OttTimeTable, Integer> {

    Optional<OttTimeTable> findByOtt(String ott);
    Optional<OttTimeTable> findByEmail(String email);
}
