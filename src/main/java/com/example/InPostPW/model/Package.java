package com.example.InPostPW.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="packages")
public class Package {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="tracker")
    private String tracker;

    @Column(name="shipping_address")
    private String shippingAdress;

    @Column(name="destination_address")
    private String destinationAddress;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sender_id")
    @ToString.Exclude
    @JsonIgnore
    private User sender;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "recipient_id")
    @ToString.Exclude
    @JsonIgnore
    private User recipient;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name="package_id")
    @JsonIgnore
    private List<Stage> stages;
}
