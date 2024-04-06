package mk.ukim.finki.uiktp.bookeshop.service;

import mk.ukim.finki.uiktp.bookeshop.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> create(String name, String surname, String birthYear);

    Optional<Author> update(Long id,String name,String surname,String birthYear);

    void delete(Long id);

}
