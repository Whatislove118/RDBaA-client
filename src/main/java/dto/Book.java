package dto;

import java.util.Date;

public class Book {
    private Long id;

    private String name;

    private String authorName;

    private String genreName;

    private String annotation;

    private String isbn;

    private Date dateOfPublished;

    public Book() {
    }

    public Book(String name, String authorName, String genreName, String annotation, String isbn) {
        this.name = name;
        this.authorName = authorName;
        this.genreName = genreName;
        this.annotation = annotation;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }


    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getDateOfPublished() {
        return dateOfPublished;
    }

    public void setDateOfPublished(Date dateOfPublished) {
        this.dateOfPublished = dateOfPublished;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
