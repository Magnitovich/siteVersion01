package com.example.model;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.condition.ParamsRequestCondition;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;

public class YachtDTO {


    public MultipartFile getObjectPhotoYacht() {
        return objectPhotoYacht;
    }

    public void setObjectPhotoYacht(MultipartFile objectPhotoYacht) {
        this.objectPhotoYacht = objectPhotoYacht;
    }

    private MultipartFile objectPhotoYacht;
    private String photo;
    private String name;
    private String descriptions;
    private Integer number;
    private BigDecimal price;
    private Long idForEdit;

    private Long idYacht;

    public Long getIdYacht() {
        return idYacht;
    }

    public void setIdYacht(Long idYacht) {
        this.idYacht = idYacht;
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

    public Long getIdForEdit() {
        return idForEdit;
    }

    public void setIdForEdit(Long idForEdit) {
        this.idForEdit = idForEdit;
    }
}
