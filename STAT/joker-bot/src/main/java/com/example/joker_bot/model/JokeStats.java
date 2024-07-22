package com.example.joker_bot.model;

public class JokeStats {
    private Long joke_id;
    private int likes;
    private int dislikes;

    public Long getJoke_id() {
        return joke_id;
    }

    public void setJoke_id(Long joke_id) {
        this.joke_id = joke_id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
}