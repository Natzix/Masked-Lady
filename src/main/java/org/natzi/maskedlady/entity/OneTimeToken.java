    package org.natzi.maskedlady.entity;

    import jakarta.persistence.*;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Data
    @Entity
    @NoArgsConstructor
    @Table(name = "one_time_tokens")
    public class OneTimeToken extends OttToken {
    }
