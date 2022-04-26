package company.storage.dao.database.databasestorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private Connection con;
    private String dbUrl = "jdbc:postgresql://localhost:5432/musiclib33";
    private String dbUserName = "postgres";
    private String dbPassword = "092327asd";

    public MyConnection(){
        try {
            con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            con.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return con;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }
}
