package com.example.noteproject.model;

public class NoteModel {
    private String title, description, data;

    public NoteModel(String title, String description, String data) {
        this.title = title;
        this.description = description;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getData() {
        return data;
    }
}
