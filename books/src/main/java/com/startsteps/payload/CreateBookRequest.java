package com.startsteps.payload;

public class CreateBookRequest {
    private String name;
    private String author;

    public CreateBookRequest(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String autor) {
        this.author = autor;
    }
}
