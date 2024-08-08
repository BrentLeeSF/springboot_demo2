package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class BookController {

	@Autowired
	BookRepository bookRepository;

	@SuppressWarnings("null")
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks() {
		try {
			List<Book> books = bookRepository.findAll();
			return new ResponseEntity<>(books, HttpStatus.OK);
		}
		catch(Exception e) {
			// TODO - create better error response with exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
		Optional<Book> bookData = bookRepository.findById(id);

		if(bookData.isPresent()) {
			return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@SuppressWarnings("null")
	@PostMapping("/books")
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		try {
			Book _book = bookRepository
				.save(new Book(
					book.getTitle(),
					book.getDescription(),
					book.getPublisher()
				));
			return new ResponseEntity<>(_book, HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
		Optional<Book> bookData = bookRepository.findById(id);

		if(bookData.isPresent()) {
			Book bookToChange = bookData.get();
        	if(book.getTitle() != null) {
            	bookToChange.setTitle(book.getTitle());
        	}
			if(book.getDescription() != null) {
            	bookToChange.setDescription(book.getDescription());
        	}
			if(book.getPublisher() != null) {
            	bookToChange.setPublisher(book.getPublisher());
        	}
			return new ResponseEntity<>(bookRepository.save(bookToChange), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/books/{id}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
		try {
			bookRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} 
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/books")
	public ResponseEntity<HttpStatus> deleteAllBooks() {
		try {
			bookRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
