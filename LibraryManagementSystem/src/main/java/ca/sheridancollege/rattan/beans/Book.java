package ca.sheridancollege.rattan.beans;

import lombok.Data;
import java.util.List;

@Data
public class Book {
    private Long id;
    private String title;
    private String author;
    private List<Review> reviews;
}