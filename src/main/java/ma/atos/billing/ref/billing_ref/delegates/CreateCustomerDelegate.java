package ma.atos.billing.ref.billing_ref.delegates;

import ma.atos.billing.ref.billing_ref.dto.CustomerDTO;
import ma.atos.billing.ref.billing_ref.mappers.CustomerMapper;
import ma.atos.billing.ref.billing_ref.models.Customer;
import ma.atos.billing.ref.billing_ref.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("createCustomerDelegate")
@RequiredArgsConstructor
public class CreateCustomerDelegate implements JavaDelegate {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        CustomerDTO dto = (CustomerDTO) execution.getVariable("customerDTO");

        Customer customer = customerMapper.toEntity(dto);
        Customer saved = customerRepository.save(customer);

        execution.setVariable("savedCustomer", customerMapper.toDto(saved));

        System.out.println("Customer créé avec ID : " + saved.getId());
    }
}
