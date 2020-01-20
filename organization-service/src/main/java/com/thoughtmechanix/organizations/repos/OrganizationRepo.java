package com.thoughtmechanix.organizations.repos;

import com.thoughtmechanix.organizations.models.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrganizationRepo extends MongoRepository<Organization, String> {


}
