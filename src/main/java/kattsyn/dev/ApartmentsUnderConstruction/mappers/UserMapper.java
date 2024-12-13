package kattsyn.dev.ApartmentsUnderConstruction.mappers;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.RegistrationDTO;
import kattsyn.dev.ApartmentsUnderConstruction.dtos.UserDTO;
import kattsyn.dev.ApartmentsUnderConstruction.entities.User;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserDTO toUserDTO(User user);

    User fromUserDTO(UserDTO userDTO);

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(registrationDTO.getPassword()))")
    User fromRegistrationDTO(RegistrationDTO registrationDTO, @Context PasswordEncoder passwordEncoder);

    List<UserDTO> toUserDTOs(List<User> users);

}
