/**
 * 
 * Name and ID : Zaid Minhas 40243097
 * COMP 249
 *  Assignment 4
 *  Due Date: 17 April 2023
 */
import java.io.Serializable;

public class Book implements Serializable {
	// -----------------------------------------------------
	// Assignment 4
	// Question: 1
	// Written by: Zaid Minhas 40243097
	// -----------------------------------------------------
    private String title;
    private String authors;
    private double price;
    private long isbn;
    private String genre;
    private int year;

    /**
     * Default constructor
     */
    public Book(){}
    
    
    /**
     * Parameterized constructor
     * @param title
     * @param authors
     * @param price
     * @param isbn
     * @param genre
     * @param year
     */
    public Book(String title, String authors, double price, long isbn, String genre, int year) {
        this.title = title;
        this.authors = authors;
        this.price = price;
        this.isbn = isbn;
        this.genre = genre;
        this.year = year;
    }

    public String toString(){
        return title + ", " + authors + ", " + price + ", " + isbn + ", " + genre + ", " + year;
    }
    
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (this.getClass() != obj.getClass() || obj == null){
            return false;
        }
        Book b = (Book)obj;
        return (title.equals(b.title) && authors.equals(b.authors) && price == b.price && isbn == b.isbn && genre.equals(b.genre) && year == b.year);
    }

    /**
     * All getters and setters
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
