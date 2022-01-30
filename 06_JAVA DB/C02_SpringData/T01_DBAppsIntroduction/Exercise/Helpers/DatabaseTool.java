package T01_DBAppsIntroduction.Exercise.Helpers;

import java.sql.*;
import java.util.Properties;

public class DatabaseTool {
    private final String ADDRESS = "jdbc:mysql://localhost:3306";
    private final String TARGET_DATABASE_NAME = "minions_db";
    private Connection connection;

    public DatabaseTool(Properties credentials) throws SQLException {
        this.connection = setConnection(credentials);
    }

    private Connection setConnection(Properties credentials) throws SQLException {
        return DriverManager.getConnection(this.ADDRESS + "/" + this.TARGET_DATABASE_NAME, credentials);
    }

    public ResultSet executeStatementQuery(String query) throws SQLException {
        Statement statement = this.connection.createStatement();
        return statement.executeQuery(query);
    }

    public PreparedStatement preparePreparedStatementQuery(String query) throws SQLException {
        return this.connection.prepareStatement(query);
    }

    public CallableStatement prepareCall(String query) throws SQLException {
        return this.connection.prepareCall(query);
    }

    public ResultSet findRecordNameInDB(String table, String name) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement
                ("SELECT `name` FROM " + table + " WHERE `name` = ?");

        preparedStatement.setString(1, name);

        return preparedStatement.executeQuery();
    }

    public ResultSet findRecordNameInDBByID(String table, int id) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement
                ("SELECT `name` FROM " + table + " WHERE `id` = ?");

        preparedStatement.setInt(1, id);

        return preparedStatement.executeQuery();
    }

    public int insertRecordInDB(String table, String field, String record) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement
                ("INSERT INTO " + table + " (" + field + ") VALUES (?)");

        preparedStatement.setString(1, record);

        return preparedStatement.executeUpdate();
    }
    public int insertRecordInDB(String table, String field1, String field2, String record1, String record2) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement
                ("INSERT INTO " + table + " (" + field1 + "," + field2 + ") VALUES (?, ?)");

        preparedStatement.setString(1, record1);
        preparedStatement.setString(2, record2);

        return preparedStatement.executeUpdate();
    }
    public int insertRecordInDB(String table, String field1, String field2, String field3, String record1, String record2, String record3) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement
                ("INSERT INTO " + table + " (" + field1 + "," + field2 + "," + field3 + ") VALUES (?, ?, ?)");

        preparedStatement.setString(1, record1);
        preparedStatement.setString(2, record2);
        preparedStatement.setString(3, record3);

        return preparedStatement.executeUpdate();
    }

    public String findRecordIdByNameInDB(String table, String name) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement
                ("SELECT `id` FROM " + table + " WHERE `name` = ?");
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();

        String featuredId = "";

        if (resultSet.next()) {
            featuredId = resultSet.getString(1);
        }
        return featuredId;
    }

}
