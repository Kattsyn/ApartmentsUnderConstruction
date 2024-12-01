package kattsyn.dev.ApartmentsUnderConstruction.service;

import kattsyn.dev.ApartmentsUnderConstruction.entities.Role;
import kattsyn.dev.ApartmentsUnderConstruction.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").orElseThrow(
                () -> new IllegalArgumentException("ROLE_USER NOT FOUND")
        );
    }

    public Role getManagerRole() {
        return roleRepository.findByName("ROLE_MANAGER").orElseThrow(
                () -> new IllegalArgumentException("ROLE_MANAGER NOT FOUND")
        );
    }

}
