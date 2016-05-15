package com.example.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;

public class YachtDTO {

    private String photo;
    private String name;
    private String descriptions;
    private Integer number;
    private BigDecimal price;
    private String idForEdit;

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

    public String getIdForEdit() {
        return idForEdit;
    }

    public void setIdForEdit(String idForEdit) {
        this.idForEdit = idForEdit;
    }
}
