package org.example.risprojekatv2.repositories;

import org.example.risprojekatv2.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
