package edu.au.cpsc.inventory.partspecification.repository.sql;

import edu.au.cpsc.inventory.partspecification.databaseaccess.Session;
import edu.au.cpsc.inventory.partspecification.databaseaccess.SupplierDao;
import edu.au.cpsc.inventory.partspecification.databaseaccess.SupplierDto;
import edu.au.cpsc.inventory.partspecification.entity.Supplier;
import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of SupplierRepository that is backed by a database.
 */
public class DatabaseSupplierRepository implements SupplierRepository {

  private Session session;

  public DatabaseSupplierRepository(Session session) {
    this.session = session;
  }

  @Override
  public Long save(Supplier supplier) {
    SupplierDto dto = supplierToDto(supplier);
    SupplierDao dao = new SupplierDao();
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
      Supplier s = dtoToSupplier(dto);
      specs.add(s);
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
    Supplier supplier = dtoToSupplier(dto);
    return supplier;
  }

  private Supplier dtoToSupplier(SupplierDto dto) {
    return new Supplier(dto.getId(), dto.getName());
  }

  private SupplierDto supplierToDto(Supplier supplier) {
    return new SupplierDto(supplier.getId(),
        supplier.getName());
  }
}
