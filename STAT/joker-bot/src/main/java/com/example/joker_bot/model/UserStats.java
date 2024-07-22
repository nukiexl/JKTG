package com.example.joker_bot.model;

public class UserStats {
    private Long chat_id;
    private int countLikes;
    private int countDislikes;
    private int countJokes;

    public Long getChat_id() {
        return chat_id;
    }

    public void setChat_id(Long chat_id) {
        this.chat_id = chat_id;
    }

    public int getCountLikes() {
        return countLikes;
    }

    public void setCountLikes(int countLikes) {
        this.countLikes = countLikes;
    }

    public int getCountDislikes() {
        return countDislikes;
    }

    public void setCountDislikes(int countDislikes) {
        this.countDislikes = countDislikes;
    }

    public int getCountJokes() {
        return countJokes;
    }

    public void setCountJokes(int countJokes) {
        this.countJokes = countJokes;
    }

}
