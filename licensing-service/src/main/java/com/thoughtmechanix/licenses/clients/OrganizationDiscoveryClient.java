package com.thoughtmechanix.licenses.clients;

import com.thoughtmechanix.licenses.models.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component("organizationDiscoveryClient")
public class OrganizationDiscoveryClient implements OrganizationClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public Organization getOrganization(String organizationId) {
        RestTemplate restTemplate = new RestTemplate();

        List<ServiceInstance> instances = discoveryClient.getInstances("organizationservice");

        if (instances.isEmpty()) {
            return null;
        }

        String url = String.format("%s/v1/organizations/%s", instances.get(0).getUri().toString(), organizationId);

        return restTemplate.exchange(url, HttpMethod.GET, null, Organization.class, organizationId).getBody();
    }
}
