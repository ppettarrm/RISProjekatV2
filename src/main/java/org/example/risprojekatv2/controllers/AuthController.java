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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>("Successfuly logged in", HttpStatus.OK);
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterDTO registerDTO){
        if(korisnikRepository.existsByUsername(registerDTO.getUsername())){
            return "error";
        }

        Korisnik k = new Korisnik();
        k.setUsername(registerDTO.getUsername());
        k.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        Role roles = roleRepository.findByName("user").get();
        k.setRoles(Collections.singletonList(roles));
        k.setMail(registerDTO.getMail());
        k.setDescription(registerDTO.getDescription());
        try {
            k.setUserImage(registerDTO.getUserImage().getBytes());
        } catch (IOException ex){
            k.setUserImage("".getBytes());
        }
        korisnikRepository.save(k);
        return "index";
    }
}
