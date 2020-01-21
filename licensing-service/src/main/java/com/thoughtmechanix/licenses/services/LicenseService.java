package com.thoughtmechanix.licenses.services;

import com.thoughtmechanix.licenses.models.License;

import java.util.List;

public interface LicenseService {

    License getLicense(String organizationId, String licenseId);

    License getLicense(String organizationId, String licenseId, String clientType);

    void saveLicense(License license);

    List<License> getLicensesByOrg(String organizationId);
}
