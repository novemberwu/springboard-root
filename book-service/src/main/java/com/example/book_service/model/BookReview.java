package com.example.book_service.model;

import lombok.Data;

@Data
public class BookReview implements Comparable<BookReview> {
    private String id;
    private String bookTitle;
    private String bookIsbn;
    private String bookIsbn13;
    private double rating;

    @Override
    public int compareTo(BookReview that) {
        return this.bookIsbn.compareTo(that.bookIsbn);
    }
}