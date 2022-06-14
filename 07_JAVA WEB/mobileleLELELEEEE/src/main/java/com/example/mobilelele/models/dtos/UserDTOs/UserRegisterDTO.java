package com.example.mobilelele.models.dtos.UserDTOs;

import com.example.mobilelele.utils.validators.MatchingFieldsValidator.FieldsMatcher;
import com.example.mobilelele.utils.validators.UniqueUsernameValidator.UniqueUsername;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@FieldsMatcher(
        firstField = "password",
        secondField = "repeatPassword"
)
public class UserRegisterDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String repeatPassword;
    private Boolean isActive;
    private LocalDateTime createdOn;

    public UserRegisterDTO() {
        this.isActive = true;
        this.createdOn = LocalDateTime.now();
    }

    @NotBlank
    @Length(min = 3)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotBlank
    @Length(min = 3)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotBlank(message = "Username should not be blank.")
    @Length(min = 3, message = "Username length should be minimum 3 symbols.")
    @UniqueUsername
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank
    @Size(min = 5)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotBlank(message = "Repeat password should not be blank.")
    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
}
