package com.iceprojects.guestlogix_mobile;

import java.io.Serializable;

public class CharacterModel implements Serializable {

    private int id;
    private String name;
    private String status;
    private String species;
    private String gender;
    private String originName;
    private String locationName;
    private String imageURL;

    public CharacterModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String stat) {
        status = stat;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Name : " + name + '\n' +
                "Status : " + status + '\n' +
                "Species : " + species + '\n' +
                "Gender : " + gender + '\n' +
                "Origin : " + originName + '\n' +
                "Location : " + locationName + '\n';
    }
}
