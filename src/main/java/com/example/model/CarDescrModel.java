package com.example.model;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "car_description")
public class CarDescrModel {

    @Column(name = "DESCRIPTIOON")
    private String description;
    @Column(name = "QUANTITY")
    private Integer quantity;
        @Column(name = "PRICE")
    private BigDecimal price;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "carDescrModel")
    private CarsModel carsModel;

    public CarsModel getCarsModel() {
        return carsModel;
    }

    public void setCarsModel(CarsModel carsModel) {
        this.carsModel = carsModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
