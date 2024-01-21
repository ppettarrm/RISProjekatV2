package org.example.risprojekatv2.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "razgovor")
public class Razgovor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "razgovor_id", nullable = false)
    private Long id;

    @Column(name = "korisnik1_id")
    private Integer korisnik1Id;

    @Column(name = "korisnik2_id")
    private Integer korisnik2Id;

}