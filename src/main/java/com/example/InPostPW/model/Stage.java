package com.example.InPostPW.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
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
