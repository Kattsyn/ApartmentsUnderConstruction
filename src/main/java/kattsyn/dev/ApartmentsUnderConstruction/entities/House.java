package kattsyn.dev.ApartmentsUnderConstruction.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "houses")
public class House {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private Long houseId;
    @Column(name = "address")
    private String address;
    @Column(name = "name")
    private String name;
    @Column(name = "building_start_date")
    private LocalDate buildingStartDate;
    @Column(name = "planned_building_end_date")
    private LocalDate plannedBuildingEndDate;
    @Column(name = "commissioning_date")
    private LocalDate commissioningDate;
    @Column(name = "house_plan")
    private String housePlan;

    public House(String address, String name, LocalDate buildingStartDate, LocalDate plannedBuildingEndDate, LocalDate commissioningDate, String housePlan) {
        this.address = address;
        this.name = name;
        this.buildingStartDate = buildingStartDate;
        this.plannedBuildingEndDate = plannedBuildingEndDate;
        this.commissioningDate = commissioningDate;
        this.housePlan = housePlan;
    }


}
