package ma.atos.billing.ref.billing_ref.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super("Customer introuvable avec l'id : " + id);
    }
}