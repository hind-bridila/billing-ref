package ma.atos.billing.ref.billing_ref.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("rejectCustomerDelegate")
public class RejectCustomerDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        execution.setVariable("rejectionReason",
                "Adresse invalide — 'Maroc' est requis dans l'adresse");

        System.out.println("Demande rejetée : adresse ne contient pas 'Maroc'");
    }
}
