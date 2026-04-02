package org.natzi.maskedlady.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@Table(name = "account")
public class Account {

    public Account(String username, String email, boolean isActive, AccountType accountType) {
        this.username = username;
        this.email = email;
        this.isActive = isActive;
        this.accountType = accountType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private AccountType accountType;

    public Account(String username) {
        this.username = username;
    }

    public Account(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
