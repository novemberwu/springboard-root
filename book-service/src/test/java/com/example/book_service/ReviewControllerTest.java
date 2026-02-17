package com.example.book_service;

import com.example.book_service.datastructure.ST;
import com.example.book_service.model.BookReview;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ST<String, BookReview> reviewStorage;

    @Test
    public void getReview_shouldReturnReview() throws Exception {
        BookReview review = new BookReview();
        review.setId("123");
        review.setBookTitle("Test Book");
        review.setRating(5.0);
        when(reviewStorage.get("123")).thenReturn(review);

        mockMvc.perform(get("/api/reviews/123"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Test Book")));
    }

    @Test
    public void getStats_shouldReturnTotalBooks() throws Exception {
        when(reviewStorage.size()).thenReturn(10);

        mockMvc.perform(get("/api/reviews/stats"))
                .andExpect(status().isOk())
                .andExpect(content().string("Total Books: 10"));
    }
}
