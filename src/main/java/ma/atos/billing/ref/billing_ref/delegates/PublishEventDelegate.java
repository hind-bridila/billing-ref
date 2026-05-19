package ma.atos.billing.ref.billing_ref.delegates;

import ma.atos.billing.ref.billing_ref.dto.CustomerDTO;
import ma.atos.billing.ref.billing_ref.messaging.CustomerCreatedEvent;
import ma.atos.billing.ref.billing_ref.messaging.CustomerEventPublisher;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("publishEventDelegate")
@RequiredArgsConstructor
public class PublishEventDelegate implements JavaDelegate {

    private final CustomerEventPublisher eventPublisher;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        CustomerDTO saved = (CustomerDTO) execution.getVariable("savedCustomer");

        CustomerCreatedEvent event = new CustomerCreatedEvent(
                saved.getId(),
                saved.getNom(),
                saved.getPrenom(),
                saved.getAdresse(),
                saved.getPaymentType().toString()
        );

        eventPublisher.publishCustomerCreated(event);

        System.out.println("Event publié pour customer ID : " + saved.getId());
    }
}