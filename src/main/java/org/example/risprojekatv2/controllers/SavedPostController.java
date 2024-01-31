package org.example.risprojekatv2.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.risprojekatv2.models.Korisnik;
import org.example.risprojekatv2.services.PostService;
import org.example.risprojekatv2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/saved")
public class SavedPostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String getSaved(HttpServletRequest req){
        Korisnik k = userService.getKorisnikByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        req.getSession().setAttribute("posts", postService.getSavedPosts(k.getId()));
        return "saved";
    }
}
