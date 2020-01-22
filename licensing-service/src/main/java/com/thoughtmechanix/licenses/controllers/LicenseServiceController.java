package com.thoughtmechanix.licenses.controllers;

import com.thoughtmechanix.licenses.filters.UserContextHolder;
import com.thoughtmechanix.licenses.models.License;
import com.thoughtmechanix.licenses.services.LicenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {

    private static final Logger logger = LoggerFactory.getLogger(LicenseServiceController.class);

    @Autowired
    private LicenseService licenseService;

    @RequestMapping(method = RequestMethod.POST)
    public void createLicense(@RequestBody License license) {
        licenseService.saveLicense(license);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<License> getLicenses(@PathVariable("organizationId") String orgId) {
        logger.info("LicenseServiceController Correlation id: {}", UserContextHolder.getContext().getCorrelationId());

        return licenseService.getLicensesByOrg(orgId);
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
