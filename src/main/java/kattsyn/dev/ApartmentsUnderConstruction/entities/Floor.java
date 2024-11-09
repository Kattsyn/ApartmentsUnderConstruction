package kattsyn.dev.ApartmentsUnderConstruction.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "floors")
public class Floor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "floor_id")
    private Long floorId;
    @Column(name = "floor_number")
    private Byte floorNumber;
    @Column(name = "floor_plan")
    private String floorPlan;

    public Floor(Byte floorNumber, String floorPlan) {
        this.floorNumber = floorNumber;
        this.floorPlan = floorPlan;
    }


    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;
}
