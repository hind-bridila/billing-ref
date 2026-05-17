package ma.atos.billing.ref.billing_ref.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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
    @Column(name = "created_at")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedDate;
}