package com.joshuaai.pinterestclone.Models;

/**
 * Created by Joshua A I on 3/22/2017.
 */

public class UserImages {

    //Data variables
    private String imageUrl;
    private String title;
    private String description;
    private int likes;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
