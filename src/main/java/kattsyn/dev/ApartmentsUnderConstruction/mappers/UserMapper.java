package kattsyn.dev.ApartmentsUnderConstruction.mappers;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.UserDTO;
import kattsyn.dev.ApartmentsUnderConstruction.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserDTO toUserDTO(User user);

    User fromUserDTO(UserDTO userDTO);

    List<UserDTO> toUserDTOs(List<User> users);

}
