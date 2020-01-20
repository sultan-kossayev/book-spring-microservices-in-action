package com.thoughtmechanix.licenses.services;

import com.thoughtmechanix.licenses.clients.OrganizationClient;
import com.thoughtmechanix.licenses.clients.OrganizationDiscoveryClient;
import com.thoughtmechanix.licenses.clients.OrganizationFeignClient;
import com.thoughtmechanix.licenses.config.ServiceConfig;
import com.thoughtmechanix.licenses.models.License;
import com.thoughtmechanix.licenses.models.Organization;
import com.thoughtmechanix.licenses.repos.LicenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultLicenseService implements LicenseService {

    @Autowired
    private LicenseRepo licenseRepo;

    @Autowired
    private ServiceConfig serviceConfig;

    @Autowired
    private OrganizationClient organizationDiscoveryClient;

    @Autowired
    private OrganizationClient organizationRestTemplateClient;

    @Autowired
    private OrganizationFeignClient organizationFeignClient;

    @Override
    public License getLicense(String organizationId, String licenseId) {
        return licenseRepo.findByOrganizationIdAndLicenseId(organizationId, licenseId);
    }

    @Override
    public License getLicense(String organizationId, String licenseId, String clientType) {
        License license = licenseRepo.findByOrganizationIdAndLicenseId(organizationId, licenseId);

        Organization org = retrieveOrgInfo(organizationId, clientType);

        return license
                .withOrganizationName(org.getName())
                .withContactName(org.getName())
                .withContactEmail(org.getContactEmail())
                .withContactPhone(org.getContactPhone());
    }

    private Organization retrieveOrgInfo(String organizationId, String clientType) {
        Organization org = null;

        if (clientType.equals("discovery")) {
            System.out.println("I am using the discovery client");
            return organizationDiscoveryClient.getOrganization(organizationId);
        } else if (clientType.equals("rest")) {
            System.out.println("I am using the rest template client");
            return organizationRestTemplateClient.getOrganization(organizationId);
        } else if (clientType.equals("feign")) {
            System.out.println("I am using feign client");
            return organizationFeignClient.getOrganization(organizationId);
        }

        return org;
    }

    @Override
    public void saveLicense(License license) {
        licenseRepo.save(license);
    }
}
