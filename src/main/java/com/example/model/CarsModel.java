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
    @Column(name = "name_Cars")
    private String name;
    @Column
    private String descriptions;
    @Column
    private Integer quantity;
    @Column
    private BigDecimal price;



    @Override
    public String toString() {
        return "CarsModel{" +
                "photo='" + photo + '\'' +
                ", name='" + name + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
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
