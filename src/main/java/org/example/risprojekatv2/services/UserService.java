package org.example.risprojekatv2.services;

import org.example.risprojekatv2.models.Korisnik;
import org.example.risprojekatv2.models.Post;
import org.example.risprojekatv2.models.Pracenje;
import org.example.risprojekatv2.models.Razgovor;
import org.example.risprojekatv2.repositories.KorisnikPostRepository;
import org.example.risprojekatv2.repositories.KorisnikRepository;
import org.example.risprojekatv2.repositories.PracenjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    KorisnikRepository kr;

    @Autowired
    KorisnikPostRepository kpr;

    @Autowired
    PracenjeRepository pr;


    public Korisnik getKorisnikByUserName(String username){
        if(kr.existsByUsername(username))
            return kr.getKorisnikByUsername(username);
        return null;
    }

    public Korisnik getKorisnikByMail(String mail){
        return kr.getKorisnikByMail(mail);
    }

    public List<Post> getKorisnikPost(Integer id){
        List<Post> posts = kpr.getKorisnikPost(id);
        Collections.reverse(posts);
        return posts;
    }

    public void save(Korisnik k){
        kr.save(k);
    }

    public void update(String mail, String novaLozinka){ kr.forgotPassword(mail, novaLozinka);}

    public List<Korisnik> getAllKorisnik(){
        return kr.findAll();
    }

    public Pracenje getPracenje(Integer pratiocId, Integer pratilacId){
        return pr.getPracenje(pratiocId, pratilacId);
    }

    public void deletePracenje(Pracenje p){
        pr.delete(p);
    }

    public void savePracenje(Pracenje p){
        pr.save(p);
    }

    public List<Pracenje> getFollowers(Korisnik k) {
        List<Pracenje> pracenja = pr.findAll();
        List<Pracenje> filteredPracenja = pracenja.stream()
                .filter(pracenje -> pracenje.getId().getPratilacId() == k.getId())
                .collect(Collectors.toList());
        return filteredPracenja;
    }

    public List<Pracenje> getFollowing(Korisnik k) {
        List<Pracenje> pracenja = pr.findAll();
        List<Pracenje> filteredPracenja = pracenja.stream()
                .filter(pracenje -> pracenje.getId().getPratiocId() == k.getId())
                .collect(Collectors.toList());
        return filteredPracenja;
    }

}
