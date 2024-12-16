package kattsyn.dev.ApartmentsUnderConstruction.service;

import kattsyn.dev.ApartmentsUnderConstruction.entities.Role;
import kattsyn.dev.ApartmentsUnderConstruction.enums.Roles;
import kattsyn.dev.ApartmentsUnderConstruction.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    @Transactional
    public Role findByName(String roleName) {
        return roleRepository.findByName(roleName).orElseThrow(
                () -> new IllegalArgumentException(String.format("Role name: %s NOT FOUND", roleName))
        );
    }

    @Transactional
    public Role findById(Integer roleId) {
        return roleRepository.findById(roleId).orElseThrow(
                () -> new IllegalArgumentException(String.format("Role id: %s NOT FOUND", roleId))
        );
    }

    @Transactional
    public Role getUserRole() {
        return roleRepository.findByName(Roles.ROLE_USER.name()).orElseThrow(
                () -> new IllegalArgumentException(Roles.ROLE_USER.name() + " NOT FOUND")
        );
    }

    @Transactional
    public Role getManagerRole() {
        return roleRepository.findByName(Roles.ROLE_MANAGER.name()).orElseThrow(
                () -> new IllegalArgumentException(Roles.ROLE_MANAGER.name() + " NOT FOUND")
        );
    }

}
