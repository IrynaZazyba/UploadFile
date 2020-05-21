package by.itech.upload.dao;

import by.itech.upload.bean.UploadFile;

import java.util.Set;

public interface FileInfoDAO {

    Set<UploadFile> getAllFileName();

    void insertFileInfo(UploadFile file);

}
