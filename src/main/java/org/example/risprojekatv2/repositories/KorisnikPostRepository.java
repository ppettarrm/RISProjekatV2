package org.example.risprojekatv2.repositories;

import org.example.risprojekatv2.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KorisnikPostRepository extends JpaRepository<Post, Integer> {
    @Query("Select p from Post p where p.korisnik.id = :uID")
    public List<Post> getKorisnikPost(@Param("uID") Integer id);
}
