package com.thoughtmechanix.licenses.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "licenses")
public class License {

    @Id
    private String licenseId;
    private String organizationId;
    private String productName;
    private String licenseType;
    private String comment;

    public License withOrganizationId(String id) {
        setOrganizationId(id);

        return this;
    }

    public License withProductName(String name) {
        setProductName(name);

        return this;
    }

    public License withLicenseType(String type) {
        setLicenseType(type);

        return this;
    }

    public License withComment(String comment) {
        setComment(comment);

        return this;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "License{" +
                "licenseId='" + licenseId + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", productName='" + productName + '\'' +
                ", licenseType='" + licenseType + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
