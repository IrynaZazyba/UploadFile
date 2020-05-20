package by.itech.upload.logic;

import by.itech.upload.logic.impl.UploadFileServiceImpl;

public class ServiceFactory {

    private final static ServiceFactory instance = new ServiceFactory();
    private final UploadFileService uploadFileService = new UploadFileServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UploadFileService getUploadFileService() {
        return uploadFileService;
    }
}
