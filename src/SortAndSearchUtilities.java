/**
 * file name SortAndSearchUtilities
 * date      10 May 2015
 * author    Stephen Drollinger
 * purpose   User sends Library to this class to be sorted in user defined variable (way)
 *               Added ability for Search/sort to be thread safe
 */

import java.util.*;

public class SortAndSearchUtilities {

    public static String sortedLibrary = "";
    public static String searchedLibrary = "";


    public static String getSearchedItem(final String searchLibraryBy,
                                         final Map<Integer, Author> libraryOfAuthors, final Map<Integer, Book> authorsBooks,
                                         final Map<Integer, Journal> authorsJournal) {

        System.out.println("entering search method looking for" + searchLibraryBy);
        int currentAuthorIndex;
        ArrayList<Integer> listOfKeys = new ArrayList<Integer>(libraryOfAuthors.keySet());


        // Searching for authors and displaying the author + their books + Journals, works with multiple authors found
        for (int authorKey : listOfKeys)

        {
            System.out.println("entering Author for loop looking for" + searchLibraryBy);
            Author currentAuthor = libraryOfAuthors.get(authorKey);
            if (currentAuthor.getName().contains(searchLibraryBy)) {
                searchedLibrary = searchedLibrary.concat(currentAuthor.toString());
                currentAuthorIndex = currentAuthor.getIndex();

                ArrayList<Integer> listOfBookKeys = new ArrayList<Integer>(authorsBooks.keySet());
                for (int bookKey : listOfBookKeys) {
                    System.out.println("entering Book for loop looking for" + searchLibraryBy);
                    Book currentBook = authorsBooks.get(bookKey);
                    if (currentBook.getAuthor_Index() == currentAuthorIndex) {
                        searchedLibrary = searchedLibrary.concat(currentBook.toString());
                    }
                }
                ArrayList<Integer> listOfJournalKeys = new ArrayList<Integer>(authorsJournal.keySet());
                for (int journalKey : listOfJournalKeys) {
                    System.out.println("entering Journal for loop looking for" + searchLibraryBy);
                    Journal currentJournal = authorsJournal.get(journalKey);
                    System.out.println("Journal being looked for:" + currentAuthorIndex);
                    System.out.println("Journal being looked at:" + currentJournal.getAuthorIndex());
                    if (currentJournal.getAuthorIndex() == currentAuthorIndex) {
                        searchedLibrary = searchedLibrary.concat(currentJournal.toString());
                    }
                }
            }
            ArrayList<Integer> listOfJournalKeys = new ArrayList<Integer>(authorsJournal.keySet());
        }

        // Searching book titles
        ArrayList<Integer> listOfBookKeys = new ArrayList<Integer>(authorsBooks.keySet());
        for (
                int bookKey
                : listOfBookKeys)

        {
            System.out.println("entering Book for loop looking for" + searchLibraryBy);
            Book currentBook = authorsBooks.get(bookKey);
            if (currentBook.getTitle().contains(searchLibraryBy)) {
                searchedLibrary = searchedLibrary.concat(currentBook.toString());
            }
        }

        if (searchedLibrary.equals(null))

        {
            searchedLibrary = "Searched for: " + searchLibraryBy + " Item not found";
        }
        return searchedLibrary;

    }

    public static String getSortedLibraryString(String sortLibraryBy,
                                                Map<Integer, Author> libraryOfAuthors, Map<Integer, Book> authorsBooks,
                                                Map<Integer, Journal> authorsJournal) {

        ArrayList<String> tempArrayStr = new ArrayList<String>();
        ArrayList<Integer> tempArrayInt = new ArrayList<Integer>();
        Integer tempKey;

        System.out.println("I'm going to sort the Library by :");
        System.out.println(sortLibraryBy);

        Iterator authorIterator = libraryOfAuthors.entrySet().iterator();
        // Sorting by Author - Index

        if (sortLibraryBy.equals("Author - Index")) {
            sortedLibrary = "";
            // iterating over the pairs
            while (authorIterator.hasNext()) {
                Map.Entry pairs = (Map.Entry) authorIterator.next();
                tempKey = (Integer) pairs.getKey();
                tempArrayInt.add(tempKey);
            }
            // concatinating sorted list to sorted string to be displayed.
            for (int j = 0; j < tempArrayInt.size(); j++) {
                System.out.println("");

                // Adding author to sorted list
                sortedLibrary = sortedLibrary.concat("\nAuthor: " + libraryOfAuthors.get(tempArrayInt.get(j)).toString());
                System.out.println("");

                // Adding authors books beneath the author
                sortedLibrary = sortedLibrary.concat(booksByAuthor(tempArrayInt.get(j), authorsBooks));
                // Adding authors journals beneath the author
                sortedLibrary = sortedLibrary.concat(journalsByAuthor(tempArrayInt.get(j), authorsJournal));
            }
        }
        // Sorting by Author - Name
        else if (sortLibraryBy.equals("Author - Name")) {
            sortedLibrary = "";
            // iterating over the pairs
            while (authorIterator.hasNext()) {

                Map.Entry pairs = (Map.Entry) authorIterator.next();
                String authorName = libraryOfAuthors.get(pairs.getKey()).getName()
                        + " : " + libraryOfAuthors.get(pairs.getKey()).getIndex();
                System.out.println("authorName = " + authorName);
                // reversing order to sort by last name
                authorName = reverseNameOrder(authorName);
                tempArrayStr.add(authorName);
            }
            System.out.println("tempArray = " + tempArrayStr);

            // sorting by last name
            tempArrayStr.sort(null);
            System.out.println("tempArray before reversal = " + tempArrayStr);

            // getting author index of sorted list
            getSortedAuthorIndex(tempArrayInt, tempArrayStr);

            for (int j = 0; j < tempArrayInt.size(); j++) {
                sortedLibrary = sortedLibrary.concat("\nAuthor: " + libraryOfAuthors.get(tempArrayInt.get(j)).toString());
                // Adding authors books beneath the author
                sortedLibrary = sortedLibrary.concat(booksByAuthor(tempArrayInt.get(j), authorsBooks));
                // Adding authors journals beneath the author
                sortedLibrary = sortedLibrary.concat(journalsByAuthor(tempArrayInt.get(j), authorsJournal));
            }
            System.out.println("");
            //getting auther index number
            sortedLibrary = sortedLibrary.concat(tempArrayStr.toString());
        } else if (sortLibraryBy.equals("Book - Title")) {
            sortedLibrary = "";
            List sortedBooks;
            sortedBooks = getSortedBookByPrice(authorsBooks);
            sortedLibrary = sortedLibrary.concat(sortedBooks.toString());
            System.out.println("");
        } else if (sortLibraryBy.equals("Book - Price")) {
            sortedLibrary = "";
            List sortedBooks;
            sortedBooks = getSortedBookByTitle(authorsBooks);
            sortedLibrary = sortedLibrary.concat(sortedBooks.toString());
            System.out.println("");
        } else if (sortLibraryBy.equals("Book - Index")) {
            sortedLibrary = "";
            List sortedBooks;
            sortedBooks = getSortedBookByBookIndex(authorsBooks);
            sortedLibrary = sortedLibrary.concat(sortedBooks.toString());
            System.out.println("");
        } else if (sortLibraryBy.equals("Journal - Date")) {
            sortedLibrary = "";
            List sortedBooks;
            sortedBooks = getSortedBookByJournalDate(authorsJournal);
            sortedLibrary = sortedLibrary.concat(sortedBooks.toString());
            System.out.println("");
        } else if (sortLibraryBy.equals("Journal - Issue")) {
            sortedLibrary = "";
            List sortedBooks;
            sortedBooks = getSortedBookByJournalIssue(authorsJournal);
            sortedLibrary = sortedLibrary.concat(sortedBooks.toString());
            System.out.println("");
        }

        System.out.println("the String of the sorted Library is :");
        System.out.println(sortedLibrary);
        return sortedLibrary;
    }

    private static String booksByAuthor(final int authorIndex, final Map<Integer, Book> authorsBooks) {

        final Iterator bookIterator = authorsBooks.entrySet().iterator();
        final String[] booksAuthor = {""};
        final String[] value = {""};
        new Thread(new Runnable() {

            public void run() {

                while (bookIterator.hasNext()) {
                    Map.Entry pairs = (Map.Entry) bookIterator.next();
                    int booksAuthorIndex = authorsBooks.get(pairs.getKey()).getAuthor_Index();
                    System.out.println("authorIndex = " + authorIndex);
                    System.out.println("booksAuthorIndex = " + booksAuthorIndex);
                    if (authorIndex == booksAuthorIndex) {
                        booksAuthor[0] += "\n Book: ";
                        value[0] = pairs.getValue().toString();
                        System.out.println("Found match: " + value[0]);
                        booksAuthor[0] += value[0];
                        System.out.println("booksAuthor = " + booksAuthor[0]);

                    } else {
                        System.out.println("Book not here");
                    }

                }
                System.out.println("Books for author found = " + booksAuthor[0]);
            }
        });
        return booksAuthor[0];
    }

    private static String journalsByAuthor(final int authorIndex, final Map<Integer, Journal> authorsJournals) {

        final Iterator journalIterator = authorsJournals.entrySet().iterator();
        final String[] journalsAuthor = {""};
        final String[] value = {""};
        new Thread(new Runnable() {

            public void run() {

                while (journalIterator.hasNext()) {
                    Map.Entry pairs = (Map.Entry) journalIterator.next();
                    int journalsAuthorIndex = authorsJournals.get(pairs.getKey()).getAuthorIndex();
                    System.out.println("authorIndex = " + authorIndex);
                    System.out.println("journalsAuthorIndex = " + journalsAuthorIndex);
                    if (authorIndex == journalsAuthorIndex) {
                        journalsAuthor[0] += "\n Journal: ";
                        value[0] = pairs.getValue().toString();
                        System.out.println("Found match: " + value[0]);
                        journalsAuthor[0] += value[0];
                        System.out.println("journalsAuthor = " + journalsAuthor[0]);

                    } else {
                        System.out.println("Book not here");
                    }

                }
                System.out.println("Books for author found = " + journalsAuthor[0]);
            }
        });
        return journalsAuthor[0];
    }

    private static void getSortedAuthorIndex(ArrayList<Integer> authorsIndexList, ArrayList<String> authorsList) {

        for (int i = 0; i < authorsList.size(); i++) {
            String name = authorsList.get(i);
            name = reverseNameOrder(name);
            authorsList.set(i, name);
            List<String> list = new ArrayList<String>(Arrays.asList(name.split("\\s*:\\s*")));
            int authorIndexValue = Integer.parseInt(list.get(1));
            authorsIndexList.add(authorIndexValue);
            System.out.println("authorIndexValue = " + authorIndexValue);
        }
    }

    private static List getSortedBookByTitle(final Map bookMap) {
        final List list = new LinkedList(bookMap.values());
        new Thread(new Runnable() {

            public void run() {
                System.out.println("Before Sorting: " + list);
                // Custom Comparator
                Collections.sort(list, new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        Book b1 = (Book) o1;
                        Book b2 = (Book) o2;
                        return ((Comparable) b1.getTitle()).compareTo(b2.getTitle());
                    }
                });
                System.out.println("");
                System.out.println("After Sorting: " + list);
            }
        });
        return list;
    }

    private static List getSortedBookByPrice(final Map bookMap) {
        final List list = new LinkedList(bookMap.values());
        new Thread(new Runnable() {

            public void run() {
                System.out.println("Before Sorting: " + list);
                // Custom Comparator
                Collections.sort(list, new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        Book b1 = (Book) o1;
                        Book b2 = (Book) o2;
                        return ((Comparable) b1.getPrice()).compareTo(b2.getPrice());
                    }
                });
                System.out.println("");
                System.out.println("After Sorting: " + list);
            }
        });
        return list;
    }

    private static List getSortedBookByBookIndex(final Map bookMap) {
        final List list = new LinkedList(bookMap.values());
        new Thread(new Runnable() {

            public void run() {
                System.out.println("Before Sorting: " + list);
                // Custom Comparator
                Collections.sort(list, new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        Book b1 = (Book) o1;
                        Book b2 = (Book) o2;
                        return ((Comparable) b1.getIndex()).compareTo(b2.getIndex());
                    }
                });
                System.out.println("");
                System.out.println("After Sorting: " + list);
            }
        });
        return list;
    }

    private static List getSortedBookByJournalDate(final Map bookMap) {
        final List list = new LinkedList(bookMap.values());
        new Thread(new Runnable() {

            public void run() {
                System.out.println("Before Sorting: " + list);
                // Custom Comparator
                Collections.sort(list, new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        Journal j1 = (Journal) o1;
                        Journal j2 = (Journal) o2;
                        return ((Comparable) j1.getJournalDatePublished()).compareTo(j2.getJournalDatePublished());
                    }
                });
                System.out.println("");
                System.out.println("After Sorting: " + list);
            }
        });
        return list;
    }

    private static List getSortedBookByJournalIssue(final Map bookMap) {
        final List list = new LinkedList(bookMap.values());
        new Thread(new Runnable() {

            public void run() {
                System.out.println("Before Sorting: " + list);
                // Custom Comparator
                Collections.sort(list, new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        Journal j1 = (Journal) o1;
                        Journal j2 = (Journal) o2;
                        return ((Comparable) j1.getIssue()).compareTo(j2.getIssue());
                    }
                });
                System.out.println("");
                System.out.println("After Sorting: " + list);
            }
        });
        return list;
    }

    private static String reverseNameOrder(String fullName) {
        String[] splitFullName = fullName.split(" ");
        return splitFullName[1] + " " + splitFullName[0] + " : " + splitFullName[3];
    }


}
