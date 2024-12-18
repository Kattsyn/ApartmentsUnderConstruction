package kattsyn.dev.ApartmentsUnderConstruction.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "apartments_reservations")
public class ApartmentReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apartments_reservations_id")
    private Long apartmentReservationId;
    @Column(name = "client_name")
    private String clientName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "call_request_date")
    private LocalDate callRequestDate;
    @Column(name = "is_confirmed")
    private Boolean isConfirmed;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

}
