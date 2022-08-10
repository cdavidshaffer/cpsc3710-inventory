package edu.au.cpsc.inventory.partspecification.repository.sql;

import edu.au.cpsc.inventory.partspecification.dataaccess.PartSpecificationDao;
import edu.au.cpsc.inventory.partspecification.dataaccess.PartSpecificationDto;
import edu.au.cpsc.inventory.partspecification.dataaccess.Session;
import edu.au.cpsc.inventory.partspecification.entity.PartSpecification;
import edu.au.cpsc.inventory.partspecification.repository.PartSpecificationRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of PartSpecificationRepository that uses SQL-based Database Access Objects to
 * fetch and store part specifications in a database.
 */
public class SQLPartSpecificationRepository implements PartSpecificationRepository {

  private Session session;

  public SQLPartSpecificationRepository(Session session) {
    this.session = session;
  }

  @Override
  public Long save(PartSpecification partSpecification) {
    PartSpecificationDto dto = partSpecificationToDto(partSpecification);
    PartSpecificationDao dao = new PartSpecificationDao();
    Long id = dao.insertOrUpdate(dto, session);
    partSpecification.setId(id);
    session.commit();
    return id;
  }

  @Override
  public List<PartSpecification> findAll() {
    PartSpecificationDao dao = new PartSpecificationDao();
    List<PartSpecificationDto> dtos = dao.selectAll(session);
    List<PartSpecification> specs = new ArrayList<>();
    for (PartSpecificationDto dto : dtos) {
      PartSpecification ps = dtoToPartSpecification(dto);
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
    PartSpecification partSpecification = dtoToPartSpecification(dto);
    return partSpecification;
  }

  private PartSpecification dtoToPartSpecification(PartSpecificationDto dto) {
    return new PartSpecification(dto.getId(), dto.getName(),
        dto.getDescription());
  }

  private PartSpecificationDto partSpecificationToDto(PartSpecification partSpecification) {
    return new PartSpecificationDto(partSpecification.getId(),
        partSpecification.getName(),
        partSpecification.getDescription());
  }
}
