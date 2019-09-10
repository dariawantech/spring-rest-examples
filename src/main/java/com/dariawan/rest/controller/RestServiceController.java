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
package com.dariawan.rest.controller;

import com.dariawan.rest.domain.Book;
import com.dariawan.rest.exception.BadResourceException;
import com.dariawan.rest.exception.ResourceAlreadyExistsException;
import com.dariawan.rest.exception.ResourceNotFoundException;
import com.dariawan.rest.service.BookService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping(path = "/rest/v1/books", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> findAllBooks() {
        List<Book> books = bookService.findAll();
        return ResponseEntity.ok(books);  // return 200, with json body
    }

    @RequestMapping(path = "/rest/v1/books/{bookId}", method = RequestMethod.GET)
    public ResponseEntity<Book> findBookById(@PathVariable long bookId) {
        try {
            Book book = bookService.findById(bookId);
            return ResponseEntity.ok(book);  // return 200, with json body
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
        }
    }

    @RequestMapping(path = "/rest/v1/books", method = RequestMethod.POST)
    public ResponseEntity<Void> addBook(@RequestBody Book book) throws URISyntaxException {
        try {
            Book newBook = bookService.save(book);
            return ResponseEntity.created(new URI("/rest/v1/books/" + newBook.getId())).build();
        } catch (ResourceAlreadyExistsException e) {
            // log exception first, then return Conflict (409)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (BadResourceException e) {
            // log exception first, then return Bad Request (404)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
