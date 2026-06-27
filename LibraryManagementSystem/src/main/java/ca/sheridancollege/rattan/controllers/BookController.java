package ca.sheridancollege.rattan.controllers;

import ca.sheridancollege.rattan.beans.Book;
import ca.sheridancollege.rattan.beans.Review;
import ca.sheridancollege.rattan.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    @Autowired
    private DatabaseAccess da;

    @GetMapping("/admin/addBook")
    public String loadAddBook(Model model) {
        model.addAttribute("book", new Book());
        return "admin/add-book";
    }

    @PostMapping("/admin/addBook")
    public String saveBook(@ModelAttribute Book book) {
        da.addBook(book);
        return "redirect:/";
    }
    
   
    @GetMapping("/viewBook/{id}")
    public String viewBook(@PathVariable Long id, Model model) {
        Book book = da.getBookById(id);
        if (book != null) {
            book.setReviews(da.getReviewsForBook(id));
            model.addAttribute("book", book);
        }
        return "view-book"; 
    }
    
    
    @GetMapping("/user/addReview/{id}")
    public String loadAddReview(@PathVariable Long id, Model model) {
        Book book = da.getBookById(id);
        if (book == null) return "redirect:/"; 
        
        Review review = new Review();
        review.setBookId(id);
        
        model.addAttribute("book", book);
        model.addAttribute("review", review);
        return "user/add-review";
    }

    @PostMapping("/user/addReview")
    public String saveReview(@ModelAttribute Review review) {
        da.addReview(review);
        
        return "redirect:/viewBook/" + review.getBookId();
    }
}