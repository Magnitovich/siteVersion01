package com.example.model;

import org.jvnet.hk2.config.Units;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "cars")
public class CarsModel {
    @Column
    private String photo;
    @Id
    @Column
    private String name_Cars;
    @Column
    private String descriptions;
    @Column
    private Integer quantity;
    @Column
    private BigDecimal price;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName_Cars() {
        return name_Cars;
    }

    public void setName_Cars(String name_Cars) {
        this.name_Cars = name_Cars;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
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
