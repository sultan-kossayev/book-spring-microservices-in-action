package com.thoughtmechanix.licenses.clients;

import com.thoughtmechanix.licenses.models.Organization;

public interface OrganizationClient {

    Organization getOrganization(String organizationId);
}
