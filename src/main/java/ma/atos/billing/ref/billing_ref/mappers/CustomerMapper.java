package ma.atos.billing.ref.billing_ref.mappers;

import ma.atos.billing.ref.billing_ref.models.Customer;
import ma.atos.billing.ref.billing_ref.dtos.CustomerRequestDTO;
import ma.atos.billing.ref.billing_ref.dtos.CustomerResponseDTO;
import org.springframework.stereotype.Component;


@Component
public class CustomerMapper {


    public Customer toEntity(CustomerRequestDTO requestDTO) {
        return Customer.builder()
                .nom(requestDTO.getNom())
                .prenom(requestDTO.getPrenom())
                .firstName(requestDTO.getFirstName())
                .lastName(requestDTO.getLastName())
                .email(requestDTO.getEmail())
                .adresse(requestDTO.getAdresse())
                .phoneNumber(requestDTO.getPhoneNumber())
                .paymentType(requestDTO.getPaymentType())
                .build();
    }


    public CustomerResponseDTO toResponseDTO(Customer entity) {
        return CustomerResponseDTO.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .prenom(entity.getPrenom())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .adresse(entity.getAdresse())
                .phoneNumber(entity.getPhoneNumber())
                .paymentType(entity.getPaymentType())
                .createdDate(entity.getCreatedDate())
                .updatedDate(entity.getUpdatedDate())
                .build();
    }
}