package ma.atos.billing.ref.billing_ref.services;

import lombok.RequiredArgsConstructor;
import ma.atos.billing.ref.billing_ref.dto.CustomerDTO;
import ma.atos.billing.ref.billing_ref.enums.PaymentType;
import ma.atos.billing.ref.billing_ref.mappers.CustomerMapper;
import ma.atos.billing.ref.billing_ref.messaging.CustomerEventPublisher;
import ma.atos.billing.ref.billing_ref.models.Customer;
import ma.atos.billing.ref.billing_ref.repositories.CustomerRepository;
import ma.atos.billing.ref.billing_ref.exceptions.CustomerNotFoundException;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final CustomerEventPublisher customerEventPublisher;
    private final RuntimeService runtimeService;
    private final HistoryService historyService; // ← AJOUTÉ

    public List<CustomerDTO> getAllCustomers() {
        return customerMapper.toDtoList(customerRepository.findAll());
    }

    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDto)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    public List<CustomerDTO> getCustomersByNom(String nom) {
        return customerMapper.toDtoList(customerRepository.findByNom(nom));
    }

    public List<CustomerDTO> getCustomersByPaymentType(PaymentType paymentType) {
        return customerMapper.toDtoList(
                customerRepository.findByPaymentType(paymentType));
    }

    public CustomerDTO createCustomer(CustomerDTO dto) {

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerDTO", dto);

        ProcessInstance instance = runtimeService.startProcessInstanceByKey(
                "customer-creation-process", variables
        );

        // Lecture depuis l'historique — le process est déjà terminé
        Boolean addressValid = (Boolean) historyService
                .createHistoricVariableInstanceQuery()
                .processInstanceId(instance.getId())
                .variableName("addressValid")
                .singleResult()
                .getValue();

        if (addressValid == null || !addressValid) {
            throw new RuntimeException(
                    "Adresse invalide — 'Maroc' est requis dans l'adresse"
            );
        }

        CustomerDTO saved = (CustomerDTO) historyService
                .createHistoricVariableInstanceQuery()
                .processInstanceId(instance.getId())
                .variableName("savedCustomer")
                .singleResult()
                .getValue();

        return saved;
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO dto) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        existing.setNom(dto.getNom());
        existing.setPrenom(dto.getPrenom());
        existing.setAdresse(dto.getAdresse());
        existing.setPaymentType(dto.getPaymentType());

        Customer saved = customerRepository.save(existing);
        return customerMapper.toDto(saved);
    }

    public void deleteCustomer(Long id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        customerRepository.deleteById(id);
    }
}