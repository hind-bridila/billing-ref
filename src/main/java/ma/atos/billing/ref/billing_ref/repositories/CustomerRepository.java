package ma.atos.billing.ref.billing_ref.repositories;

import ma.atos.billing.ref.billing_ref.models.Customer;
import ma.atos.billing.ref.billing_ref.enums.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Recherche par nom
    List<Customer> findByNom(String nom);

    // Recherche par type de paiement
    List<Customer> findByPaymentType(PaymentType paymentType);
}
