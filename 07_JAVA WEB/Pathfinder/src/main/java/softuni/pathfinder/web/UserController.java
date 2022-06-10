package softuni.pathfinder.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.pathfinder.models.dtos.authDTOs.UserLoginDTO;
import softuni.pathfinder.models.dtos.authDTOs.UserRegisterDTO;
import softuni.pathfinder.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userModel")
    public UserRegisterDTO initUserModel() {
        return new UserRegisterDTO();
    }

    @ModelAttribute("userLoginModel")
    public UserLoginDTO loginUserModel() {
        return new UserLoginDTO();
    }

    @GetMapping("/register")
    private String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    private String register(@Valid UserRegisterDTO userRegisterDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.userService.registerNewUser(userRegisterDTO)) {
            redirectAttributes.addFlashAttribute("userModel", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",
                    bindingResult);

            bindingResult.rejectValue("username", "username.taken", "username is already taken");
            bindingResult.rejectValue("email", "email.taken", "email is already taken");

            return "redirect:/users/register";
        }

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    private String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String userLogin(@Valid UserLoginDTO userLoginDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.userService.loginUser(userLoginDTO)) {
            redirectAttributes.addFlashAttribute("userLoginModel", userLoginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginModel", bindingResult);
            bindingResult.rejectValue("password", "wrong.password", "Invalid password");
            return "redirect:/users/login";
        }

        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "profile";
    }

    @GetMapping("/logout")
    public String logout() {
        this.userService.logoutUser();
        return "redirect:/";
    }
}
