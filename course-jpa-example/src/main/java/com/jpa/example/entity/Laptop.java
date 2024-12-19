package com.jpa.example.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "jpa_laptops")
public class Laptop {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "user_id_seq")
    private String id;

    private String model;
    private String about;

    @ManyToOne(fetch = FetchType.EAGER)

    //@OneToOne(mappedBy = )
    @JoinColumn(name = "user_id")
    private User user;

    //user
//    @OneToOne(fetch = FetchType.LAZY)
//    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
