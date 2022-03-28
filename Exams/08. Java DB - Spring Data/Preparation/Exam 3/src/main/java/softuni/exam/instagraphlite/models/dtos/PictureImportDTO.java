package softuni.exam.instagraphlite.models.dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class PictureImportDTO {
    @Expose
    private String path;
    @Expose
    private Double size;

    @NotNull
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @DecimalMin("500.0")
    @DecimalMax("60000.0")
    @NotNull
    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }
}
