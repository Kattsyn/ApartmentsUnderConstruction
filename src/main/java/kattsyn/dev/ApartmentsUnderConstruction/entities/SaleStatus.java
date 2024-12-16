package kattsyn.dev.ApartmentsUnderConstruction.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sale_statuses")
public class SaleStatus {

    @Id
    @Column(name = "sale_status_id")
    private Integer saleStatusId;
    @Column(name = "name")
    private String name;

}
