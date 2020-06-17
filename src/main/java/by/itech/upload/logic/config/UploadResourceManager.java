package by.itech.upload.logic.config;

import java.util.ResourceBundle;

public class UploadResourceManager {

    private final static String RESOURCE_BUNDLE_UPLOAD_PATH="uploadPath";
    private final static UploadResourceManager instance = new UploadResourceManager();

    private ResourceBundle bundle =
            ResourceBundle.getBundle(RESOURCE_BUNDLE_UPLOAD_PATH);

    public static UploadResourceManager getInstance() {
        return instance;
    }

    public String getUploadPathValue(String key) {
        return bundle.getString(key);
    }
}
