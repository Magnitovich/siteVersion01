package com.example.model;

import org.jvnet.hk2.config.Units;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "car")
public class CarsModel {
    @Column(name = "PHOTO")
    private String photo;
    @Id
    @Column(name = "IDCAR")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idCar;
    @Column(name = "NAME_CAR")
    private String name;

    @OneToOne
    @JoinColumn(name = "CAR_DESCRIPTION_ID")
    private CarDescrModel carDescrModel;

    public CarDescrModel getCarDescrModel() {
        return carDescrModel;
    }

    public Long getIdCar() {
        return idCar;
    }

    public void setIdCar(Long idCar) {
        this.idCar = idCar;
    }

    public void setCarDescrModel(CarDescrModel carDescrModel) {
        this.carDescrModel = carDescrModel;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
