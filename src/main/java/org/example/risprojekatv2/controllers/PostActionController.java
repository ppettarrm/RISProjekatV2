package org.example.risprojekatv2.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.risprojekatv2.dto.AddCommentDTO;
import org.example.risprojekatv2.models.*;
import org.example.risprojekatv2.services.PostService;
import org.example.risprojekatv2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;

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

    @PostMapping("/comment")
    public String commentPost(@ModelAttribute AddCommentDTO addCommentDTO, @RequestParam("post_id")Integer pid, HttpServletRequest req){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Korisnik k = userService.getKorisnikByUserName(username);
        Post p = postService.getPostByID(pid).get();
        if(k == null || p == null)
            return "home";

        Komentar komentar = new Komentar();
        komentar.setKorisnik(k);
        komentar.setTekst(addCommentDTO.getComment());
        komentar.setPostid(p.getId());
        komentar.setTimestamp(Instant.now());

        postService.saveKomentar(komentar);
        return "redirect:/post/" + p.getId();
    }

    @PostMapping("/delete")
    public String deletePost(@RequestParam("post_id")Integer pid){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Korisnik k = userService.getKorisnikByUserName(username);
        Post p = postService.getPostByID(pid).get();
        if(k == null || p == null)
            return "home";

        if(k.getId() != p.getKorisnik().getId()){
            return "home";
        }

        postService.deletePost(p);

        return "home";
    }

}
