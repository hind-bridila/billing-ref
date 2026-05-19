package ma.atos.billing.ref.billing_ref.delegates;

import ma.atos.billing.ref.billing_ref.dto.CustomerDTO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("validateAddressDelegate")
public class ValidateAddressDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        CustomerDTO dto = (CustomerDTO) execution.getVariable("customerDTO");

        boolean isValid = dto.getAdresse() != null
                && dto.getAdresse().toLowerCase().contains("maroc");

        execution.setVariable("addressValid", isValid);

        System.out.println("Adresse validée : " + isValid);
    }
}