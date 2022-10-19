package com.example.InPostPW.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="permissions")
public class Permission {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "roles_permissions",
            joinColumns = @JoinColumn(name = "permissions_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    @ToString.Exclude
    @JsonIgnore
    private List<Role> roles;
}
