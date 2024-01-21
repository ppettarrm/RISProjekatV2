package org.example.risprojekatv2.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "savedpost")
public class Savedpost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "savedpost_id", nullable = false)
    private Long id;

    @Column(name = "userid")
    private Integer userid;

    @Column(name = "postid")
    private Long postid;

}