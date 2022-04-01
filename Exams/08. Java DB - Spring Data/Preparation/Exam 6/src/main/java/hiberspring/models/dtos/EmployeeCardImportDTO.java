package hiberspring.models.dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class EmployeeCardImportDTO {
    @Expose
    private String number;

    @NotNull
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
