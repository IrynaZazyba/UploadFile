package by.itech.upload.dao.impl;

import by.itech.upload.bean.UploadFile;
import by.itech.upload.dao.FileInfoDAO;
import by.itech.upload.dao.dbconn.ConnectionHandler;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class SQLFileInfoDAOImpl implements FileInfoDAO {

    private final static ConnectionHandler connectionHandler = ConnectionHandler.getInstance();
    private final static String SELECT_ALL_FILE_NAME = "SELECT title FROM `file_name`";
    private final static String INSERT_FILE_NAME = "INSERT INTO `file_name` (`title`) VALUES (?)";


    @Override
    public Set<UploadFile> getAllFileName() {
        Set<UploadFile> fileNames = new HashSet<>();

        try (Connection connection = connectionHandler.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet resultSet = statement.executeQuery(SELECT_ALL_FILE_NAME);
            while (resultSet.next()) {
                UploadFile file = new UploadFile();
                file.setTitle(resultSet.getString("title"));
                fileNames.add(file);
            }

        } catch (SQLException e) {
            System.out.println("sql ex");
            e.printStackTrace();
        }


        return fileNames;
    }

    @Override
    public void insertFileInfo(UploadFile file) {
        try (Connection connection = connectionHandler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FILE_NAME);) {
            preparedStatement.setString(1, file.getTitle());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("sql ex");
            e.printStackTrace();
        }

    }
}



