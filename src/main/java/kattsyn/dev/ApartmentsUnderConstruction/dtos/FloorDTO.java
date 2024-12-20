package kattsyn.dev.ApartmentsUnderConstruction.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FloorDTO {

    private Integer floorNumber;
    private String floorPlan;
    private Long houseId;

}
