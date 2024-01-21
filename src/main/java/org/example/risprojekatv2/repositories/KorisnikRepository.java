package org.example.risprojekatv2.repositories;

import org.example.risprojekatv2.models.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
    Optional<Korisnik> findKorisnikByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByMail(String mail);

    @Transactional
    @Modifying
    @Query("update Korisnik k set k.password = :lozinka where k.mail = :mail")
    void forgotPassword(@Param("mail") String mail, @Param("lozinka") String lozinka);

    public Korisnik getKorisnikByMail(String mail);
    public Korisnik getKorisnikByUsername(String username);

}
