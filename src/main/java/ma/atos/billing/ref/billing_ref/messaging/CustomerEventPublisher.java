package ma.atos.billing.ref.billing_ref.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.atos.billing.ref.billing_ref.config.RabbitMQConfig;
import ma.atos.billing.ref.billing_ref.dto.CustomerDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishCustomerCreated(CustomerCreatedEvent event) {


        log.info("Publishing CustomerCreatedEvent customerId={} nom={}",
                event.customerId(), event.nom());


        rabbitTemplate.convertAndSend(
                RabbitMQConfig.PAYMENT_INITIATION_QUEUE,
                event
        );
    }
}
