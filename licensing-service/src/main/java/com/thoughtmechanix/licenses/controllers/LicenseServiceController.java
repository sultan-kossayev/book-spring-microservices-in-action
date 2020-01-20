package com.thoughtmechanix.licenses.controllers;

import com.thoughtmechanix.licenses.models.License;
import com.thoughtmechanix.licenses.services.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
@RequestMapping("/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {

    @Autowired
    private LicenseService licenseService;

    @RequestMapping(method = RequestMethod.POST)
    public void createLicense(@RequestBody License license) {
        licenseService.saveLicense(license);
    }

    @RequestMapping(value = "/{licenseId}", method = RequestMethod.GET)
    public License getLicense(@PathVariable("organizationId") String organizationId, @PathVariable("licenseId") String licenseId) {
        License found = licenseService.getLicense(organizationId, licenseId);

        if (Objects.isNull(found)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "license not found");
        }

        return found;
    }

    @RequestMapping(value = "/{licenseId}/{clientType}", method = RequestMethod.GET)
    public License getLicenseByClient(@PathVariable("organizationId") String organizationId,
                                      @PathVariable("licenseId") String licenseId,
                                      @PathVariable("clientType") String clientType) {
        License license = licenseService.getLicense(organizationId, licenseId, clientType);

        if (Objects.isNull(license)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "license not found");
        }

        return license;
    }
}
