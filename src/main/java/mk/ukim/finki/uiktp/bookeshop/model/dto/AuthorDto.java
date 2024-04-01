package mk.ukim.finki.uiktp.bookeshop.model.dto;

import lombok.Data;

@Data
public class AuthorDto {
    private String name;
    private String surname;
    private String birthYear;

    public AuthorDto(){

    }

    public AuthorDto(String name, String surname, String birthYear) {
        this.name = name;
        this.surname = surname;
        this.birthYear = birthYear;
    }
}
