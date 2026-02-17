package com.example.book_service;

import com.example.book_service.datastructure.ST;
import com.example.book_service.datastructure.UnsortedST;
import com.example.book_service.model.BookReview;
import com.example.book_service.parser.CSVParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class BookServiceApplication {

	    private final ResourceLoader resourceLoader;
	
	    public BookServiceApplication(ResourceLoader resourceLoader) {
	        this.resourceLoader = resourceLoader;
	    }
	
		public static void main(String[] args) {
			SpringApplication.run(BookServiceApplication.class, args);
		}
	
		@Bean
		public ST<String, BookReview> reviewStorage() throws IOException {
			ST<String, BookReview> st = new UnsortedST<>();
	        String resourcePath = "classpath:data/books.csv";
	        Resource resource = resourceLoader.getResource(resourcePath);
	        try (InputStream inputStream = resource.getInputStream()) {
	            List<BookReview> bookReviews = CSVParser.parse(inputStream, resourcePath);
	            for (BookReview review : bookReviews) {
	                st.put(review.getId(), review);
	            }
	        }
			return st;
		}}
