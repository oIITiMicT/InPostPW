package com.example.InPostPW.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "stages")
public class Stage {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "time")
    private Date time;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "package_id")
    @ToString.Exclude
    private Package parcel;
}
