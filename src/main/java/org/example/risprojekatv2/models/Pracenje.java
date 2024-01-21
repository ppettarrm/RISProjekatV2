package org.example.risprojekatv2.models;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "pracenje")
public class Pracenje {
    @EmbeddedId
    private PracenjeId id;

    @Column(name = "timestamp")
    private Instant timestamp;

}