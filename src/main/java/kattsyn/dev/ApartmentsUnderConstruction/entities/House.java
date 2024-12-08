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

    public House(String address, String name, LocalDate buildingStartDate, LocalDate plannedBuildingEndDate, LocalDate commissioningDate) {
        this.address = address;
        this.name = name;
        this.buildingStartDate = buildingStartDate;
        this.plannedBuildingEndDate = plannedBuildingEndDate;
        this.commissioningDate = commissioningDate;
    }

    @ManyToMany
    @JoinTable(
            name = "houses_images",
            joinColumns = @JoinColumn(name = "house_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private List<Image> images;
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;
}
