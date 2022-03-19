package com.example.football.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class PlayerDto {
    private String firstName;
    private String lastName;
    private String email;
    private String birthDate;
    private String position;
    private PlayerTownDto town;
    private PlayerTeamDto team;
    private PlayerStatDto stat;

    @XmlElement(name = "first-name")
    @Size(min = 3)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name = "last-name")
    @Size(min = 3)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Email
    @Pattern(regexp = "(?=.*[.]).+")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name = "birth-date")
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @XmlElement(name = "town")
    public PlayerTownDto getTown() {
        return town;
    }

    public void setTown(PlayerTownDto town) {
        this.town = town;
    }

    @XmlElement(name = "team")
    public PlayerTeamDto getTeam() {
        return team;
    }

    public void setTeam(PlayerTeamDto team) {
        this.team = team;
    }

    @XmlElement(name = "stat")
    public PlayerStatDto getStat() {
        return stat;
    }

    public void setStat(PlayerStatDto stat) {
        this.stat = stat;
    }
}
