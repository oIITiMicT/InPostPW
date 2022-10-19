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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="email")
    private String email;

    @JsonIgnore
    @Column(name="password_hash")
    private String password;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "roles_id", referencedColumnName = "id")
    @ToString.Exclude
    private Role role;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name="sender_id")
    @JsonIgnore
    private List<Package> sendedPackages;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name="recipient_id")
    @JsonIgnore
    private List<Package> expectedPackages;

}
