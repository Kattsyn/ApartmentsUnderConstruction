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
@Table(name = "apartments")
public class Apartment {

    @Id
    @Column(name = "apartment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long apartmentId;
    @Column(name = "apartment_number")
    private Integer apartmentNumber;
    @Column(name = "total_area")
    private Float totalArea;
    @Column(name = "living_area")
    private Float livingArea;
    @Column(name = "amount_of_rooms")
    private Byte amountOfRooms;
    @Column(name = "entrance_number")
    private Byte entranceNumber;
    @Column(name = "apartment_cost")
    private Integer apartmentCost;
    @Column(name = "apartment_plan")
    private String apartmentPlan;

    @ManyToOne
    @JoinColumn(name = "floor_id")
    private Floor floor;
    @ManyToOne
    @JoinColumn(name = "sale_status_id")
    private SaleStatus status;

    //@ManyToMany
    //private List<Owner> owners;

    public Apartment(Integer apartmentNumber, Float totalArea, Float livingArea, Byte amountOfRooms, Byte entranceNumber, Integer apartmentCost, String apartmentPlan) {
        this.apartmentNumber = apartmentNumber;
        this.totalArea = totalArea;
        this.livingArea = livingArea;
        this.amountOfRooms = amountOfRooms;
        this.entranceNumber = entranceNumber;
        this.apartmentCost = apartmentCost;
        this.apartmentPlan = apartmentPlan;
    }
}
