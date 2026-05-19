package ma.atos.billing.ref.billing_ref.messaging;

public record CustomerCreatedEvent(
        Long customerId,
        String nom,
        String prenom,
        String adresse,
        String paymentType
) {}
