package exam.model.dtos.customers;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class TownNameDTO {
    @Expose
    private String name;

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
