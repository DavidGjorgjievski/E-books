package mk.ukim.finki.uiktp.bookeshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String birthYear;

    public Author(){

    }

    public Author(String name, String surname, String birthYear) {
        this.name = name;
        this.surname = surname;
        this.birthYear = birthYear;
    }
}
