package com.example.model;


import java.math.BigDecimal;

public class CarsDTO {

    private String photo;

    private String name;

    private String descriptions;

    private Integer quantity;

    private BigDecimal price;

    private String idForEditAdd;

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

    public String getIdForEditAdd() {
        return idForEditAdd;
    }

    public void setIdForEditAdd(String idForEditAdd) {
        this.idForEditAdd = idForEditAdd;
    }
}