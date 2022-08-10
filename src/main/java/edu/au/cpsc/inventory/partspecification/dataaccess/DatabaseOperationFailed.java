package edu.au.cpsc.inventory.partspecification.dataaccess;

import java.sql.SQLException;

/**
 * I am thrown when a database operation fails.
 */
public class DatabaseOperationFailed extends RuntimeException {

  public DatabaseOperationFailed(SQLException ex) {
    super(ex);
  }

  public DatabaseOperationFailed(String s) {
    super(s);
  }
}
