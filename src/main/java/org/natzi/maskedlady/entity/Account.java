package org.natzi.maskedlady.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role roleId;

    public Account(String username) {
        this.username = username;
    }

    public Account(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
