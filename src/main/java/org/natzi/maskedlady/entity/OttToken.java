package org.natzi.maskedlady.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@MappedSuperclass
public class OttToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String ott;
    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;
    private boolean used;
}
