package edu.au.cpsc.inventory.partspecification.repository.sql;

import edu.au.cpsc.inventory.partspecification.databaseaccess.PartSpecificationDao;
import edu.au.cpsc.inventory.partspecification.databaseaccess.PartSpecificationDto;
import edu.au.cpsc.inventory.partspecification.databaseaccess.PartSpecificationsToSuppliersDao;
import edu.au.cpsc.inventory.partspecification.databaseaccess.PartSpecificationsToSuppliersDto;
import edu.au.cpsc.inventory.partspecification.databaseaccess.Session;
import edu.au.cpsc.inventory.partspecification.entity.PartSpecification;
import edu.au.cpsc.inventory.partspecification.entity.Supplier;
import edu.au.cpsc.inventory.partspecification.repository.PartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of PartSpecificationRepository that uses SQL-based Database Access Objects to
 * fetch and store part specifications in a database.
 */
public class DatabasePartSpecificationRepository implements PartSpecificationRepository {

  private Session session;
  private SupplierRepository supplierRepository;

  public DatabasePartSpecificationRepository(Session session,
      SupplierRepository supplierRepository) {
    this.session = session;
    this.supplierRepository = supplierRepository;
  }

  @Override
  public Long save(PartSpecification partSpecification) {
    PartSpecificationDto dto = partSpecificationToDto(partSpecification);
    PartSpecificationDao dao = new PartSpecificationDao();
    Long id = dao.insertOrUpdate(dto, session);
    partSpecification.setId(id);
    for (Supplier s : partSpecification.getSuppliers()) {
      var joinTableDto = new PartSpecificationsToSuppliersDto(partSpecification.getId(), s.getId());
      PartSpecificationsToSuppliersDao joinTableDao = new PartSpecificationsToSuppliersDao();
      joinTableDao.insertOrUpdate(joinTableDto, session);
    }
    session.commit();
    return id;
  }

  @Override
  public List<PartSpecification> findAll() {
    PartSpecificationDao dao = new PartSpecificationDao();
    List<PartSpecificationDto> dtos = dao.selectAll(session);
    List<PartSpecification> specs = new ArrayList<>();
    for (PartSpecificationDto dto : dtos) {
      List<Supplier> suppliers = supplierRepository.findForPartSpecificationId(dto.getId());
      PartSpecification ps = dtoToPartSpecification(dto, suppliers);
      specs.add(ps);
    }
    return specs;
  }

  @Override
  public PartSpecification findOne(Long id) {
    PartSpecificationDao dao = new PartSpecificationDao();
    PartSpecificationDto dto = dao.selectOne(id, session);
    if (dto == null) {
      return null;
    }
    List<Supplier> suppliers = supplierRepository.findForPartSpecificationId(id);
    PartSpecification partSpecification = dtoToPartSpecification(dto, suppliers);
    return partSpecification;
  }

  private PartSpecification dtoToPartSpecification(PartSpecificationDto dto,
      List<Supplier> suppliers) {
    return new PartSpecification(dto.getId(), dto.getName(),
        dto.getDescription(), suppliers);
  }

  private PartSpecificationDto partSpecificationToDto(PartSpecification partSpecification) {
    return new PartSpecificationDto(partSpecification.getId(),
        partSpecification.getName(),
        partSpecification.getDescription());
  }
}
