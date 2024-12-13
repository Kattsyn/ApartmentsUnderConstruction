package kattsyn.dev.ApartmentsUnderConstruction.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HouseDTO {

    private Integer regionId;
    private String address;
    private String name;
    private LocalDate buildingStartDate;
    private LocalDate plannedBuildingEndDate;
    private LocalDate commissioningDate;
    private String housePlan;
    private Boolean isReady;

    /*
    public void setPlannedBuildingEndDate(LocalDate plannedBuildingEndDate) {
        if (plannedBuildingEndDate != null && plannedBuildingEndDate.isBefore(this.buildingStartDate)) {
            throw new IllegalArgumentException("End date cannot be earlier than start date.");
        }
        this.plannedBuildingEndDate = plannedBuildingEndDate;
    }

    public void setCommissioningDate(LocalDate commissioningDate) {
        if (commissioningDate != null && commissioningDate.isBefore(this.plannedBuildingEndDate)) {
            throw new IllegalArgumentException("Commissioning date cannot be earlier than building end date.");
        }
        this.commissioningDate = commissioningDate;
    }
     */
}
