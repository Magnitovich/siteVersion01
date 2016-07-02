package com.example.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name ="whisky")
public class WhiskeyModel {

    @Column
    private String photo;
    @Id
    // @GeneratedValue(strategy=GenerationType.IDENTITY) эта строка указывает хибернейту,
//    что ID будет генерится
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "NAME")
    private String nameWhisky;
    @Column(name = "DESCRIPTION")
    private String describeWhisky;
    @Column(name = "QUANTITY")
    private Integer quantityWhisky;
    @Column
    private BigDecimal price;

    @Override
    public String toString() {
        return "WhiskeyModel{" +
                "photo='" + photo + '\'' +
                ", nameWhisky='" + nameWhisky + '\'' +
                ", describeWhisky='" + describeWhisky + '\'' +
                ", quantityWhisky=" + quantityWhisky +
                ", price=" + price +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNameWhisky() {
        return nameWhisky;
    }

    public void setNameWhisky(String nameWhisky) {
        this.nameWhisky = nameWhisky;
    }

    public String getDescribeWhisky() {
        return describeWhisky;
    }

    public void setDescribeWhisky(String describeWhisky) {
        this.describeWhisky = describeWhisky;
    }

    public Integer getQuantityWhisky() {
        return quantityWhisky;
    }

    public void setQuantityWhisky(Integer quantityWhisky) {
        this.quantityWhisky = quantityWhisky;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
