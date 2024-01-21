package org.example.risprojekatv2.services;

import org.example.risprojekatv2.models.Korisnik;
import org.example.risprojekatv2.models.Post;
import org.example.risprojekatv2.repositories.KorisnikPostRepository;
import org.example.risprojekatv2.repositories.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    KorisnikRepository kr;

    @Autowired
    KorisnikPostRepository kpr;

    public Korisnik getKorisnikByUserName(String username){
        if(kr.existsByUsername(username))
            return kr.getKorisnikByUsername(username);
        return null;
    }

    public Korisnik getKorisnikByMail(String mail){
        return kr.getKorisnikByMail(mail);
    }

    public List<Post> getKorisnikPost(Integer id){
        return kpr.getKorisnikPost(id);
    }

    public void save(Korisnik k){
        kr.save(k);
    }

    public void update(String mail, String novaLozinka){ kr.forgotPassword(mail, novaLozinka);}

}
