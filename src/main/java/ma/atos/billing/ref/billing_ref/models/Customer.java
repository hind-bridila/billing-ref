package ma.atos.billing.ref.billing_ref.models;
import jakarta.persistence.*;
import lombok.*;
import ma.atos.billing.ref.billing_ref.enums.PaymentType;

import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "customer", schema = "customer")
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
