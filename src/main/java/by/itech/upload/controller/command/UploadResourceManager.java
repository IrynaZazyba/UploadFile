package by.itech.upload.controller.command;

import java.util.ResourceBundle;

public class UploadResourceManager {
    private final static UploadResourceManager instance = new UploadResourceManager();

    private ResourceBundle bundle =
            ResourceBundle.getBundle("uploadPath");

    public static UploadResourceManager getInstance() {
        return instance;
    }

    public String getUploadPathValue(String key) {
        return bundle.getString(key);
    }
}
