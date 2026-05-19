package ma.atos.billing.ref.billing_ref.mappers;


import ma.atos.billing.ref.billing_ref.dto.CustomerDTO;
import ma.atos.billing.ref.billing_ref.models.Customer;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toDto(Customer entity);

    Customer toEntity(CustomerDTO dto);

    List<CustomerDTO> toDtoList(List<Customer> entities);
}
