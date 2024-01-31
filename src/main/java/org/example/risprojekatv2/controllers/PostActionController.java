package org.example.risprojekatv2.controllers;

import org.example.risprojekatv2.models.Korisnik;
import org.example.risprojekatv2.models.Likepost;
import org.example.risprojekatv2.models.Post;
import org.example.risprojekatv2.models.Savedpost;
import org.example.risprojekatv2.services.PostService;
import org.example.risprojekatv2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/postAction")
public class PostActionController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;



    @PostMapping("/like")
    public void likePost(@RequestParam("post_id")Long pid){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Korisnik k = userService.getKorisnikByUserName(username);
        Likepost lp = new Likepost();
        lp.setUserid(k.getId());
        lp.setPostid(pid);
        postService.saveLike(lp);
    }

    @PostMapping("/save")
    public void savePost(@RequestParam("post_id")Long pid){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Korisnik k = userService.getKorisnikByUserName(username);
        Savedpost sp = new Savedpost();
        sp.setUserid(k.getId());
        sp.setPostid(pid);
        postService.saveSavedpost(sp);
    }

}
