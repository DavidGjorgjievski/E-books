package mk.ukim.finki.uiktp.bookeshop.web;

import mk.ukim.finki.uiktp.bookeshop.model.Book;
import mk.ukim.finki.uiktp.bookeshop.model.enumeration.Genre;
import mk.ukim.finki.uiktp.bookeshop.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<Book> findAll() {
        return this.bookService.findAll();
    }

    @GetMapping("/isbn/{isbn}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Book> findBookByISBN(@PathVariable String isbn) {
        return this.bookService.findBookByISBN(isbn)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/genre/{genre}")
    @PreAuthorize("isAuthenticated()")
    public List<Book> findBooksByGenre(@PathVariable Genre genre) {
        return this.bookService.findBooksByGenre(genre);
    }

    @GetMapping("/author/{authorId}")
    @PreAuthorize("isAuthenticated()")
    public List<Book> findBooksByAuthor(@PathVariable Long authorId) {
        return this.bookService.findBooksByAuthor(authorId);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> create(@RequestParam String isbn,
                                       @RequestParam String title,
                                       @RequestParam String publicationHouse,
                                       @RequestParam String publicationYear,
                                       @RequestParam Genre genre,
                                       @RequestParam String price,
                                       @RequestParam byte[] imageData,
                                       @RequestParam List<Long> authorIds) {
        return this.bookService.create(isbn, title, publicationHouse, publicationYear, genre, price, imageData, authorIds)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{isbn}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> update(@PathVariable String isbn,
                                       @RequestParam String title,
                                       @RequestParam String publicationHouse,
                                       @RequestParam String publicationYear,
                                       @RequestParam Genre genre,
                                       @RequestParam String price,
                                       @RequestParam byte[] imageData,
                                       @RequestParam List<Long> authorIds) {
        return this.bookService.update(isbn, title, publicationHouse, publicationYear, genre, price, imageData, authorIds)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{isbn}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteBookByIsbn(@PathVariable String isbn) {
        this.bookService.deleteBookByIsbn(isbn);
        if (this.bookService.findBookByISBN(isbn).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
