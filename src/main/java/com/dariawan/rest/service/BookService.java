package com.dariawan.rest.service;

import com.dariawan.rest.domain.Book;
import com.dariawan.rest.exception.ResourceNotFoundException;
import java.util.List;

/**
 *
 * @author Desson Ariawan
 */
public interface BookService {
    
    List<Book> findAll();
    
    Book findById(long id) throws ResourceNotFoundException;
}
