package org.example.risprojekatv2.repositories;

import org.example.risprojekatv2.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("Select p from Post p where p.id in (Select sp.postid from Savedpost sp where sp.userid = :uid)")
    public List<Post> getSavedPosts(@Param("uid")Integer uid);

}
