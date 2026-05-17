package ma.atos.billing.ref.billing_ref.service;

import ma.atos.billing.ref.billing_ref.models.Customer;
import ma.atos.billing.ref.billing_ref.repositories.CustomerRepository;
import ma.atos.billing.ref.billing_ref.enums.PaymentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ma.atos.billing.ref.billing_ref.services.CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer createCustomer(Customer customer) {
        if (customer.getNom() == null || customer.getNom().isEmpty()) {
            throw new IllegalArgumentException("Le nom du customer est obligatoire");
        }
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("L'ID doit être positif");
        }
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Long id, Customer customerDetails) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("L'ID doit être positif");
        }

        Optional<Customer> existingCustomer = customerRepository.findById(id);

        if (existingCustomer.isEmpty()) {
            throw new IllegalArgumentException("Customer avec l'ID " + id + " non trouvé");
        }

        Customer customer = existingCustomer.get();


        if (customerDetails.getNom() != null && !customerDetails.getNom().isEmpty()) {
            customer.setNom(customerDetails.getNom());
        }
        if (customerDetails.getPrenom() != null && !customerDetails.getPrenom().isEmpty()) {
            customer.setPrenom(customerDetails.getPrenom());
        }
        if (customerDetails.getAdresse() != null && !customerDetails.getAdresse().isEmpty()) {
            customer.setAdresse(customerDetails.getAdresse());
        }
        if (customerDetails.getPaymentType() != null) {
            customer.setPaymentType(customerDetails.getPaymentType());
        }


        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("L'ID doit être positif");
        }
        if (!customerRepository.existsById(id)) {
            throw new IllegalArgumentException("Customer avec l'ID " + id + " non trouvé");
        }
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> findByNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Le nom ne doit pas être vide");
        }
        return customerRepository.findByNom(nom);
    }

    @Override
    public List<Customer> findByPaymentType(PaymentType paymentType) {
        if (paymentType == null) {
            throw new IllegalArgumentException("Le type de paiement ne doit pas être vide");
        }
        return customerRepository.findByPaymentType(paymentType);
    }
}