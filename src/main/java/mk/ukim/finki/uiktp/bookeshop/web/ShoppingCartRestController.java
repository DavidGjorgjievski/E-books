package mk.ukim.finki.uiktp.bookeshop.web;

import mk.ukim.finki.uiktp.bookeshop.model.Book;
import mk.ukim.finki.uiktp.bookeshop.model.ShoppingCart;
import mk.ukim.finki.uiktp.bookeshop.model.exceptions.BookNotFoundException;
import mk.ukim.finki.uiktp.bookeshop.model.exceptions.UserNotFoundException;
import mk.ukim.finki.uiktp.bookeshop.service.ShoppingCartService;
import mk.ukim.finki.uiktp.bookeshop.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shopping-cart")
public class ShoppingCartRestController {
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartRestController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public List<ShoppingCart> findAll() {
        return this.shoppingCartService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> findById(@PathVariable Long id) {
        return this.shoppingCartService.findById(id)
                .map(shoppingCart -> ResponseEntity.ok().body(shoppingCart))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add-book")
    public ResponseEntity<ShoppingCart> addBookToCart(@RequestParam String username,
                                                      @RequestParam String bookIsbn,
                                                      @RequestParam int quantity){
        try {
            ShoppingCart shoppingCart = this.shoppingCartService.addBookToCart(username, bookIsbn, quantity);
            return ResponseEntity.ok().body(shoppingCart);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/remove-book")
    public ResponseEntity<ShoppingCart> removeBookFromCart(@RequestParam String username,
                                                           @RequestParam String bookIsbn) {
        try {
            ShoppingCart shoppingCart = this.shoppingCartService.removeBookFromCart(username, bookIsbn);
            return ResponseEntity.ok().body(shoppingCart);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (BookNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update-quantity")
    public ResponseEntity<ShoppingCart> updateBookQuantity(@RequestParam String username,
                                                           @RequestParam String bookIsbn,
                                                           @RequestParam int quantity) {
        try {
            ShoppingCart shoppingCart = this.shoppingCartService.updateBookQuantity(username, bookIsbn, quantity);
            return ResponseEntity.ok().body(shoppingCart);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (BookNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/clear-cart/{username}")
    public ResponseEntity clearCart(@PathVariable String username) {
        try {
            this.shoppingCartService.clearCart(username);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/contents")
    public List<Book> getCartContents(@RequestParam String username) {
        return this.shoppingCartService.getCartContents(username);
    }

}
