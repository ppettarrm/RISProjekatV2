package org.example.risprojekatv2.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postid", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userid")
    private Korisnik korisnik;

    @Column(name = "slika")
    private byte[] slika;

    @Lob
    @Column(name = "opis")
    private String opis;

    @Column(name = "timestamp")
    private Instant timestamp;

}