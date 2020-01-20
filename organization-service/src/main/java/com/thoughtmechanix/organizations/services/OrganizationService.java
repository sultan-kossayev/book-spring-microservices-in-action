package com.thoughtmechanix.organizations.services;

import com.thoughtmechanix.organizations.models.Organization;

public interface OrganizationService {

    void createOrganization(Organization org);

    Organization getOrganizationById(String id);
}
