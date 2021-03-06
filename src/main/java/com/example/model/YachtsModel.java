package com.example.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name ="yachts" )
public class YachtsModel {
    @Column
    private String photo;

    @Id
    @Column(name = "IDYACHT")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idYacht;
    @Column
    private String name;
    @Column
    private String descriptions;

    @Column(name = "quantity")
    private Integer number;
    @Column
    private BigDecimal price;

    public Long getIdYacht() {
        return idYacht;
    }

    public void setIdYacht(Long idYacht) {
        this.idYacht = idYacht;
    }

    @Override
    public String toString() {
        return "YachtsModel{" +
                "photo='" + photo + '\'' +
                ", name='" + name + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", number=" + number +
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
