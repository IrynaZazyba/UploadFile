package by.itech.upload.dao.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHandler {


    private final static ConnectionHandler instance = new ConnectionHandler();

    private String driverName;
    private String url;
    private String user;
    private String password;


    private ConnectionHandler() {
        DBResourceManager dbResourseManager = DBResourceManager.getInstance();
        this.url = dbResourseManager.getValue(DBParameter.DB_URL);
        this.user = dbResourseManager.getValue(DBParameter.DB_USER);
        this.password = dbResourseManager.getValue(DBParameter.DB_PASSWORD);
        this.driverName=dbResourseManager.getValue(DBParameter.DB_DRIVER);
    }

    public static ConnectionHandler getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("no such driver");
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, user, password);
    }

}
