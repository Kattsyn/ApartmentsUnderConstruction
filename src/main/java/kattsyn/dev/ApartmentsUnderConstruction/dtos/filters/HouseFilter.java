package kattsyn.dev.ApartmentsUnderConstruction.dtos.filters;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HouseFilter {

    private Integer regionId;
    private String address;
    private String name;
    private LocalDate buildingStartDate;
    private LocalDate plannedBuildingEndDate;
    private LocalDate commissioningDate;
    private String housePlan;
    private Boolean isReady;

}
