package mk.ukim.finki.uiktp.bookeshop.service.Impl;

import mk.ukim.finki.uiktp.bookeshop.model.Author;
import mk.ukim.finki.uiktp.bookeshop.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.uiktp.bookeshop.repository.AuthorRepository;
import mk.ukim.finki.uiktp.bookeshop.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> create(String name, String surname, String birthYear) {
        Author author = new Author(name, surname, birthYear);
        return Optional.of(this.authorRepository.save(author));
    }
    @Override
    public Optional<Author> update(Long id, String name, String surname, String birthYear) {
       Author author = this.authorRepository.findById(id)
               .orElseThrow(()->new AuthorNotFoundException(id));
       author.setName(name);
       author.setSurname(surname);
       author.setBirthYear(birthYear);
       return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public void delete(Long id) {
        this.authorRepository.deleteById(id);
    }
}
