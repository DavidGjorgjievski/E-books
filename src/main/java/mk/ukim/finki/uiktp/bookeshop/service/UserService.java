package mk.ukim.finki.uiktp.bookeshop.service;

import mk.ukim.finki.uiktp.bookeshop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findByUsername(String username);

    Optional<User> create(String username, String name, String surname, String email, String address, String phoneNumber, String role, String password);

    Optional<User> update(String username, String name, String surname, String email, String address, String phoneNumber, String role);

//    void delete(String username);
    Optional<User> delete(String username);
}
