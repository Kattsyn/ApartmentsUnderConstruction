package kattsyn.dev.ApartmentsUnderConstruction.service;

import kattsyn.dev.ApartmentsUnderConstruction.entities.Role;
import kattsyn.dev.ApartmentsUnderConstruction.enums.Roles;
import kattsyn.dev.ApartmentsUnderConstruction.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName(Roles.ROLE_USER.name()).orElseThrow(
                () -> new IllegalArgumentException(Roles.ROLE_USER.name() + " NOT FOUND")
        );
    }

    public Role getManagerRole() {
        return roleRepository.findByName(Roles.ROLE_MANAGER.name()).orElseThrow(
                () -> new IllegalArgumentException(Roles.ROLE_MANAGER.name() + " NOT FOUND")
        );
    }

}
