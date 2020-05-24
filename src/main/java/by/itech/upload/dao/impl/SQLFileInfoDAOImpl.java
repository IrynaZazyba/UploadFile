package by.itech.upload.dao.impl;

import by.itech.upload.bean.UploadFile;
import by.itech.upload.dao.DAOSQLException;
import by.itech.upload.dao.FileInfoDAO;
import by.itech.upload.dao.dbconn.ConnectionHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class SQLFileInfoDAOImpl implements FileInfoDAO {

    private final static Logger logger = LogManager.getLogger();

    private final static ConnectionHandler connectionHandler = ConnectionHandler.getInstance();
    private final static String SELECT_ALL_FILE_NAME = "SELECT title FROM `file_name`";
    private final static String INSERT_FILE_NAME = "INSERT INTO `file_name` (`title`) VALUES (?)";
    private final static String SELECT_ALL_UPLOAD_FILE_ID = "SELECT id FROM `file_name`";
    private final static String SELECT_UPLOAD_FILE_BY_ID = "SELECT title FROM `file_name` WHERE id=?";

    @Override
    public Set<UploadFile> getAllUploadFileInfoTitle() throws DAOSQLException {
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
            logger.log(Level.ERROR, "SQLException  in SQLFileInfoDAOImpl method getAllUploadFileInfoTitle()", e);
            throw new DAOSQLException("Exception in SQLFileInfoDAOImpl method getAllUploadFileInfoTitle()", e);
        }
        return fileNames;
    }

    @Override
    public void insertUploadFileInfo(UploadFile file) throws DAOSQLException {
        try (Connection connection = connectionHandler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FILE_NAME);) {
            preparedStatement.setString(1, file.getTitle());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException  in SQLFileInfoDAOImpl method insertUploadFileInfo()", e);
            throw new DAOSQLException("Exception in SQLFileInfoDAOImpl method insertUploadFileInfo()", e);
        }

    }


    @Override
    public Set<Integer> getAllUploadFilesId() throws DAOSQLException {
        Set<Integer> filesId = new HashSet<>();

        try (Connection connection = connectionHandler.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SELECT_ALL_UPLOAD_FILE_ID);
            while (resultSet.next()) {
                filesId.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException  in SQLFileInfoDAOImpl method getUploadFilesId()", e);
            throw new DAOSQLException("Exception in SQLFileInfoDAOImpl method getUploadFilesId()", e);
        }

        return filesId;
    }

    @Override
    public UploadFile getUploadFileById(int fileId) throws DAOSQLException {
        UploadFile uploadFile = null;

        try (Connection connection = connectionHandler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_UPLOAD_FILE_BY_ID)) {
            preparedStatement.setInt(1, fileId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                uploadFile = new UploadFile();
                uploadFile.setId(fileId);
                uploadFile.setTitle(resultSet.getString("title"));
            }

        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException  in SQLFileInfoDAOImpl method getUploadFileById()", e);
            throw new DAOSQLException("Exception in SQLFileInfoDAOImpl method getUploadFileById()", e);
        }
        return uploadFile;
    }
}



