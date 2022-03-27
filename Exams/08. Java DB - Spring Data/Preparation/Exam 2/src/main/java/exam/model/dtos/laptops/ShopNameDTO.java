package exam.model.dtos.laptops;

import com.google.gson.annotations.Expose;

public class ShopNameDTO {
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
