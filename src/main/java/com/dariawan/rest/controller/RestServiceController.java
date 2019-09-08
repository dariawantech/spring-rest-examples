package com.dariawan.rest.controller;

import com.dariawan.rest.domain.Book;
import com.dariawan.rest.exception.ResourceNotFoundException;
import com.dariawan.rest.service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Desson Ariawan
 */
@RestController
public class RestServiceController {

    @Autowired
    protected BookService bookService;
    
    @RequestMapping(path = "/books", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> findAllBooks() {
        // read from database
        List<Book> books = bookService.findAll();
        return ResponseEntity.ok(books);  // return 200, with json body
    }

    @RequestMapping(path = "/books/{bookId}", method = RequestMethod.GET)
    public ResponseEntity<Book> findById(@PathVariable long bookId) {
        try {
            // read from database
            Book book = bookService.findById(bookId);
            return ResponseEntity.ok(book);  // return 200, with json body
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
        }
    }
}
