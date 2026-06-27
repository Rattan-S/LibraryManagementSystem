package ca.sheridancollege.rattan.database;

import ca.sheridancollege.rattan.beans.Book;
import ca.sheridancollege.rattan.beans.Review;
import ca.sheridancollege.rattan.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    public User findUserAccount(String email) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM users WHERE email= :email;";
        parameters.addValue("email", email);
        try {
            return jdbc.queryForObject(query, parameters, new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<String> getRolesById(Long userid) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT roles.rolename FROM user_role, roles WHERE user_role.roleid = roles.roleid AND userid = :userid";
        parameters.addValue("userid", userid);
        return jdbc.queryForList(query, parameters, String.class);
    }
    
    public List<Book> getBooks() {
        String query = "SELECT * FROM books";
        return jdbc.query(query, new MapSqlParameterSource(), new BeanPropertyRowMapper<>(Book.class));
    }

    public void addBook(Book book) {
        String query = "INSERT INTO books (title, author) VALUES (:title, :author)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("title", book.getTitle());
        parameters.addValue("author", book.getAuthor());
        jdbc.update(query, parameters);
    }
    
    public Book getBookById(Long id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM books WHERE id = :id";
        parameters.addValue("id", id);
        try {
            return jdbc.queryForObject(query, parameters, new BeanPropertyRowMapper<>(Book.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Review> getReviewsForBook(Long bookId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM reviews WHERE bookId = :bookId";
        parameters.addValue("bookId", bookId);
        return jdbc.query(query, parameters, new BeanPropertyRowMapper<>(Review.class));
    }
    
    public void addReview(Review review) {
        String query = "INSERT INTO reviews (text, bookId) VALUES (:text, :bookId)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("text", review.getText());
        parameters.addValue("bookId", review.getBookId());
        jdbc.update(query, parameters);
    }
    
    public void addUser(String email, String password) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "INSERT INTO users (email, encryptedpassword, enabled) VALUES (:email, :password, 1)";
        parameters.addValue("email", email);
        parameters.addValue("password", password);
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(query, parameters, keyHolder);
        
        Number key = keyHolder.getKey();
        if (key != null) {
            Long userId = key.longValue();
            
            
            String roleQuery = "INSERT INTO user_role (userid, roleid) VALUES (:userid, 2)";
            MapSqlParameterSource roleParams = new MapSqlParameterSource();
            roleParams.addValue("userid", userId);
            jdbc.update(roleQuery, roleParams);
        }
    }
}