    package org.natzi.maskedlady.entity;

    import jakarta.persistence.*;
    import lombok.Data;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Entity
    @Getter @Setter
    @NoArgsConstructor
    @Table(name = "one_time_token_login")
    public class OneTimeTokenLogin extends TokenConfirmationTemplate {
        private String ott;
    }

