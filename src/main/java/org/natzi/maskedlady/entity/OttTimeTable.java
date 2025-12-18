package org.natzi.maskedlady.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "temp_email_confirmation")
public class OttTimeTable extends OttToken {

    private String email;
}
