package org.example.risprojekatv2.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "komentar")
public class Komentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "komentar_id", nullable = false)
    private Long id;

    @Column(name = "userid")
    private Integer userid;

    @Column(name = "postid")
    private Long postid;

    @Lob
    @Column(name = "tekst")
    private String tekst;

    @Column(name = "timestamp")
    private Instant timestamp;

}