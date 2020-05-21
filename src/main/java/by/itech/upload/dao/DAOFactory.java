package by.itech.upload.dao;

import by.itech.upload.dao.impl.SQLFileInfoDAOImpl;

public class DAOFactory {

    private final static DAOFactory instance = new DAOFactory();
    private final FileInfoDAO fileInfoDAO = new SQLFileInfoDAOImpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public FileInfoDAO getFileInfoDAO() {
        return fileInfoDAO;
    }
}
