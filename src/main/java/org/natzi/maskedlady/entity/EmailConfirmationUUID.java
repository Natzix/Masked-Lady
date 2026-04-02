package org.natzi.maskedlady.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "email_confirmation_uuid")
public class EmailConfirmationUUID extends TokenConfirmationTemplate {

    private String codConfirmation;
}
