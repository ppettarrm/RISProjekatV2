package org.example.risprojekatv2.repositories;

import org.example.risprojekatv2.models.Pracenje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PracenjeRepository extends JpaRepository<Pracenje, Integer> {

    @Query("select p from Pracenje p where p.id.pratiocId = :pratiocId and p.id.pratilacId = :pratilacId")
    public Pracenje getPracenje(@Param("pratiocId")Integer pratiocId, @Param("pratilacId")Integer pratilacId);
}
