package mk.ukim.finki.uiktp.bookeshop.service.Impl;

import mk.ukim.finki.uiktp.bookeshop.model.Author;
import mk.ukim.finki.uiktp.bookeshop.model.Book;
import mk.ukim.finki.uiktp.bookeshop.model.enumeration.Genre;
import mk.ukim.finki.uiktp.bookeshop.model.exceptions.BookNotFoundException;
import mk.ukim.finki.uiktp.bookeshop.repository.AuthorRepository;
import mk.ukim.finki.uiktp.bookeshop.repository.BookRepository;
import mk.ukim.finki.uiktp.bookeshop.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findBookByISBN(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public List<Book> findBooksByGenre(Genre genre) {
        return bookRepository.findBooksByGenre(genre);
    }

    public List<Book> findBooksByPublicationYear(String year){
        return bookRepository.findBooksByPublicationYear(year);
    }

    @Override
    public List<Book> findBooksByAuthor(Long authorId) {
        return bookRepository.findBooksByAuthorId(authorId);
    }

    @Override
    public Optional<Book> create(String isbn, String title, String publicationHouse, String publicationYear, Genre genre, String price, byte[] imageData, List<Long> authorIds) {
        List<Author> authors = authorRepository.findAllById(authorIds);
        Book book = new Book(isbn, title, publicationHouse, publicationYear, genre, price, imageData, authors);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> update(String isbn, String title, String publicationHouse, String publicationYear, Genre genre, String price, byte[] imageData, List<Long> authorIds) {

        List<Author> authors = authorRepository.findAllById(authorIds);
        Book book = this.bookRepository.findById(isbn).orElseThrow(()-> new BookNotFoundException(isbn));
        book.setTitle(title);
        book.setPublicationHouse(publicationHouse);
        book.setPublicationYear(publicationYear);
        book.setGenre(genre);
        book.setPrice(price);
        book.setImageData(imageData);
        book.setAuthors(authors);

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void deleteBookByIsbn(String isbn) {
        bookRepository.deleteById(isbn);
    }
}
