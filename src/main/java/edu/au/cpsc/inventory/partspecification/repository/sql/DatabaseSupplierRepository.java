package edu.au.cpsc.inventory.partspecification.repository.sql;

import edu.au.cpsc.inventory.partspecification.databaseaccess.Session;
import edu.au.cpsc.inventory.partspecification.databaseaccess.SupplierDao;
import edu.au.cpsc.inventory.partspecification.databaseaccess.SupplierDto;
import edu.au.cpsc.inventory.partspecification.entity.PartSpecification;
import edu.au.cpsc.inventory.partspecification.entity.Supplier;
import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
import edu.au.cpsc.inventory.partspecification.repository.caching.Cache;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of SupplierRepository that is backed by a database.
 */
public class DatabaseSupplierRepository implements SupplierRepository {

  private final Cache cache;
  private Session session;

  public DatabaseSupplierRepository(Session session) {
    this.cache = new Cache();
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
    List<Supplier> result = new ArrayList<>();
    for (Supplier ps : specs) {
      Supplier fromCache = (Supplier) cache.get(Supplier.class, ps.getId());
      if (fromCache == null) {
        result.add(ps);
        cache.insert(ps.getId(), ps);
      } else {
        result.add(fromCache);
      }
    }
    return result;
  }

  @Override
  public Supplier findOne(Long id) {
    Supplier result = (Supplier) cache.get(Supplier.class, id);
    if (result != null) {
      return result;
    }
    SupplierDao dao = new SupplierDao();
    SupplierDto dto = dao.selectOne(id, session);
    if (dto == null) {
      return null;
    }
    result = dtoToSupplier(dto);
    cache.insert(id, result);
    return result;
  }

  @Override
  public List<Supplier> findForPartSpecificationId(Long id) {
    SupplierDao dao = new SupplierDao();
    List<SupplierDto> dtos = dao.selectForPartSpecificationId(id, session);
    List<Supplier> specs = new ArrayList<>();
    for (SupplierDto dto : dtos) {
      Supplier s = dtoToSupplier(dto);
      specs.add(s);
    }
    List<Supplier> result = new ArrayList<>();
    for (Supplier ps : specs) {
      Supplier fromCache = (Supplier) cache.get(Supplier.class, ps.getId());
      if (fromCache == null) {
        result.add(ps);
        cache.insert(ps.getId(), ps);
      } else {
        result.add(fromCache);
      }
    }
    return result;
  }

  private Supplier dtoToSupplier(SupplierDto dto) {
    return new Supplier(dto.getId(), dto.getName());
  }

  private SupplierDto supplierToDto(Supplier supplier) {
    return new SupplierDto(supplier.getId(),
        supplier.getName());
  }


}
