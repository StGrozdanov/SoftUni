package softuni.pathfinder.models.entities;

import softuni.pathfinder.models.enums.CategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoriesEntity extends BaseEntity {
    private CategoryEnum name;
    private String description;

    @Enumerated(value = EnumType.STRING)
    public CategoryEnum getName() {
        return name;
    }

    public void setName(CategoryEnum name) {
        this.name = name;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
