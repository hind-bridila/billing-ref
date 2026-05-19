package ma.atos.billing.ref.billing_ref.controllers;

import lombok.RequiredArgsConstructor;
import ma.atos.billing.ref.billing_ref.dto.CustomerDTO;
import ma.atos.billing.ref.billing_ref.enums.PaymentType;
import ma.atos.billing.ref.billing_ref.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAll() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<List<CustomerDTO>> getByNom(@PathVariable String nom) {
        return ResponseEntity.ok(customerService.getCustomersByNom(nom));
    }

    @GetMapping("/payment-type/{type}")
    public ResponseEntity<List<CustomerDTO>> getByPaymentType(@PathVariable PaymentType type) {
        return ResponseEntity.ok(customerService.getCustomersByPaymentType(type));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.createCustomer(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> update(@PathVariable Long id,
                                              @RequestBody CustomerDTO dto) {
        return ResponseEntity.ok(customerService.updateCustomer(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}