package org.example.risprojekatv2.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.risprojekatv2.models.Korisnik;
import org.example.risprojekatv2.models.Pracenje;
import org.example.risprojekatv2.services.PostService;
import org.example.risprojekatv2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @GetMapping("")
    public String getAdmin(HttpServletRequest req){

        List<Korisnik> korisnici = userService.getAllKorisnik();
        HashMap<Integer, Integer> pracenja = new HashMap<>();
        HashMap<Integer, Integer> postovi = new HashMap<>();
        for(Korisnik k: korisnici){
            pracenja.put(k.getId(), userService.getFollowing(k).size());
            postovi.put(k.getId(), userService.getKorisnikPost(k.getId()).size());
        }

        req.getSession().setAttribute("users", korisnici);
        req.getSession().setAttribute("following", pracenja);
        req.getSession().setAttribute("postovi", postovi);
        return "admin";
    }

}
