import org.testng.annotations.Test;
import utilities.DBUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintSQLResults {

    @Test
    public void printResults() throws SQLException {
        // print all products: id, title, price which are between 2000 and 5000
        Connection connection = DBUtilities.getDBConnection();
        String sqlQuery = "select distinct id, title, price from products where title is not null";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String[] title = resultSet.getString("title").trim().split("\\s+");
            if (title.length == 3) {
                System.out.println(resultSet.getString("title") + "\t" + resultSet.getString("id") + "\t" + resultSet.getString("price"));
            }
        }
        // EXTRA: print only those titles that contain 3 words
    }

    @Test
    public void printCategories() throws SQLException {
        Connection connection = DBUtilities.getDBConnection();
        String sqlQuery = "SELECT DISTINCT id, title, description, created FROM categories " +
                "WHERE title <> '' AND description <> '' " +
                "ORDER BY title ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            String created = resultSet.getString("created").split(" ")[0]; // Getting only the date part

            if (title.equals("QA Engineer")) {
                System.out.println("ID: " + id +
                        ", Title: " + title.toUpperCase() +
                        ", Description: " + description +
                        ", Created: " + created);
            } else {
                System.out.println("ID: " + id +
                        ", Title: " + title.toLowerCase() +
                        ", Description: " + description +
                        ", Created: " + created);
            }
        }
    }
}
