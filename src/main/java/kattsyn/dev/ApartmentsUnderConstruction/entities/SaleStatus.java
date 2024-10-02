package kattsyn.dev.ApartmentsUnderConstruction.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleStatus {

    @Id
    @Column(name = "sale_status_id")
    Long saleStatusId;
    @Column(name = "name")
    String name;
}