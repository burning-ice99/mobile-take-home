package com.iceprojects.guestlogix_mobile;

import java.util.Arrays;

public class EpisodeModel {

    private int iD;
    private String name;
    private String airdate;
    private String episode;
    private String detailsURL;
    private String[] characters;

    public EpisodeModel() {
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAirdate() {
        return airdate;
    }

    public void setAirdate(String airdate) {
        this.airdate = airdate;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getDetailsURL() {
        return detailsURL;
    }

    public void setDetailsURL(String detailsURL) {
        this.detailsURL = detailsURL;
    }

    public String[] getCharacters() {
        return characters;
    }

    public void setCharacters(String[] characters) {
        this.characters = characters;
    }

    @Override
    public String toString() {
        return "EpisodeModel{" +
                "iD=" + iD +
                ", name='" + name + '\'' +
                ", airdate='" + airdate + '\'' +
                ", episode='" + episode + '\'' +
                ", detailsURL='" + detailsURL + '\'' +
                ", characters=" + Arrays.toString(characters) +
                '}';
    }
}
