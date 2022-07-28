package edu.au.cpsc.inventory.partspecification;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PartSpecificationDao {

  public Long insertOrUpdate(PartSpecificationDto dto, Session session) {
    try {
      PreparedStatement statement = session.prepareStatement(
          "INSERT INTO PartSpecifications(name, description) VALUES (?, ?)",
          Statement.RETURN_GENERATED_KEYS);
      statement.setString(1, dto.getName());
      statement.setString(2, dto.getDescription());
      statement.executeUpdate();
      ResultSet rs = statement.getGeneratedKeys();
      if (!rs.next()) {
        throw new DatabaseOperationFailed("Failed to get id for new part specification");
      }
      return rs.getLong(1);
    } catch (SQLException ex) {
      throw new DatabaseOperationFailed(ex);
    }
  }

  public List<PartSpecificationDto> selectAll(Session session) {
    try {
      PreparedStatement statement = session.prepareStatement(
          "SELECT id, name, description FROM PartSpecifications",
          Statement.NO_GENERATED_KEYS);
      ResultSet rs = statement.executeQuery();
      List<PartSpecificationDto> result = new ArrayList<>();
      while (rs.next()) {
        result.add(new PartSpecificationDto(rs.getLong(1), rs.getString(1),
            rs.getString(3)));
      }
      return result;
    } catch (SQLException ex) {
      throw new DatabaseOperationFailed(ex);
    }
  }

  public PartSpecificationDto selectOne(Long id, Session session) {
    try {
      PreparedStatement statement = session.prepareStatement(
          "SELECT id, name, description FROM PartSpecifications where id=?",
          Statement.NO_GENERATED_KEYS);
      statement.setLong(1, id);
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        return new PartSpecificationDto(rs.getLong(1), rs.getString(1),
            rs.getString(3));
      }
      return null;
    } catch (SQLException ex) {
      throw new DatabaseOperationFailed(ex);
    }
  }
}
