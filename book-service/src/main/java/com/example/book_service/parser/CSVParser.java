package com.example.book_service.parser;

import com.example.book_service.model.BookReview;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

    public static List<BookReview> parse(String filePath) throws IOException {
        List<BookReview> bookReviews = new ArrayList<>();
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip header
            br.readLine();

            while ((line = br.readLine()) != null) {
                try {
                    String[] bookData = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    BookReview bookReview = new BookReview();
                    bookReview.setId(bookData[0]);
                    bookReview.setBookTitle(bookData[1]);
                    bookReview.setRating(Double.parseDouble(bookData[3]));
                    bookReview.setBookIsbn(bookData[4]);
                    bookReviews.add(bookReview);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.err.println("Skipping malformed line: " + line);
                }
            }
        }

        return bookReviews;
    }
}
