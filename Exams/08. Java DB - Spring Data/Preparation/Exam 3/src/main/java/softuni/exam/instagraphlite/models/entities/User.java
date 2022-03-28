package softuni.exam.instagraphlite.models.entities;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String username;
    private String password;
    private Picture profilePicture;
    private List<Post> posts;

    @Column(nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToOne
    @JoinColumn(nullable = false)
    public Picture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Picture profilePicture) {
        this.profilePicture = profilePicture;
    }

    @OneToMany(mappedBy = "user")
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format(
                "User: %s\nPost count: %d\n%s",
                this.username, this.posts.size(),
                        this.posts
                                .stream()
                                .map(post -> String.format("==Post Details:\n" +
                                                            "----Caption: %s\n" +
                                                           "----Picture Size: %.2f",
                                post.getCaption(), post.getPicture().getSize()))
                                .collect(Collectors.joining("\n"))));

        return stringBuilder.toString().trim();
    }
}
