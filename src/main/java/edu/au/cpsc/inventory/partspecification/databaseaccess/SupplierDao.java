package edu.au.cpsc.inventory.partspecification.databaseaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Database Access Object for the Suppliers table.  I implement database operations using * JDBC
 * calls.  My operations use a Session to interact with the database but they <b>do not</b> * engage
 * in transaction management.  It is my client's responsibility to manage transactions.
 */
public class SupplierDao {

  /**
   * Insert (if dto.getId() is null) or update (otherwise) into the Suppliers table
   *
   * @param dto     data transfer object
   * @param session database session
   * @return the id of the inserted or updated object.  If an insert was performed, this id will be
   *     the auto-generated id of the inserted row, otherwise it will be dto.getId().
   */
  public Long insertOrUpdate(SupplierDto dto, Session session) {
    try {
      if (dto.getId() == null) {
        return insert(dto, session);
      } else {
        return update(dto, session);
      }
    } catch (SQLException ex) {
      throw new DatabaseOperationFailed(ex);
    }
  }

  private Long update(SupplierDto dto, Session session) throws SQLException {
    try (PreparedStatement statement = session.prepareStatement(
        "UPDATE Suppliers SET name=? WHERE id=?",
        Statement.NO_GENERATED_KEYS)) {
      statement.setString(1, dto.getName());
      statement.setLong(2, dto.getId());
      statement.executeUpdate();
      return dto.getId();
    }
  }

  private long insert(SupplierDto dto, Session session) throws SQLException {
    try (PreparedStatement statement = session.prepareStatement(
        "INSERT INTO Suppliers(name) VALUES (?)",
        Statement.RETURN_GENERATED_KEYS)) {
      statement.setString(1, dto.getName());
      statement.executeUpdate();
      try (ResultSet rs = statement.getGeneratedKeys()) {
        if (!rs.next()) {
          throw new DatabaseOperationFailed("Failed to get id for new supplier");
        }
        return rs.getLong(1);
      }
    }
  }

  /**
   * Fetch a single row from the Suppliers table.
   *
   * @param id      id of the row to fetch
   * @param session database session
   * @return the row or null if there is no row with the specified id
   */
  public SupplierDto selectOne(Long id, Session session) {
    try (
        PreparedStatement statement = session.prepareStatement(
            "SELECT id, name FROM Suppliers where id=?",
            Statement.NO_GENERATED_KEYS)) {
      statement.setLong(1, id);
      try (ResultSet rs = statement.executeQuery()) {
        if (rs.next()) {
          return new SupplierDto(rs.getLong(1), rs.getString(2));
        }
        return null;
      }
    } catch (SQLException ex) {
      throw new DatabaseOperationFailed(ex);
    }
  }

  /**
   * Fetch all rows from the Suppliers table.
   *
   * @param session the database session
   * @return a list of all of the rows.
   */
  public List<SupplierDto> selectAll(Session session) {
    try (PreparedStatement statement = session.prepareStatement(
        "SELECT id, name FROM Suppliers",
        Statement.NO_GENERATED_KEYS)) {
      try (ResultSet rs = statement.executeQuery()) {
        List<SupplierDto> result = new ArrayList<>();
        while (rs.next()) {
          result.add(new SupplierDto(rs.getLong(1), rs.getString(2)));
        }
        return result;
      }
    } catch (SQLException ex) {
      throw new DatabaseOperationFailed(ex);
    }
  }

  /**
   * Fetch all rows from Suppliers that are joined to the specified part specification id using the
   * join table.
   *
   * @param id      of the PartSpecification whose suppliers we need
   * @param session database session
   * @return list of SupplierDto's that correspond to this part specification
   */
  public List<SupplierDto> selectForPartSpecificationId(Long id, Session session) {

    try (PreparedStatement statement = session.prepareStatement(
        "SELECT Suppliers.id, Suppliers.name FROM Suppliers "
            + "INNER JOIN PartSpecificationsToSuppliers "
            + "ON Suppliers.id = PartSpecificationsToSuppliers.supplierId "
            + "WHERE partSpecificationId=?",
        Statement.NO_GENERATED_KEYS)) {
      statement.setLong(1, id);
      try (ResultSet rs = statement.executeQuery()) {
        List<SupplierDto> result = new ArrayList<>();
        while (rs.next()) {
          result.add(new SupplierDto(rs.getLong(1), rs.getString(2)));
        }
        return result;
      }
    } catch (SQLException ex) {
      throw new DatabaseOperationFailed(ex);
    }
  }
}
