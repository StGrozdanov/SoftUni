package softuni.pathfinder.models.entities;

import softuni.pathfinder.models.enums.LevelEnum;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "routes")
public class RouteEntity extends BaseEntity {
    private String gpxCoordinates;
    private LevelEnum level;
    private String name;
    private UserEntity author;
    private String videoUrl;
    private String description;
    private Set<CategoriesEntity> categories;

    @Column(name = "gpx_coordinates", columnDefinition = "LONGTEXT")
    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    @Enumerated(value = EnumType.STRING)
    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }

    @Column(unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    @Column(name = "video_url")
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany
    public Set<CategoriesEntity> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoriesEntity> categories) {
        this.categories = categories;
    }
}
