package com.hackathon.hackathon.model;

public class Book extends Item {
    private String author;
    private int yearPublished;

    public Book(String name, double initialPrice, Bidder currentBidder, String type, String author, int yearPublished) {
        super(name, initialPrice, currentBidder, type);
        this.author = author;
        this.yearPublished = yearPublished;
    }

    // Getters y Setters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }
}
