package softuni.exam.instagraphlite.models.dtos.posts;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class PostDTO {
    private String caption;
    private UsernameDTO user;
    private PicturePathDTO picture;

    @NotNull
    @Size(min = 21)
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @NotNull
    public UsernameDTO getUser() {
        return user;
    }

    public void setUser(UsernameDTO user) {
        this.user = user;
    }

    @NotNull
    public PicturePathDTO getPicture() {
        return picture;
    }

    public void setPicture(PicturePathDTO picture) {
        this.picture = picture;
    }
}
