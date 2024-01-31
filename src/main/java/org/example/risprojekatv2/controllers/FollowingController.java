package org.example.risprojekatv2.controllers;

import org.example.risprojekatv2.models.Korisnik;
import org.example.risprojekatv2.models.Pracenje;
import org.example.risprojekatv2.models.PracenjeId;
import org.example.risprojekatv2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;

@Controller
@RequestMapping("/follow")
public class FollowingController {

    @Autowired
    UserService userService;

    @PostMapping("")
    public void follow(@RequestParam("username")String toFollow){
        Korisnik toFollowUser = userService.getKorisnikByUserName(toFollow);
        Korisnik user = userService.getKorisnikByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        if(toFollowUser.getId() == user.getId())
            return;
        Pracenje exists = userService.getPracenje(user.getId(), toFollowUser.getId());
        if(exists == null){
            PracenjeId pracenjeId = new PracenjeId();
            pracenjeId.setPratiocId(user.getId());
            pracenjeId.setPratilacId(toFollowUser.getId());

            Pracenje p = new Pracenje();
            p.setId(pracenjeId);
            p.setTimestamp(Instant.now());

            userService.savePracenje(p);
            return;
        }

        userService.deletePracenje(exists);
    }

}
