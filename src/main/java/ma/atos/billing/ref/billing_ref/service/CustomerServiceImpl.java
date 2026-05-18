package ma.atos.billing.ref.billing_ref.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import ma.atos.billing.ref.billing_ref.models.Customer;
import ma.atos.billing.ref.billing_ref.repositories.CustomerRepository;
import ma.atos.billing.ref.billing_ref.enums.PaymentType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * CREATE - Efface le cache
     */
    @CacheEvict(value = "customers", allEntries = true)
    @Override
    public Customer createCustomer(Customer customer) {
        if (customer == null || customer.getNom() == null || customer.getNom().isBlank()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        if (customer.getEmail() == null || customer.getEmail().isBlank()) {
            throw new IllegalArgumentException("L'email est obligatoire");
        }
        return customerRepository.save(customer);
    }

    /**
     * READ BY ID - Utilise le cache
     * @Cacheable = Si en cache, retourne du cache. Sinon, appelle la méthode.
     */
    @Cacheable(value = "customers", key = "#id")
    @Override
    public Optional<Customer> getCustomerById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("L'ID doit être positif");
        }
        return customerRepository.findById(id);
    }

    /**
     * READ ALL - Utilise le cache
     */
    @Cacheable(value = "customers", key = "'all'")
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * UPDATE - Efface le cache
     * @CacheEvict = Supprime les entrées du cache
     */
    @CacheEvict(value = "customers", allEntries = true)
    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("L'ID doit être positif");
        }
        if (customer == null || customer.getNom() == null || customer.getNom().isBlank()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }

        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            Customer existing = existingCustomer.get();
            existing.setNom(customer.getNom());
            existing.setPrenom(customer.getPrenom());
            existing.setFirstName(customer.getFirstName());
            existing.setLastName(customer.getLastName());
            existing.setEmail(customer.getEmail());
            existing.setAdresse(customer.getAdresse());
            existing.setPhoneNumber(customer.getPhoneNumber());
            existing.setPaymentType(customer.getPaymentType());
            return customerRepository.save(existing);
        } else {
            throw new IllegalArgumentException("Customer avec l'ID " + id + " n'existe pas");
        }
    }

    /**
     * DELETE - Efface le cache
     */
    @CacheEvict(value = "customers", allEntries = true)
    @Override
    public void deleteCustomer(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("L'ID doit être positif");
        }
        if (!customerRepository.existsById(id)) {
            throw new IllegalArgumentException("Customer avec l'ID " + id + " n'existe pas");
        }
        customerRepository.deleteById(id);
    }

    /**
     * SEARCH BY NOM - Utilise le cache
     */
    @Cacheable(value = "customers", key = "'nom_' + #nom")
    @Override
    public List<Customer> findByNom(String nom) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        return customerRepository.findByNom(nom);
    }

    /**
     * SEARCH BY PAYMENT TYPE - Utilise le cache
     */
    @Cacheable(value = "customers", key = "'paymentType_' + #paymentType")
    @Override
    public List<Customer> findByPaymentType(PaymentType paymentType) {
        if (paymentType == null) {
            throw new IllegalArgumentException("Le type de paiement est obligatoire");
        }
        return customerRepository.findByPaymentType(paymentType);
    }
}