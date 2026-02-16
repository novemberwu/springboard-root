package com.example.book_service.model;

import lombok.Data;

@Data
public class BookReview implements Comparable<BookReview> {
    private String id;
    private String bookTitle;
    private String bookId;
    private double rating;
    private String reviewText;

    @Override
    public int compareTo(BookReview that) {
        return this.bookId.compareTo(that.bookId);
    }
}