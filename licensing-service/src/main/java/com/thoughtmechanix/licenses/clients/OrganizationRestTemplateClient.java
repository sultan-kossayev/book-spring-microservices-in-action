package com.thoughtmechanix.licenses.clients;

import com.thoughtmechanix.licenses.models.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("organizationRestTemplateClient")
public class OrganizationRestTemplateClient implements OrganizationClient {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Organization getOrganization(String organizationId) {
        return restTemplate.exchange("http://organizationservice/v1/organizations/{organizationId}",
                HttpMethod.GET, null, Organization.class, organizationId).getBody();
    }
}
