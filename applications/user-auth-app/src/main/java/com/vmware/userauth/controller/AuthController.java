package com.vmware.userauth.controller;

import ch.qos.logback.core.spi.ErrorCodes;
import com.vmware.userauth.dto.UserDTO;
import com.vmware.userauth.model.User;
import com.vmware.userauth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    // Displays custom login page to all
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // Displays add user form to admin role
    @GetMapping("/register")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showAddUserForm(Model model){
        // create model object to store form data
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addUser(@Valid @ModelAttribute("user") UserDTO userDto,
                               BindingResult result,
                               Model model){
        User existingUser = authService.findUserByUsername(userDto.getUsername());

        if(existingUser != null && existingUser.getUsername() != null && !existingUser.getUsername().isEmpty()){
            result.rejectValue("username", "",
                    "There is already an account registered with the same username !");
        }

        if(result.hasErrors()){
            model.addAttribute("user", existingUser);
            return "/register";
        }

        authService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String displayUsers(Model model){
        List<UserDTO> users = authService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

}
