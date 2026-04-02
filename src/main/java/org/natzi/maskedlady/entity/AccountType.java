package org.natzi.maskedlady.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor @AllArgsConstructor
@Table(name = "account_type")
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;

    @OneToMany(mappedBy = "accountType") // mappedBy => bidireccional
    private Set<Account> accounts;

    public AccountType(int id, String type) {
        this.id = id;
        this.type = type;
    }
}
