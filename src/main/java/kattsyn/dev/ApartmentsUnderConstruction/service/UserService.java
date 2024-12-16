package kattsyn.dev.ApartmentsUnderConstruction.service;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.RegistrationDTO;
import kattsyn.dev.ApartmentsUnderConstruction.entities.Role;
import kattsyn.dev.ApartmentsUnderConstruction.entities.User;
import kattsyn.dev.ApartmentsUnderConstruction.mappers.UserMapper;
import kattsyn.dev.ApartmentsUnderConstruction.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    @Transactional
    public void activateUser(
            Long userId
    ) {
        User user = findById(userId);
        user.setStatus(true);
        save(user);
    }

    @Transactional
    public void deactivateUser(
            Long userId
    ) {
        User user = findById(userId);
        user.setStatus(false);
        save(user);
    }

    @Transactional
    public void deleteUser(
            Long userId
    ) {
        userRepository.delete(findById(userId));
    }

    @Transactional
    public void addRoleByRoleId(Long userId, Integer roleId) {
        User user = findById(userId);
        Role role = roleService.findById(roleId);
        if (!user.getRoles().contains(role)) {
            user.getRoles().add(role);
        }
        save(user);
    }

    @Transactional
    public void removeRole(Long userId, Integer roleId) {
        User user = findById(userId);
        user.getRoles().remove(roleService.findById(roleId));
        save(user);
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException(String.format("User id: %s NOT FOUND", userId)));
    }

    @Transactional
    public Page<User> getUsersPage(int currentPage, int pageSize) {
        return userRepository.findAll(PageRequest.of(currentPage, pageSize));
    }

    @Transactional
    public void createUser(RegistrationDTO registrationDTO) {
        User user = userMapper.fromRegistrationDTO(registrationDTO, passwordEncoder);
        user.setRoles(List.of(roleService.getUserRole()));
        userRepository.save(user);
    }

    @Transactional
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User %s not found", username)));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
        );
    }


}
