package org.example.risprojekatv2.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "poruka")
public class Poruka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageid", nullable = false)
    private Long id;

    @Column(name = "razgovor_id")
    private Long razgovorId;

    @Column(name = "korisnik_id")
    private Integer korisnikId;

    @Lob
    @Column(name = "text")
    private String text;

    @Column(name = "timestamp")
    private Instant timestamp;

}