package T01_DBAppsIntroduction.Lab;

import java.sql.*;
import java.util.Scanner;

public class P02_DataRetrievalApp {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", "root", "12345");

        System.out.println("Hello, enter the desired username you want to retrieve information for");
        String targetUsername = scanner.nextLine();

        PreparedStatement preparedStatement = connection.prepareStatement
                (
                    "SELECT `first_name`, `last_name`, COUNT(*) AS `played_games`\n" +
                        "FROM `users` AS `u`\n" +
                        "JOIN `users_games` AS `ug`\n" +
                        "ON u.`id` = ug.`user_id`\n" +
                        "WHERE u.`user_name` = ?\n" +
                        "GROUP BY `user_id`;"
                );

        preparedStatement.setString(1, targetUsername);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String gamesPlayed = resultSet.getString("played_games");

            System.out.printf("User: %s%n%s %s has played %s games", targetUsername, firstName, lastName, gamesPlayed);
        } else {
            System.out.println("No such user exists");
        }

    }
}
