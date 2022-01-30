package T01_DBAppsIntroduction.Lab;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class P01_AccessingDatabase {
    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "12345");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", props);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter desired salary");
        String targetSalary = scanner.nextLine();

        PreparedStatement statement = connection.prepareStatement("SELECT `first_name`, `last_name` FROM `employees` WHERE `salary` > ?");;

        statement.setDouble(1, Double.parseDouble(targetSalary));

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");

            System.out.println(firstName + " " + lastName);
        }

    }
}
