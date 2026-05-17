package ma.atos.billing.ref.billing_ref.services;

import ma.atos.billing.ref.billing_ref.models.Customer;
import ma.atos.billing.ref.billing_ref.enums.PaymentType;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer createCustomer(Customer customer);
    Optional<Customer> getCustomerById(Long id);
    List<Customer> getAllCustomers();
    Customer updateCustomer(Long id, Customer customer);


    void deleteCustomer(Long id);

    List<Customer> findByNom(String nom);
    List<Customer> findByPaymentType(PaymentType paymentType);
}