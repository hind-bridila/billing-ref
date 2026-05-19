package ma.atos.billing.ref.billing_ref.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.sql.Date;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessObject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GLOBAL_SEQUENCE")
    @SequenceGenerator(
            name = "GLOBAL_SEQUENCE",
            sequenceName = "GLOBAL_SEQUENCE",
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "UPDATED_DATE")
    private LocalDateTime updatedDate;
}
