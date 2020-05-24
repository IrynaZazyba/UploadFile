package by.itech.upload.bean;

import java.io.Serializable;
import java.util.Objects;

public class UploadFile implements Serializable {


    private static final long serialVersionUID = 1019020931170618272L;

    private int id;
    private String title;


    public UploadFile() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UploadFile file = (UploadFile) o;
        return id == file.id &&
                Objects.equals(title, file.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
