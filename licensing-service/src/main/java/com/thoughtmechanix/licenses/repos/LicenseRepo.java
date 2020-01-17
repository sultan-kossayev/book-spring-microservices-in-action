package com.thoughtmechanix.licenses.repos;

import com.thoughtmechanix.licenses.models.License;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenseRepo extends MongoRepository<License, String> {

    List<License> findByOrganizationId(String id);

    License findByOrganizationIdAndLicenseId(String organizationId, String licenseId);
}
