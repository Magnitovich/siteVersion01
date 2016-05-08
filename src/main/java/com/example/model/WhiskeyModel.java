package com.example.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="whisky")
public class WhiskeyModel {

    @Column
    private String photo;
    @Id
    @Column
    private String nameWhisky;


}
