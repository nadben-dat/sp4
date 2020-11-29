package Mario;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Date;

public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void readDataBase() throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
           // Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB//
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/Pizzas?"
                            + "user=root&password=Nougat1965");

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery("select * from Pizzas.comments");
            writeResultSet(resultSet);

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect
                    .prepareStatement("insert into  Pizzas.comments values (default, ?, ?, ?, ? , ?.?)");
            // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
            // Parameters start with 1
            preparedStatement.setString(1, "iD");
            preparedStatement.setString(2, "pizzaNr");
            preparedStatement.setString(3, "pizzaName");
            preparedStatement.setString(4, "pizzaFilling");
            preparedStatement.setString(5, "pizzaPrice");
            preparedStatement.setString(6, "statistic");


            preparedStatement.executeUpdate();

            preparedStatement = connect
                    .prepareStatement("SELECT  iD,pizzaNr, pizzaName, pizzaFilling,pizzaPrice,static From Pizza comment");
            resultSet = preparedStatement.executeQuery();
            writeResultSet(resultSet);

            // Remove again the insert comment
            preparedStatement = connect
                    .prepareStatement("delete from Pizzas.comments where pizzaNr= ? ; ");
            preparedStatement.setString(1, "PizzaNr");
            preparedStatement.executeUpdate();

            resultSet = statement
                    .executeQuery("select * from pizza.comments");
            writeMetaData(resultSet);

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }

    private void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);








           /* String summary = resultSet.getString("");
           // Date date = resultSet.getDate("datum");
            String comment = resultSet.getString("comments");
            System.out.println("User: " + user);
            //System.out.println("Website: " + website);
            //System.out.println("summary: " + summary);
            //System.out.println("Date: " + date);
            System.out.println("Comment: " + comment);*/
        }
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }


}