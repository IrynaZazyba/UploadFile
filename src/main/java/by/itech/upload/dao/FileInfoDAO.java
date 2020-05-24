package by.itech.upload.dao;

import by.itech.upload.bean.UploadFile;

import java.util.Set;

public interface FileInfoDAO {

    Set<UploadFile> getAllUploadFileInfoTitle() throws DAOSQLException;

    void insertUploadFileInfo(UploadFile file) throws DAOSQLException;

    Set<Integer> getAllUploadFilesId() throws DAOSQLException;

    UploadFile getUploadFileById(int fileId) throws DAOSQLException;
}
