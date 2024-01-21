package org.example.risprojekatv2.services;

import org.example.risprojekatv2.models.Korisnik;
import org.example.risprojekatv2.models.Post;
import org.example.risprojekatv2.repositories.KorisnikPostRepository;
import org.example.risprojekatv2.repositories.KorisnikRepository;
import org.example.risprojekatv2.repositories.PostRepository;
import org.example.risprojekatv2.models.Post;
import org.example.risprojekatv2.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository pr;



    public List<Post> getPosts(){
        return pr.findAll();
    }

    public Optional<Post> getPostByID(int id){ return pr.findById(id); }

    public void savePost(Post p){ pr.save(p); }


;}
