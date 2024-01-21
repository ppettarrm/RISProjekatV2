package org.example.risprojekatv2.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.risprojekatv2.models.Korisnik;
import org.example.risprojekatv2.models.Post;
import org.example.risprojekatv2.repositories.KorisnikRepository;
import org.example.risprojekatv2.services.PostService;
import org.example.risprojekatv2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("")
class GlobalController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String getLogin(){
        return "login";
    }

    @GetMapping("/home")
    public String getHome(HttpServletRequest req) {
        List<Post> posts = postService.getPosts();
        HashMap<Long, String> slike = new HashMap<>();
        String base64Image;
        for(Post p : posts){
            if(p.getSlika() != null){
                base64Image = Base64.getEncoder().encodeToString(p.getSlika());
                slike.put(p.getId(), base64Image);
            }
        }
        req.getSession().setAttribute("posts", posts);
        req.getSession().setAttribute("images", slike);
        return "home";
    }

}