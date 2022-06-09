package com.example.mobilelele.web;

import com.example.mobilelele.models.dtos.UserLoginDTO;
import com.example.mobilelele.models.dtos.UserRegisterDTO;
import com.example.mobilelele.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/login")
    public String loginPage() {
        return "auth-login";
    }

    @PostMapping("login")
    public String loginUser(
            @Valid UserLoginDTO userLoginDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
                            ) {
        if (bindingResult.hasErrors() || !this.userService.userLoggedIn(userLoginDTO)) {
            handleUserPostErrors(userLoginDTO, bindingResult, redirectAttributes);
            bindingResult.rejectValue("password", "InvalidPasswordError", "Invalid password.");
            return "redirect:/users/login";
        }

        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String registerPage
            (
            @Valid UserRegisterDTO user,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
                                                  ) {

        if (bindingResult.hasErrors() || !this.userService.registerUser(user)) {
            handleUserPostErrors(user, bindingResult, redirectAttributes);
            bindingResult.rejectValue("username", "UsernameTakenError", "This username is already taken.");
            return "redirect:/users/register";
        }
        return "redirect:/users/login";
    }

    @GetMapping("/logout")
    public String logout() {
        this.userService.logout();

        return "redirect:/";
    }


    private void handleUserPostErrors(Object userInput, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("userModel", userInput);
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",
                bindingResult);
    }
}
