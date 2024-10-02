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
public class Apartment {

    @Id
    @Column(name = "apartment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long apartmentId;
    @Column(name = "total_area")
    Integer totalArea;
    @Column(name = "living_area")
    Integer livingArea;
    @Column(name = "amount_of_rooms")
    Byte amountOfRooms;
    @Column(name = "entrance_number")
    Byte entranceNumber;
    @Column(name = "apartment_cost")
    Integer apartmentCost;
    @Column(name = "apartment_plan")
    String apartmentPlan;

    @ManyToOne
    Floor floor;
    @ManyToOne
    SaleStatus status;

    @ManyToMany
    List<Owner> owners;
}
