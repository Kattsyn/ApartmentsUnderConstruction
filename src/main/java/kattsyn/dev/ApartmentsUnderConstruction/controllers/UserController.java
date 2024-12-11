package kattsyn.dev.ApartmentsUnderConstruction.controllers;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.UserDTO;
import kattsyn.dev.ApartmentsUnderConstruction.entities.User;
import kattsyn.dev.ApartmentsUnderConstruction.mappers.UserMapper;
import kattsyn.dev.ApartmentsUnderConstruction.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Secured("ROLE_ADMIN")
    @GetMapping({"", "/"})
    public String showUsersList(
            Model model,
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "25") int pageSize) {
        List<UserDTO> userDTOs = userMapper.toUserDTOs(userService.getUsersPage(currentPage, pageSize).getContent());
        model.addAttribute("users", userDTOs);
        return "users/index";
    }
}
