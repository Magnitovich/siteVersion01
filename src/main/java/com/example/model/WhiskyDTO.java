package com.example.model;


import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;

public class WhiskyDTO {

    private String idForEdit;

    private MultipartFile fileObject;
    private String photo;
    private String nameWhisky;
    private String describeWhisky;
    private Integer quantityWhisky;
    private BigDecimal price;

    public String getIdForEdit() {
        return idForEdit;
    }

    public void setIdForEdit(String idForEdit) {
        this.idForEdit = idForEdit;
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

    public MultipartFile getFileObject() {
        return fileObject;
    }

    public void setFileObject(MultipartFile file) {
        this.fileObject = file;
    }
}
