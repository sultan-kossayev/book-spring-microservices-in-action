package com.thoughtmechanix.licenses.clients;

import com.thoughtmechanix.licenses.models.Organization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component("organizationFeignClient")
@FeignClient("organizationService")
public interface OrganizationFeignClient extends OrganizationClient {

    @Override
    @RequestMapping(method = RequestMethod.GET,
            value = "/v1/organizations/{organizationId}",
            consumes = "application/json")
    Organization getOrganization(@PathVariable("organizationId") String organizationId);
}
