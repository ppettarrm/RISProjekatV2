package org.example.risprojekatv2.repositories;

import org.example.risprojekatv2.models.Likepost;
import org.example.risprojekatv2.models.Savedpost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SavedpostRepository extends JpaRepository<Savedpost, Integer> {
    @Query("Select sp from Savedpost sp where sp.postid = :pid and sp.userid = :uid")
    public Savedpost getSavedpost(@Param("pid") Long pid, @Param("uid")Integer uid);

    public List<Savedpost> getSavedpostsByUserid(Integer uid);
}
