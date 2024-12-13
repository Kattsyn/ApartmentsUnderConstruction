package kattsyn.dev.ApartmentsUnderConstruction.dtos.filters;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApartmentFilter {
    private Long floorId;
    private Integer regionId;
    private String houseName;
    private byte amountOfRooms;
    private byte floorNumber;
    private int minTotalArea;
    private int maxTotalArea;
    private int minApartmentCost;
    private int maxApartmentCost;

}
