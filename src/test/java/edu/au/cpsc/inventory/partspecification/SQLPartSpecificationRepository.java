package edu.au.cpsc.inventory.partspecification;

import edu.au.cpsc.inventory.partspecification.entity.PartSpecification;
import edu.au.cpsc.inventory.partspecification.repository.PartSpecificationRepository;
import java.util.ArrayList;
import java.util.List;

public class SQLPartSpecificationRepository implements PartSpecificationRepository {

  private Session session;

  public SQLPartSpecificationRepository(Session session) {
    this.session = session;
  }

  @Override
  public Long save(PartSpecification entity) {
    PartSpecificationDto dto = new PartSpecificationDto(entity.getId(), entity.getName(),
        entity.getDescription());
    PartSpecificationDao dao = new PartSpecificationDao();
    return dao.insertOrUpdate(dto, session);
  }

  @Override
  public List<PartSpecification> findAll() {
    PartSpecificationDao dao = new PartSpecificationDao();
    List<PartSpecificationDto> dtos = dao.selectAll(session);
    List<PartSpecification> specs = new ArrayList<>();
    for (PartSpecificationDto dto : dtos) {
      specs.add(new PartSpecification());
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
    return new PartSpecification();
  }
}
