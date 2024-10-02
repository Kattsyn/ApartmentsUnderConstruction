package kattsyn.dev.ApartmentsUnderConstruction.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Floor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "floor_id")
    Long floorId;
    @Column(name = "floor_number")
    Byte floorNumber;
    @Column(name = "floor_plan")
    String floorPlan;

    @OneToMany
    List<Apartment> apartments;

    @ManyToOne
    House house;
}
