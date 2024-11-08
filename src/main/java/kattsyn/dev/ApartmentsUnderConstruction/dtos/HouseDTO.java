package kattsyn.dev.ApartmentsUnderConstruction.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HouseDTO {

    //@NotEmpty(message = "The address is required.")
    private String address;
    //@NotEmpty(message = "The name is required.")
    private String name;
    private LocalDate buildingStartDate;
    private LocalDate plannedBuildingEndDate;
    private LocalDate commissioningDate;

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
    //todo: разобраться что с этим делать, либо удалить, тк та же проверка происходит на фронте. Если забить,
    то вылетает ошибка от bindingResult в методе create. А если закомментить вылет ошибки, то поле commissioningDate не сохраняется
     */
}
