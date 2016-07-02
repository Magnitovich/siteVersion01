package com.example.model;


import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class CarsDTO {

    private MultipartFile objectPhotoCar;

    private String photo;

    private String name;

    private String descriptions;

    private Integer quantity;

    private BigDecimal price;

    private Long idForEditAdd;

    private Long idCar;

    public Long getIdCar() {
        return idCar;
    }

    public void setIdCar(Long idCar) {
        this.idCar = idCar;
    }

    public MultipartFile getObjectPhotoCar() {
        return objectPhotoCar;
    }

    public void setObjectPhotoCar(MultipartFile objectPhotoCar) {
        this.objectPhotoCar = objectPhotoCar;
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

    public Long getIdForEditAdd() {
        return idForEditAdd;
    }

    public void setIdForEditAdd(Long idForEditAdd) {
        this.idForEditAdd = idForEditAdd;
    }
}
