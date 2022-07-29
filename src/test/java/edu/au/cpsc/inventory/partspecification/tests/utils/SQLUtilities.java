package edu.au.cpsc.inventory.partspecification.tests.utils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLUtilities {

  public static void executeSqlFile(
      String s, Connection c)
      throws IOException, SQLException {
    URL url = SQLUtilities.class.getResource(s);
    try (var stream = url.openStream()) {
      byte[] bytes = stream.readAllBytes();
      String fileContents = new String(bytes, StandardCharsets.UTF_8);
      for (var statementString : fileContents.split(";")) {
        if (!statementString.isBlank()) {
          try (Statement statement = c.createStatement()) {
            statement.execute(statementString);
          }
        }
      }
    }
  }
}
