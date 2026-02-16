package com.example.book_service;

import com.example.book_service.datastructure.ST;
import com.example.book_service.model.BookReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    // Your Symbol Table implementation
    private final ST<String, BookReview> reviewStorage;

    @Autowired
    public ReviewController(ST<String, BookReview> reviewStorage) {
        this.reviewStorage = reviewStorage;
    }

    @GetMapping("/{id}")
    public BookReview getReview(@PathVariable String id) {
        // Use the ST's get(key) method
        return reviewStorage.get(id);
    }

    @GetMapping("/stats")
    public String getStats() {
        // Use ST's size() and min()/max() for ordered data
        return "Total Books: " + reviewStorage.size();
    }
}
