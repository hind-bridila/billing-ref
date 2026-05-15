package ma.atos.billing.ref.billing_ref.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.atos.billing.ref.billing_ref.enums.PaymentType;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

    public class Customer extends BusinessObject {

        @Column(name = "PRENOM")
        private String prenom;

        @Column(name = "NOM")
        private String nom;

        @Column(name = "ADRESSE")
        private String adresse;

        @Enumerated(EnumType.STRING)
        @Column(name = "PAYMENT_TYPE")
        private PaymentType paymentType;


}
