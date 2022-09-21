package edu.au.cpsc.inventory.partspecification.repository.spring;

import edu.au.cpsc.inventory.partspecification.entity.PartSpecification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring data part specification repository but does not implement our PartSpecificationRepository
 * interface. See {@link SpringDataPartSpecificationRepository} for an adapter class.
 */
@Repository
public interface SpringDataPartSpecificationCrudRepository extends
    CrudRepository<PartSpecification, Long> {

}
