package edu.au.cpsc.inventory.partspecification.repository.sql;

import edu.au.cpsc.inventory.partspecification.dataaccess.Session;
import edu.au.cpsc.inventory.partspecification.dataaccess.SupplierDao;
import edu.au.cpsc.inventory.partspecification.dataaccess.SupplierDto;
import edu.au.cpsc.inventory.partspecification.entity.Supplier;
import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
import java.util.ArrayList;
import java.util.List;

public class SQLSupplierRepository implements SupplierRepository {

  private Session session;

  public SQLSupplierRepository(Session session) {
    this.session = session;
  }

  @Override
  public Long save(Supplier supplier) {
    var dto = supplierToDto(supplier);
    var dao = new SupplierDao();
    Long id = dao.insertOrUpdate(dto, session);
    supplier.setId(id);
    session.commit();
    return id;
  }

  @Override
  public List<Supplier> findAll() {
    SupplierDao dao = new SupplierDao();
    List<SupplierDto> dtos = dao.selectAll(session);
    List<Supplier> specs = new ArrayList<>();
    for (SupplierDto dto : dtos) {
      Supplier ps = dtoToSupplier(dto);
      specs.add(ps);
    }
    return specs;
  }

  @Override
  public Supplier findOne(Long id) {
    SupplierDao dao = new SupplierDao();
    SupplierDto dto = dao.selectOne(id, session);
    if (dto == null) {
      return null;
    }
    Supplier Supplier = dtoToSupplier(dto);
    return Supplier;
  }

  private Supplier dtoToSupplier(SupplierDto dto) {
    return new Supplier(dto.getId(), dto.getName());
  }

  private SupplierDto supplierToDto(Supplier supplier) {
    return new SupplierDto(supplier.getId(), supplier.getName());
  }
}
