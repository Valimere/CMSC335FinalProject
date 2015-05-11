/**
 * file name Book
 * date      10 May 2015
 * author    Stephen Drollinger
 * purpose   Class to hold book properties
 *
 */

import java.util.List;

// Defining Book
public class Book {

    // Setting Book Specific Variables

    private int index;
    private String title;
    private String genre;
    private double price;
    private int author_index;
    private int book_inventory;

    private int book_inventory_maximum;
    private List<String> extras;

    // constructing Book bringing in super's properties
    public Book(int index, String title, String genre, double price,
                int author_index, int book_Inventory, int book_inventory_maximum, List<String> extras) {

        // bringing in book specific properties
        this.index = index;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.author_index = author_index;
        this.book_inventory = book_Inventory;
        this.book_inventory_maximum = book_inventory_maximum;
        this.extras = extras;

    }

    // constructing Book bringing in super's properties
    public Book(Book book) {

        // bringing in book specific properties
        this.index = book.index;
        this.title = book.title;
        this.genre = book.genre;
        this.price = book.price;
        this.author_index = book.author_index;
        this.book_inventory = book.book_inventory;
        this.extras = book.extras;

    }

    public String toString() {
        return "\n" + "Index: " + getIndex() + "\n" +
                "Title: " + getTitle() + "\n" + "Genre: " + getGenre() + "\n" +
                "Price: $" + getPrice() + "\n" + "Author Index: " + getAuthor_Index() + "\n" +
                "Book Inventory: " + getBook_inventory() + "\n" +
                "Book Inventory Maximum: " + getBook_inventory_maximum() + "\n";
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    public int getBook_inventory() {
        return book_inventory;
    }

    public void setBook_inventory(int book_inventory) {
        this.book_inventory = book_inventory;
    }
    public int getBook_inventory_maximum() {
        return book_inventory_maximum;
    }

    public void setBook_inventory_maximum(int book_inventory_maximum) {
        this.book_inventory_maximum = book_inventory_maximum;
    }
    /**
     * @return the author_index
     */
    public int getAuthor_Index() {
        return author_index;
    }

    /**
     * @param author_index the author_index to set
     */

    public void setAuthor_Index(int author_index) {
        this.author_index = author_index;
    }

    /**
     * @return the extras
     */
    public List<String> getExtras() {
        return extras;
    }

    /**
     * @param extras the extras to set
     */
    public void setExtras(List<String> extras) {
        this.extras = extras;
    }


}
