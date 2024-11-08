package com.upn.animeapp;

import com.google.gson.annotations.SerializedName;

public class Anime {

    @SerializedName("mal_id")
    private int malId;

    @SerializedName("title")
    private String title;

    @SerializedName("episodes")
    private int episodes;

    @SerializedName("images")
    private ImageWrapper images;

    @SerializedName("synopsis")
    private String description;

    // Getters
    public int getMalId() {
        return malId;
    }

    public String getTitle() {
        return title;
    }

    public int getEpisodes() {
        return episodes;
    }

    public ImageWrapper getImages() {
        return images;
    }

    public String getDescription() {
        return description;
    }

    public static class ImageWrapper {
        @SerializedName("jpg")
        private ImageDetails jpg;

        public ImageDetails getJpg() {
            return jpg;
        }

        public static class ImageDetails {
            @SerializedName("image_url")
            private String imageUrl;

            public String getImageUrl() {
                return imageUrl;
            }
        }
    }
}
