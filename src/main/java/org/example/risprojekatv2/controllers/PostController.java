package org.example.risprojekatv2.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.risprojekatv2.models.Post;
import org.example.risprojekatv2.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/{id}")
    public String getPost(@PathVariable("id") Integer id, HttpServletRequest req) {
        req.getSession().removeAttribute("message");
        req.getSession().removeAttribute("post");
        Post p;
        try {
            p = postService.getPostByID(id).get();
        } catch (Exception ex) {
            p = null;
        }
        if (p != null) {
            List<Post> posts = postService.getPosts();
            String base64Image = null;
            if (p.getSlika() != null) {
                base64Image = Base64.getEncoder().encodeToString(p.getSlika());
            }
            req.getSession().setAttribute("images", base64Image);
            req.getSession().setAttribute("post", p);
            req.getSession().setAttribute("comments", postService.getComments(p));
        }
        return "post";
    }

}
