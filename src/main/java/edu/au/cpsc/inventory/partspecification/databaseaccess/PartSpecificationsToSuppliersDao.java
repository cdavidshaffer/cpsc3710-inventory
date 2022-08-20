package edu.au.cpsc.inventory.partspecification.databaseaccess;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Database Access Object for the PartSpecificationsToSuppliers table.  I implement database
 * operations using JDBC calls.  My operations use a Session to interact with the database but they
 * <b>do not</b> engage in transaction management.  It is my client's responsibility to manage
 * transactions.
 *
 * <p>Note that this table does not have a primary key so not all operations are supported.
 */
public class PartSpecificationsToSuppliersDao {

  /**
   * Insert into the PartSpecificationsToSuppliers table.
   *
   * @param dto     data transfer object
   * @param session database session
   * @return the id of the inserted or updated object.  If an insert was performed, this id will be
   *     the auto-generated id of the inserted row, otherwise it will be dto.getId().
   */
  public Long insertOrUpdate(PartSpecificationsToSuppliersDto dto, Session session) {
    try {
      return insert(dto, session);
    } catch (SQLException ex) {
      throw new DatabaseOperationFailed(ex);
    }
  }


  private Long insert(PartSpecificationsToSuppliersDto dto, Session session) throws SQLException {
    try (PreparedStatement statement = session.prepareStatement(
        "INSERT INTO PartSpecifications_Suppliers(partSpecificationId, supplierId) VALUES (?, ?)",
        Statement.NO_GENERATED_KEYS)) {
      statement.setLong(1, dto.getPartSpecificationId());
      statement.setLong(2, dto.getSupplierId());
      statement.executeUpdate();
    }
    return null;
  }

}
