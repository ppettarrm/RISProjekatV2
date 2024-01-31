package org.example.risprojekatv2.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.risprojekatv2.dto.SearchDTO;
import org.example.risprojekatv2.models.Korisnik;
import org.example.risprojekatv2.models.Post;
import org.example.risprojekatv2.services.PostService;
import org.example.risprojekatv2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @PostMapping("/")
    public String getProfile(@ModelAttribute SearchDTO searchDTO, HttpServletRequest req){
        List<Korisnik> users = userService.getAllKorisnik();
        List<Korisnik> filteredUsers = users.stream()
                .filter(user -> user.getUsername().contains(searchDTO.getSearch()))
                .collect(Collectors.toList());
        List<Post> posts = postService.getPosts();
        List<Post> filteredPosts = posts.stream()
                .filter(post -> post.getOpis().contains(searchDTO.getSearch()))
                .collect(Collectors.toList());
        req.getSession().setAttribute("posts", filteredPosts);
        req.getSession().setAttribute("users", filteredUsers);
        return "search";
    }

}
