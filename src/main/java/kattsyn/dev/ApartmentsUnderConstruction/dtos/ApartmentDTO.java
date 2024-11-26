package kattsyn.dev.ApartmentsUnderConstruction.dtos;

import kattsyn.dev.ApartmentsUnderConstruction.entities.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentDTO {

    private Integer apartmentNumber;
    private Integer totalArea;
    private Integer livingArea;
    private Byte amountOfRooms;
    private Byte entranceNumber;
    private Integer apartmentCost;
    private String apartmentPlan;

    private Long floorId;
    private Long statusId;
    private List<Owner> owners;

    public ApartmentDTO(Integer apartmentNumber, Integer totalArea, Integer livingArea, Byte amountOfRooms, Byte entranceNumber, Integer apartmentCost, String apartmentPlan) {
        this.apartmentNumber = apartmentNumber;
        this.totalArea = totalArea;
        this.livingArea = livingArea;
        this.amountOfRooms = amountOfRooms;
        this.entranceNumber = entranceNumber;
        this.apartmentCost = apartmentCost;
        this.apartmentPlan = apartmentPlan;
    }
}
