/**
 * file name Library
 * date      10 May 2015
 * author    Stephen Drollinger
 * purpose   class to define the library
 *
 */

import java.util.HashMap;
import java.util.Map;

// Library class
public class Library {

    // Creating Library ArrayList<ArrayList>


    // Creating AuthorList/s
    // Creating BooksList
    private static Map<Integer, Author> libraryOfAuthors = new HashMap<Integer, Author>();
    private static Map<Integer, Book> authorsBooks = new HashMap<Integer, Book>();
    private static Map<Integer, Journal> authorsJournal = new HashMap<Integer, Journal>();
    private static String sortedLibrary;
    private static String searchedLibrary;

    // Constructor for Arraylist Author/Books
    public Library() {
    }

    @Override
    public String toString() {
        return "\n" + "authorList: " + libraryOfAuthors +
                "\n" + "authorsBooks: " + authorsBooks +
                "\n" + "authorsJournal: " + authorsJournal;
    }

    public static String sort(String sortBy) {
        sortedLibrary = SortAndSearchUtilities.getSortedLibraryString(sortBy, libraryOfAuthors, authorsBooks, authorsJournal);
        return sortedLibrary;
    }

    public static String search(String searchBy) {
        searchedLibrary = SortAndSearchUtilities.getSearchedItem(searchBy, libraryOfAuthors, authorsBooks, authorsJournal);
        return searchedLibrary;
    }

    /**
     * @return the libraryOfAuthors
     */
    public Map<Integer, Author> getLibraryOfAuthors() {
        return libraryOfAuthors;
    }

    /**
     * @param libraryOfAuthors the libraryOfAuthors to set
     */
    public void setLibraryOfAuthors(Map<Integer, Author> libraryOfAuthors) {
        Library.libraryOfAuthors = libraryOfAuthors;
    }

    /**
     * @return the authorsBooks
     */
    public Map<Integer, Book> getAuthorsBooks() {
        return authorsBooks;
    }

    /**
     * @param authorsBooks the authorsBooks to set
     */
    public void setAuthorsBooks(Map<Integer, Book> authorsBooks) {
        Library.authorsBooks = authorsBooks;
    }

    /**
     * @return the authorsJournal
     */
    public Map<Integer, Journal> getAuthorsJournal() {
        return authorsJournal;
    }

    /**
     * @param authorsJournal the authorsJournal to set
     */
    public void setAuthorsJournal(Map<Integer, Journal> authorsJournal) {
        Library.authorsJournal = authorsJournal;
    }

}
