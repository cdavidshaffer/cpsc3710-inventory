package edu.au.cpsc.inventory.partspecification.dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PartSpecificationDao {

  public Long insertOrUpdate(PartSpecificationDto dto, Session session) {
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

  private Long update(PartSpecificationDto dto, Session session) throws SQLException {
    try (PreparedStatement statement = session.prepareStatement(
        "UPDATE PartSpecifications SET name=?, description=? WHERE id=?",
        Statement.NO_GENERATED_KEYS)) {
      statement.setString(1, dto.getName());
      statement.setString(2, dto.getDescription());
      statement.setLong(3, dto.getId());
      statement.executeUpdate();
      return dto.getId();
    }
  }

  private long insert(PartSpecificationDto dto, Session session) throws SQLException {
    try (PreparedStatement statement = session.prepareStatement(
        "INSERT INTO PartSpecifications(name, description) VALUES (?, ?)",
        Statement.RETURN_GENERATED_KEYS)) {
      statement.setString(1, dto.getName());
      statement.setString(2, dto.getDescription());
      statement.executeUpdate();
      try (ResultSet rs = statement.getGeneratedKeys()) {
        if (!rs.next()) {
          throw new DatabaseOperationFailed("Failed to get id for new part specification");
        }
        return rs.getLong(1);
      }
    }
  }

  public List<PartSpecificationDto> selectAll(Session session) {
    try (PreparedStatement statement = session.prepareStatement(
        "SELECT id, name, description FROM PartSpecifications",
        Statement.NO_GENERATED_KEYS)) {
      try (ResultSet rs = statement.executeQuery()) {
        List<PartSpecificationDto> result = new ArrayList<>();
        while (rs.next()) {
          result.add(new PartSpecificationDto(rs.getLong(1), rs.getString(2),
              rs.getString(3)));
        }
        return result;
      }
    } catch (SQLException ex) {
      throw new DatabaseOperationFailed(ex);
    }
  }

  public PartSpecificationDto selectOne(Long id, Session session) {
    try (
        PreparedStatement statement = session.prepareStatement(
            "SELECT id, name, description FROM PartSpecifications where id=?",
            Statement.NO_GENERATED_KEYS)) {
      statement.setLong(1, id);
      try (ResultSet rs = statement.executeQuery()) {
        if (rs.next()) {
          return new PartSpecificationDto(rs.getLong(1), rs.getString(2),
              rs.getString(3));
        }
        return null;
      }
    } catch (SQLException ex) {
      throw new DatabaseOperationFailed(ex);
    }
  }
}
