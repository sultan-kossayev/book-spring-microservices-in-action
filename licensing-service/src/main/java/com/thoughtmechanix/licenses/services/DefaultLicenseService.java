package com.thoughtmechanix.licenses.services;

import com.thoughtmechanix.licenses.config.ServiceConfig;
import com.thoughtmechanix.licenses.models.License;
import com.thoughtmechanix.licenses.repos.LicenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultLicenseService implements LicenseService {

    @Autowired
    private LicenseRepo licenseRepo;

    @Autowired
    private ServiceConfig serviceConfig;

    @Override
    public License getLicense(String organizationId, String licenseId) {
        License license = licenseRepo.findByOrganizationIdAndLicenseId(organizationId, licenseId);

        return license == null? null : license.withComment(serviceConfig.getExampleProperty());
    }

    @Override
    public void saveLicense(License license) {
        licenseRepo.save(license);
    }
}
