package edu.au.cpsc.inventory.partspecification.dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupplierDao {

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
          throw new DatabaseOperationFailed("Failed to get id for new part specification");
        }
        return rs.getLong(1);
      }
    }
  }


  public List<SupplierDto> selectAll(Session session) {
    {
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
  }

  public SupplierDto selectOne(Long id, Session session) {
    {
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
  }
}
