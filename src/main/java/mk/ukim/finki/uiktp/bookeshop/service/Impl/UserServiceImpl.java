package mk.ukim.finki.uiktp.bookeshop.service.Impl;

import mk.ukim.finki.uiktp.bookeshop.model.User;
import mk.ukim.finki.uiktp.bookeshop.model.enumeration.Role;
import mk.ukim.finki.uiktp.bookeshop.model.exceptions.UserNotFoundException;
import mk.ukim.finki.uiktp.bookeshop.repository.UserRepository;
import mk.ukim.finki.uiktp.bookeshop.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> create(String username, String name, String surname, String email, String address, String phoneNumber, String roleString, String password) {
        Role role = Role.valueOf(roleString);
        User user = new User(username,name,surname,email,address,phoneNumber,role,password);
        return Optional.of(this.userRepository.save(user));
    }

    @Override
    public Optional<User>  update(String username, String name, String surname, String email, String address, String phoneNumber, String roleString) {
        Role role = Role.valueOf(roleString);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);
        user.setRole(role);
        return Optional.of(this.userRepository.save(user));
    }

    @Override
    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }
}
