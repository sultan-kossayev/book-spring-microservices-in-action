package com.thoughtmechanix.licenses.models;

public class License {

    private String id;
    private String organizationId;
    private String productName;
    private String licenseType;

    public License(String id) {
        this.id = id;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
