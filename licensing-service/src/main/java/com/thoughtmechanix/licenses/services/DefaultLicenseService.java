package com.thoughtmechanix.licenses.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.thoughtmechanix.licenses.clients.OrganizationClient;
import com.thoughtmechanix.licenses.clients.OrganizationDiscoveryClient;
import com.thoughtmechanix.licenses.clients.OrganizationFeignClient;
import com.thoughtmechanix.licenses.config.ServiceConfig;
import com.thoughtmechanix.licenses.models.License;
import com.thoughtmechanix.licenses.models.Organization;
import com.thoughtmechanix.licenses.repos.LicenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @HystrixCommand
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

    @Override
    //@HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000")})
    @HystrixCommand(fallbackMethod = "buildFallbackLicenseList")
    public List<License> getLicensesByOrg(String organizationId) {
        randomlyRunLong();
        return licenseRepo.findByOrganizationId(organizationId);
    }

    private List<License> buildFallbackLicenseList(String organizationId){
        List<License> fallbackList = new ArrayList<>();
        License license = new License()
                .withId("0000000-00-00000")
                .withOrganizationId( organizationId )
                .withProductName("Sorry no licensing information currently available");

        fallbackList.add(license);
        return fallbackList;
    }

    private void randomlyRunLong() {
        Random rand = new Random();

        int randomNum = rand.nextInt(3) + 1;

        if (randomNum == 3) {
            try {
                Thread.sleep(11000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
