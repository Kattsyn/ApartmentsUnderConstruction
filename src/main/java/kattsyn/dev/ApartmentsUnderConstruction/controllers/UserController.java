package kattsyn.dev.ApartmentsUnderConstruction.controllers;

import kattsyn.dev.ApartmentsUnderConstruction.dtos.UserDTO;
import kattsyn.dev.ApartmentsUnderConstruction.enums.Roles;
import kattsyn.dev.ApartmentsUnderConstruction.mappers.UserMapper;
import kattsyn.dev.ApartmentsUnderConstruction.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
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

    @Secured("ROLE_ADMIN")
    @GetMapping("/addRole")
    public void addRole(
            @RequestParam Long userId,
            @RequestParam Integer roleId
    ) {
        userService.addRoleByRoleId(userId, roleId);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/addManagerRole")
    public String addManagerRole(
            @RequestParam Long userId
    ) {
        userService.addRoleByRoleId(userId, Roles.ROLE_MANAGER.ordinal() + 1);
        return "redirect:/users/";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/removeRole")
    public String removeRole(
            @RequestParam Long userId,
            @RequestParam Integer roleId
    ) {
        userService.removeRole(userId, roleId);
        return "redirect:/users/";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/activate")
    public String activateUser(
            @RequestParam Long userId
    ) {
        userService.activateUser(userId);
        return "redirect:/users/";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/deactivate")
    public String deactivateUser(
            @RequestParam Long userId
    ) {
        userService.deactivateUser(userId);
        return "redirect:/users/";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/delete")
    public String deleteUser(
            @RequestParam Long userId
    ) {
        userService.deleteUser(userId);
        return "redirect:/users/";
    }
}
