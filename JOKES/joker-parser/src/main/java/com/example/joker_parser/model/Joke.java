package com.example.joker_parser.model;

import java.time.LocalDate;

public class Joke {
    private int id_joke;
    private String joke_text;
    private LocalDate date_created;
    private int id_category;

    public int getid_joke() {
        return id_joke;
    }

    public void setid_joke(int id_joke) {
        this.id_joke = id_joke;
    }

    public String getjoke_text() {
        return joke_text;
    }

    public void setjoke_text(String joke_text) {
        this.joke_text = joke_text;
    }

    public LocalDate getdate_created() {
        return date_created;
    }

    public void setdate_created(LocalDate date_created) {
        this.date_created = date_created;
    }

    public int getid_category() {
        return id_category;
    }

    public void setid_category(int id_category) {
        this.id_category = id_category;
    }

    @Override
    public String toString() {
        return "Joke{id=" + id_joke + ", joke_text='" + joke_text + "', date_created=" + date_created + ", id_category=" + id_category + "}";
    }
}
