package org.example.risprojekatv2.repositories;

import org.example.risprojekatv2.models.Likepost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikepostRepository extends JpaRepository<Likepost, Integer> {

    @Query("Select lk from Likepost lk where lk.postid = :pid and lk.userid = :uid")
    public Likepost getLikepost(@Param("pid") Long pid, @Param("uid")Integer uid);

}
