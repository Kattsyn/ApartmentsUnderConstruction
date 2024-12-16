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
    private Float totalArea;
    private Float livingArea;
    private Byte amountOfRooms;
    private Byte entranceNumber;
    private Integer apartmentCost;
    private String apartmentPlan;

    private Long floorId;
    private Integer statusId;
    private List<Owner> owners;

}
