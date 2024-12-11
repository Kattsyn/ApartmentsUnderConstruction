package kattsyn.dev.ApartmentsUnderConstruction.dtos.filters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentFilter {
    private Long floorId;
    private String regionName;
    private String houseName;
    private byte amountOfRooms;
    private byte floorNumber;
    private int minTotalArea;
    private int maxTotalArea;
    private int minApartmentCost;
    private int maxApartmentCost;

    @Override
    public String toString() {
        return "ApartmentFilter{" +
                "floorId=" + floorId +
                ", regionName='" + regionName + '\'' +
                ", houseName='" + houseName + '\'' +
                ", amountOfRooms=" + amountOfRooms +
                ", floorNumber=" + floorNumber +
                ", minTotalArea=" + minTotalArea +
                ", maxTotalArea=" + maxTotalArea +
                ", minApartmentCost=" + minApartmentCost +
                ", maxApartmentCost=" + maxApartmentCost +
                '}';
    }
}
