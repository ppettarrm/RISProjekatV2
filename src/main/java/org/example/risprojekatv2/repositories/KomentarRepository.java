package org.example.risprojekatv2.repositories;

import org.example.risprojekatv2.models.Komentar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KomentarRepository extends JpaRepository<Komentar, Integer> {

    public List<Komentar> getKomentarsByPostid(Long id);

    public void deleteAllByPostid(Long id);

}
