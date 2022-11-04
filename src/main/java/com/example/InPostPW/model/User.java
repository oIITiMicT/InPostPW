package com.example.InPostPW.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="email")
    private String email;

    @Column(name="password_hash")
    @JsonIgnore
    private String password;

    @Column(name="salt")
    @JsonIgnore
    private String salt;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name="sender_id")
    @JsonIgnore
    private List<Package> sentPackages;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name="recipient_id")
    @JsonIgnore
    private List<Package> expectedPackages;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ToString.Exclude
    private Role role;

}