package com.upn.animeapp;

import com.google.gson.annotations.SerializedName;

public class Episode {

    @SerializedName("title")
    private String title;

    @SerializedName("aired")
    private String airedDate; // Fecha de publicación

    @SerializedName("mal_id")
    private int episodeNumber; // Número del episodio

    public Episode(String title, String airedDate, int episodeNumber) {
        this.title = title;
        this.airedDate = airedDate;
        this.episodeNumber = episodeNumber;
    }
    // Getters
    public String getTitle() {
        return title;
    }

    public String getAiredDate() {
        return airedDate;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }
}
