package com.thoughtmechanix.organizations.services;

import com.thoughtmechanix.organizations.models.Organization;
import com.thoughtmechanix.organizations.repos.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultOrganizationService implements OrganizationService {

    @Autowired
    OrganizationRepo organizationRepo;

    @Override
    public void createOrganization(Organization org) {
        organizationRepo.save(org);
    }

    @Override
    public Organization getOrganizationById(String id) {
        return organizationRepo.findById(id).get();
    }
}
