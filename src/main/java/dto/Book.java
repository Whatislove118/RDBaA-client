package dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@ToString
@RequiredArgsConstructor


public class Book {
    private Long id;

    private String name;

    private String authorName;

    private String genreName;

    private String annotation;

    private String isbn;

    private Date dateOfPublished;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorName='" + authorName + '\'' +
                ", genreName='" + genreName + '\'' +
                ", annotation='" + annotation + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
