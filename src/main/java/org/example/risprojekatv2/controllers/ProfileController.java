package org.example.risprojekatv2.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.risprojekatv2.models.Korisnik;
import org.example.risprojekatv2.models.Post;
import org.example.risprojekatv2.services.PostService;
import org.example.risprojekatv2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @GetMapping("/{username}")
    public String getProfile(@PathVariable("username") String username, HttpServletRequest req){
        req.getSession().removeAttribute("message");
        req.getSession().removeAttribute("posts");
        req.getSession().removeAttribute("images");
        Korisnik k = userService.getKorisnikByUserName(username);
        if(k == null){
            return "redirect:/home";
        }
        req.getSession().setAttribute("user", k);
        byte[] imageBytes = k.getUserImage();
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        req.setAttribute("base64Image", base64Image);
        List<Post> posts = userService.getKorisnikPost(k.getId());
        HashMap<Long, String> slike = new HashMap<>();
        for(Post p : posts){
            if(p.getSlika() != null){
                base64Image = Base64.getEncoder().encodeToString(p.getSlika());
                slike.put(p.getId(), base64Image);
            }
        }
        req.getSession().setAttribute("posts", posts);
        req.getSession().setAttribute("images", slike);
        return "profile";
    }
}
