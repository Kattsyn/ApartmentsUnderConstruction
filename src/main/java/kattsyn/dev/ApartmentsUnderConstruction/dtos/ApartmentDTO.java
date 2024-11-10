package kattsyn.dev.ApartmentsUnderConstruction.dtos;

import jakarta.persistence.*;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Floor;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Owner;
import kattsyn.dev.ApartmentsUnderConstruction.entities.SaleStatus;
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

    private Integer totalArea;
    private Integer livingArea;
    private Byte amountOfRooms;
    private Byte entranceNumber;
    private Integer apartmentCost;
    private String apartmentPlan;

    Long floorId;
    Integer statusId;
    List<Owner> owners;

    public ApartmentDTO(Integer totalArea, Integer livingArea, Byte amountOfRooms, Byte entranceNumber, Integer apartmentCost, String apartmentPlan) {
        this.totalArea = totalArea;
        this.livingArea = livingArea;
        this.amountOfRooms = amountOfRooms;
        this.entranceNumber = entranceNumber;
        this.apartmentCost = apartmentCost;
        this.apartmentPlan = apartmentPlan;
    }
}
