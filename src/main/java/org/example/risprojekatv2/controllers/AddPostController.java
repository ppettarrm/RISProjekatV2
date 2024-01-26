package org.example.risprojekatv2.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.risprojekatv2.dto.AddPostDTO;
import org.example.risprojekatv2.models.Korisnik;
import org.example.risprojekatv2.models.Post;
import org.example.risprojekatv2.services.PostService;
import org.example.risprojekatv2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/addPost")
public class AddPostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String getAddPostPage(){
        return "addPost";
    }

    @PostMapping("/")
    public String addPost(@ModelAttribute AddPostDTO addPostDTO, HttpServletRequest req) {
        req.getSession().removeAttribute("message");
        String fileName = addPostDTO.getImage().getOriginalFilename();
        if (fileName != null && !fileName.toLowerCase().matches(".*\\.(png|jpeg|jpg)$")) {
            req.getSession().setAttribute("message", "File has to be an image!");
            return "addPost";
        }
        Korisnik k = (Korisnik) userService.getKorisnikByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        if (k == null){
            req.getSession().setAttribute("message", "Error occurred! Try again!");
            return "addPost";
        }
        Post p = new Post();
        p.setOpis(addPostDTO.getDescription());
        try {
            p.setSlika(addPostDTO.getImage().getBytes());
        } catch (IOException ex) {
            p.setSlika(null);
        }
        p.setKorisnik(k);

        postService.savePost(p);
        return "redirect:/post/" + p.getId();
    }

}
