/*
    The class that connects with the database.
*/

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Abdelfattah
 */
public class DatabaseDriver {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private String queryStr;
    private ResultSet resultSet; // used for store data from database

    public DatabaseDriver() {
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    public void setQuerystr(String querystr) {
        this.queryStr = querystr;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public String getQuerystr() {
        return queryStr;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void startConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoedb", "root", "1234");
        } catch (ClassNotFoundException | SQLException ex) {
            // Handled in checkConnection Function
        }
    }

    public Boolean checkConnection() {
        this.startConnection();
        if (this.getConnection() == null) {
            return false;
        } else {
            try {
                connection.close();
                return true;
            } catch (SQLException ex) {
                return false;
            }
        }
    }

    public void endStatConnection() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException ex) {
        }
    }

    public void endPStatConnection() {
        try {
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
        }
    }

    public void endResultSet() {
        try {
            resultSet.close();
        } catch (SQLException ex) {
        }
    }
}
