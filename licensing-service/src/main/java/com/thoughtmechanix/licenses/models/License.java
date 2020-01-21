package com.thoughtmechanix.licenses.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "licenses")
public class License {

    @Id
    private String licenseId;
    private String organizationId;
    private String productName;
    private String licenseType;
    private String comment;

    @Transient
    private String organizationName;
    @Transient
    private String contactName;
    @Transient
    private String contactEmail;
    @Transient
    private String contactPhone;

    public License withId(String id) {
        setLicenseId(id);

        return this;
    }

    public License withOrganizationId(String orgId) {
        setOrganizationId(orgId);

        return this;
    }

    public License withOrganizationName(String name) {
        setOrganizationName(name);

        return this;
    }

    public License withContactName(String name) {
        setContactName(name);

        return this;
    }

    public License withContactEmail(String email) {
        setContactEmail(email);

        return this;
    }

    public License withContactPhone(String phone) {
        setContactPhone(phone);

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

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
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
