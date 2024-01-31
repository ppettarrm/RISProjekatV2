package org.example.risprojekatv2.services;

import org.example.risprojekatv2.models.*;
import org.example.risprojekatv2.repositories.*;
import org.example.risprojekatv2.models.Post;
import org.example.risprojekatv2.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {


    @Autowired
    PostRepository pr;

    @Autowired
    LikepostRepository lpr;

    @Autowired
    SavedpostRepository spr;



    public List<Post> getPosts(){
        List<Post> posts = pr.findAll();
        Collections.reverse(posts);
        return posts;
    }

    public Optional<Post> getPostByID(int id){ return pr.findById(id); }

    public void savePost(Post p){ pr.save(p); }

    public void saveLike(Likepost lp){
        Likepost exists = lpr.getLikepost(lp.getPostid(), lp.getUserid());
        if(exists == null){
            System.out.println("SACUVANO");
            lpr.save(lp);
        }
        else
            lpr.delete(exists);
    }

    public void saveSavedpost(Savedpost sp){
        Savedpost exists = spr.getSavedpost(sp.getPostid(), sp.getUserid());
        if(exists == null){
            spr.save(sp);
        }
        else
            spr.delete(exists);
    }

    public List<Post> getSavedPosts(Integer uid){
        List<Post> posts = pr.getSavedPosts(uid);
        return posts;
    }


;}
