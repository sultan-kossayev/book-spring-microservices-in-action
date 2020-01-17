package com.thoughtmechanix.licenses.services;

import com.thoughtmechanix.licenses.models.License;

public interface LicenseService {

    License getLicense(String organizationId, String licenseId);

    void saveLicense(License license);
}
