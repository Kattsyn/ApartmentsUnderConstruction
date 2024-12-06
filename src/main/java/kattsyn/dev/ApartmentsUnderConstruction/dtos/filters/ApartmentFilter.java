package kattsyn.dev.ApartmentsUnderConstruction.dtos.filters;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApartmentFilter {
    //private String region;
    //private String houseName;
    private byte amountOfRooms;
    //private byte floorNumber;
    //private int minTotalArea;
    //private int maxTotalArea;
    private int minApartmentCost;
    private int maxApartmentCost;
}
