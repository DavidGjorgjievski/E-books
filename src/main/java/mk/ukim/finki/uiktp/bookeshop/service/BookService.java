    package mk.ukim.finki.uiktp.bookeshop.service;

    import mk.ukim.finki.uiktp.bookeshop.model.Book;
    import mk.ukim.finki.uiktp.bookeshop.model.enumeration.Genre;

    import java.util.List;
    import java.util.Optional;

    public interface BookService {

        List<Book> findAll();

        Optional<Book> findBookByISBN(String isbn);

        List<Book> findBooksByGenre(Genre genre);

        List<Book> findBooksByAuthor(Long authorId);

        Optional<Book> create(String isbn, String title, String publicationHouse, String publicationYear, Genre genre, String price, byte[] imageData, List<Long> authorIds);

        Optional<Book>  update(String isbn, String title, String publicationHouse, String publicationYear, Genre genre, String price,byte[] imageData, List<Long> authorIds);

        void deleteBookByIsbn(String isbn);

    }
