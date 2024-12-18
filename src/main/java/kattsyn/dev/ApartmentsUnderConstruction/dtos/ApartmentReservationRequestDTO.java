package kattsyn.dev.ApartmentsUnderConstruction.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApartmentReservationRequestDTO {

    private Long apartmentReservationId;
    private Long apartmentId;
    private String clientName;
    private String phoneNumber;
    private LocalDate callRequestDate;
    private Boolean isConfirmed;

}
