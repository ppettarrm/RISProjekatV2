package org.example.risprojekatv2.repositories;

import org.example.risprojekatv2.models.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
    Optional<Korisnik> findKorisnikByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByMail(String mail);

}
