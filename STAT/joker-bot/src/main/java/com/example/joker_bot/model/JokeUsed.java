package com.example.joker_bot.model;

import java.time.LocalDate;

public class JokeUsed {
    private Integer id_uj;
    private Long id_joke;
    private Long id_chat;
    private LocalDate date_used;

    public int getId_uj() {
        return id_uj;
    }

    public Long getId_joke() {
        return id_joke;
    }

    public Long getId_chat() {
        return id_chat;
    }

    public LocalDate getDate_used() {
        return date_used;
    }

    public void setId_uj(int id_uj) {
        this.id_uj = id_uj;
    }

    public void setId_joke(Long id_joke) {
        this.id_joke = id_joke;
    }

    public void setId_chat(Long id_chat) {
        this.id_chat = id_chat;
    }

    public void setDate_used(LocalDate date_used) {
        this.date_used = date_used;
    }
}
