
/**
 * File Client Date: 5/8/2015 Author: Stephen Drollinger Purpose: Client checks
 * out and returns items
 */
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Client extends Library {

    //private static Map<Integer, Author> libraryOfAuthors = new ConcurrentHashMap<Integer, Author>();
    //private static Map<Integer, Book> authorsBooks = new ConcurrentHashMap<Integer, Book>();
    //private static Map<Integer, Journal> authorsJournal = new ConcurrentHashMap<Integer, Journal>();
    // Constructor for Arraylist Author/Books
    public Client() {
    }

    @Override
    public String toString() {
        return "\n" + "authorList: " + getLibraryOfAuthors()
                + "\n" + "authorsBooks: " + getAuthorsBooks()
                + "\n" + "authorsJournal: " + getAuthorsJournal();
    }
    

}
