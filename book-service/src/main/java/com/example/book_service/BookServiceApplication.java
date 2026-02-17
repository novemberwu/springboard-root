package com.example.book_service;

import com.example.book_service.datastructure.ST;
import com.example.book_service.datastructure.UnsortedST;
import com.example.book_service.model.BookReview;
import com.example.book_service.parser.CSVParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class BookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

    @Bean
    public ST<String, BookReview> reviewStorage() throws IOException {
        ST<String, BookReview> st = new UnsortedST<>();
        List<BookReview> bookReviews = CSVParser.parse("src/main/resources/data/books.csv");
        for (BookReview review : bookReviews) {
            st.put(review.getId(), review);
        }
        return st;
    }
}
