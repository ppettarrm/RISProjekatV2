package org.example.risprojekatv2.controllers;

import org.example.risprojekatv2.dto.LoginDTO;
import org.example.risprojekatv2.dto.RegisterDTO;
import org.example.risprojekatv2.models.Korisnik;
import org.example.risprojekatv2.models.Role;
import org.example.risprojekatv2.repositories.KorisnikRepository;
import org.example.risprojekatv2.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private KorisnikRepository korisnikRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, KorisnikRepository korisnikRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.korisnikRepository = korisnikRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDTO loginDTO){
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
    public String register(@ModelAttribute RegisterDTO registerDTO){
        if(!registerDTO.getPassword().equals(registerDTO.getConfirmPassword()) ||
                korisnikRepository.existsByUsername(registerDTO.getUsername()) ||
                korisnikRepository.existsByMail(registerDTO.getMail())){
            return "register";
        }

        Korisnik k = new Korisnik();
        k.setUsername(registerDTO.getUsername());
        k.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        Role roles = roleRepository.findByName("user").get();
        k.setRoles(Collections.singletonList(roles));
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

        korisnikRepository.save(k);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            registerDTO.getUsername(), registerDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/home";
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
    public String getRegisterPage(){
        return "register";
    }

    @GetMapping("/loginPage")
    public String getLoginPage(){
        return "login";
    }
}
