package kattsyn.dev.ApartmentsUnderConstruction.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "roles")
public class Role {

    @Column(name = "role_id")
    @Id
    private Integer roleId;
    @Column(name = "name")
    private String name;

}
