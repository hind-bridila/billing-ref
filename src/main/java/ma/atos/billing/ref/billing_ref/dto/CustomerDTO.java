package ma.atos.billing.ref.billing_ref.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.atos.billing.ref.billing_ref.enums.PaymentType;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO implements Serializable {
    private Long id;          // on garde l'id pour identifier

    private String prenom;

    private String nom;

    private String adresse;

    private PaymentType paymentType;
}
