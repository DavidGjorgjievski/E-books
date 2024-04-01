package mk.ukim.finki.uiktp.bookeshop.web;

import mk.ukim.finki.uiktp.bookeshop.model.User;
import mk.ukim.finki.uiktp.bookeshop.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return this.userService.findAll();
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
        return this.userService.findByUsername(username)
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> create(@RequestParam String username,
                                       @RequestParam String name,
                                       @RequestParam String surname,
                                       @RequestParam String email,
                                       @RequestParam String address,
                                       @RequestParam String phoneNumber,
                                       @RequestParam String roleString,
                                       @RequestParam String password) {
        return this.userService.create(username, name, surname, email, address, phoneNumber, roleString, password)
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> update(@PathVariable String username,
                                       @RequestParam String name,
                                       @RequestParam String surname,
                                       @RequestParam String email,
                                       @RequestParam String address,
                                       @RequestParam String phoneNumber,
                                       @RequestParam String roleString) {
        return this.userService.update(username, name, surname, email, address, phoneNumber, roleString)
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity delete(@PathVariable String username) {
        this.userService.delete(username);
        if (this.userService.findByUsername(username).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
