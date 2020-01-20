package com.thoughtmechanix.organizations.controllers;

import com.thoughtmechanix.organizations.models.Organization;
import com.thoughtmechanix.organizations.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="v1/organizations")
public class OrganizationServiceController {

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(method = RequestMethod.POST)
    public void createOrganization(@RequestBody Organization org) {
        organizationService.createOrganization(org);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Organization getOrganization(@PathVariable("id") String orgId) {
        return organizationService.getOrganizationById(orgId);
    }
}
