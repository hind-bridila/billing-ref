package ma.atos.billing.ref.billing_ref.dtos;

import ma.atos.billing.ref.billing_ref.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDTO {

    private Long id;
    private String nom;
    private String prenom;
    private String firstName;
    private String lastName;
    private String email;
    private String adresse;
    private String phoneNumber;
    private PaymentType paymentType;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}