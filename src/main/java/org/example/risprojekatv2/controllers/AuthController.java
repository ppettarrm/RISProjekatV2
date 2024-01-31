package org.example.risprojekatv2.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.risprojekatv2.dto.ConfirmDTO;
import org.example.risprojekatv2.dto.LoginDTO;
import org.example.risprojekatv2.dto.RegisterDTO;
import org.example.risprojekatv2.dto.ResetDTO;
import org.example.risprojekatv2.models.Korisnik;
import org.example.risprojekatv2.models.Role;
import org.example.risprojekatv2.repositories.KorisnikRepository;
import org.example.risprojekatv2.repositories.RoleRepository;
import org.example.risprojekatv2.services.MailService;
import org.example.risprojekatv2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private KorisnikRepository korisnikRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private MailService mailService;
    private UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, KorisnikRepository korisnikRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder, MailService mailService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.korisnikRepository = korisnikRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDTO loginDTO, HttpServletRequest req){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(), loginDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "home";
        } catch (Exception ex){
            ex.printStackTrace();
            return "register";
        }
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterDTO registerDTO, HttpServletRequest req){
        if(!registerDTO.getPassword().equals(registerDTO.getConfirmPassword()) ||
                korisnikRepository.existsByUsername(registerDTO.getUsername()) ||
                korisnikRepository.existsByMail(registerDTO.getMail())){
            return "register";
        }

        Korisnik k = new Korisnik();
        k.setUsername(registerDTO.getUsername());
        k.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        k.setMail(registerDTO.getMail());
        if(registerDTO.getUserImage() != null){
            String fileName = registerDTO.getUserImage().getOriginalFilename();
            if(fileName != null && !fileName.toLowerCase().matches(".*\\.(png|jpeg|jpg)"))
                return "register";
        }

        try {
            k.setUserImage(registerDTO.getUserImage().getBytes());
        } catch (IOException ex){
            return "register";
        }

        int confirmationCode = (int)(Math.random() * 8999 + 1000);
        req.getSession().setAttribute("code", confirmationCode);
        req.getSession().setAttribute("confirmationAccount", k);
        String message = "Dear " + k.getUsername() + ",\n\nYour confirmation code is " + confirmationCode + "\n\nKindly,\nCodegram";
        mailService.sendEmail(k.getMail(), "Codegram: Confirm email", message);
        return "register";
    }

    @PostMapping("/confirm")
    public String confirmMail(@ModelAttribute ConfirmDTO confirmDTO, HttpServletRequest req){
        Korisnik k = (Korisnik) req.getSession().getAttribute("confirmationAccount");
        int code = (int) req.getSession().getAttribute("code");
        if(confirmDTO.getCode() != code){
            return "register";
        }
        req.getSession().removeAttribute("code");
        req.getSession().removeAttribute("confirmationAccount");
        Role roles = roleRepository.findByName("user").get();
        k.setRoles(Collections.singletonList(roles));
        try {
            korisnikRepository.save(k);
            String message = "Dear " + k.getUsername() + ",\n\nYour account has been created.\nThank you for joining Codegram community!\n\nKindly,\nCodegram";
            mailService.sendEmail(k.getMail(), "Codegram: Account has been created!", message);
            return "redirect:/auth/loginPage";
        } catch (Exception ex){
            ex.printStackTrace();
            return "register";
        }

    }

    @GetMapping("/logout")
    public String logout(){
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/auth/loginPage";
    }

    @GetMapping("/registerPage")
    public String getRegisterPage(HttpServletRequest request){
        request.getSession().removeAttribute("message");
        return "register";
    }

    @GetMapping("/loginPage")
    public String getLoginPage(HttpServletRequest request){
        request.getSession().removeAttribute("message");
        return "login";
    }

    @GetMapping("/resetPasswordPage")
    public String getResetPage(){ return "forgotPassword"; }

    @PostMapping("/reset")
    public String reset(@ModelAttribute ResetDTO resetDTO, HttpServletRequest req){
        req.getSession().removeAttribute("message");
        Korisnik k = korisnikRepository.getKorisnikByMail(resetDTO.getMail());
        if(k == null) {
            req.getSession().setAttribute("message", "There is no user with this mail!");
            return "forgotPassword";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UUID randomUUID = UUID.randomUUID();
        String newPassword = randomUUID.toString().replaceAll("_", "");
        userService.update(k.getMail(), encoder.encode(newPassword));
        String message = "Dear " + k.getUsername() + ",\n\nYour password has been reset.\nYour new password is: " + newPassword + "\n\nKindly,\nCodegram";
        mailService.sendEmail(k.getMail(), "Codegram: Password has been reset", message);
        return "redirect: /loginPage";
    }
}
