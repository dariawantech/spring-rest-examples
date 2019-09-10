/**
 * Spring REST Examples v1 (https://www.dariawan.com)
 * Copyright (C) 2019 Dariawan <hello@dariawan.com>
 *
 * Creative Commons Attribution-ShareAlike 4.0 International License
 *
 * Under this license, you are free to:
 * # Share - copy and redistribute the material in any medium or format
 * # Adapt - remix, transform, and build upon the material for any purpose,
 *   even commercially.
 *
 * The licensor cannot revoke these freedoms
 * as long as you follow the license terms.
 *
 * License terms:
 * # Attribution - You must give appropriate credit, provide a link to the
 *   license, and indicate if changes were made. You may do so in any
 *   reasonable manner, but not in any way that suggests the licensor
 *   endorses you or your use.
 * # ShareAlike - If you remix, transform, or build upon the material, you must
 *   distribute your contributions under the same license as the original.
 * # No additional restrictions - You may not apply legal terms or
 *   technological measures that legally restrict others from doing anything the
 *   license permits.
 *
 * Notices:
 * # You do not have to comply with the license for elements of the material in
 *   the public domain or where your use is permitted by an applicable exception
 *   or limitation.
 * # No warranties are given. The license may not give you all of
 *   the permissions necessary for your intended use. For example, other rights
 *   such as publicity, privacy, or moral rights may limit how you use
 *   the material.
 *
 * You may obtain a copy of the License at
 *   https://creativecommons.org/licenses/by-sa/4.0/
 *   https://creativecommons.org/licenses/by-sa/4.0/legalcode
 */
package com.dariawan.rest.service.impl;

import com.dariawan.rest.domain.Author;
import com.dariawan.rest.domain.Book;
import com.dariawan.rest.exception.BadResourceException;
import com.dariawan.rest.exception.ResourceAlreadyExistsException;
import com.dariawan.rest.exception.ResourceNotFoundException;
import com.dariawan.rest.service.BookService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author Desson Ariawan
 */

@Service("bookService")
public class BookServiceImpl implements BookService{
    
    private static ArrayList<Author> authors;
    private static ArrayList<Book> books;
    
    static {
        authors = new ArrayList<>(Arrays.asList(new Author(1, "J.R.R. Tolkien", "J.R.R. Tolkien is best known as the author of classic high fantasy works The Hobbit, The Lord of The Rings, and The Silmarillion"),
                new Author(2, "C.S. Lewis", "C.S. Lewis is best known for his works of fiction especially The Screwtape Letters, The Chronicles of Narnia, and The Space Trilogy, and for his non-fiction Christian apologetics")));
        books = new ArrayList<>(Arrays.asList(new Book(1, "1827184", null, null, "The Hobbit"),
                new Book(2, "1487587", null, null, "The Lord of The Rings"),
                new Book(3, "3318634", "0048231398", null, "The Silmarillion"),
                new Book(4, "7207376", null, null, "The Lion, the Witch, and The Wardrobe"),
                new Book(5, "2812448", null, "9780006716792", "Prince Caspian"),
                new Book(6, "2805288", null, "9780006716808", "The Voyage of the Dawn Treader"),
                new Book(7, "1304139", null, "9780006716815", "The Silver Chair"),
                new Book(8, "2801054", null, "9780006716785", "The Horse and His Boy"),
                new Book(9, "2497740", null, "9780006716839", "The Magician's Nephew"),
                new Book(10, "752428300", null, "9780006716822", "The Last Battle")));
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    private Book findBookById(long id) {
        return books.stream()
                .filter(b -> id == b.getId())
                .findAny()
                .orElse(null);
    }
    
    private long findBooksLastId() {
        Collections.sort(books);         
        return books.get(books.size() - 1).getId();     
    }
    
    /*
    private Book findBookByTitle(String title) {
        return books.stream()
                .filter(b -> title.equalsIgnoreCase(b.getTitle()))
                .findAny()
                .orElse(null);
    }
    */
    
    @Override
    public Book findById(long id) throws ResourceNotFoundException {
        // only for demo, in real scenario: use database
        Book book = findBookById(id);
        if (book==null) {
            throw new ResourceNotFoundException("Cannot find book with id: " + id);
        }
        else return book;
    }

    @Override
    public Book save(Book book) throws BadResourceException, ResourceAlreadyExistsException {
        if (book.getId() > 0 && findBookById(book.getId())!=null) { 
            throw new ResourceAlreadyExistsException("Bookwith with id: " + book.getId() + " already exists");
        }
        
        if (!StringUtils.isEmpty(book.getTitle())) {
            long newId = findBooksLastId() + 1;
            book.setId(newId);
            books.add(book);
            return book;
        }
        else {
            BadResourceException exc = new BadResourceException("Failedto save book");
            exc.addErrorMessage("Title is null or empty");
            throw exc;
        }
    }
}
