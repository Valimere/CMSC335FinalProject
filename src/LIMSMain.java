
/**
 * file name LIMSMain
 * date      10 May 2015
 * author    Stephen Drollinger
 * purpose   main program that actively puts data into correct Library type
 *
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LIMSMain {

    // ArrayList of the input file
    static private ArrayList<String> arrayOfInput = new ArrayList<String>();
    // ArrayList of lines in file that are not expected
    static private ArrayList<String> arrayOfUnexpectedInput = new ArrayList<String>();
    // String of file chosen
    static public String fileChoice;
    // Library with both Authors and book Array Lists
    static public Library mainLibrary = new Library();

    // Default constructor s
    public LIMSMain(ArrayList<String> aL1, ArrayList<String> aLO1, String fc,
                    ArrayList<Book> bk) {
        arrayOfInput = aL1;
        arrayOfUnexpectedInput = aLO1;
        fileChoice = fc;
    }

    public static void main(String[] args) throws ParseException {
        // requesting user for input file
        System.out.println("Gethering file location from user");
        System.out.println("");
        SimpleFileChooser sfc = new SimpleFileChooser();
        // loop to only show file choice while a choice is not made
        while (fileChoice == null) {
            sfc.setVisible(true);
            fileChoice = sfc.getFileChoice();
        }
        sfc.setVisible(false);

        System.out.println("Your file location: ");
        System.out.println(fileChoice);
        System.out.println("");

        // assigning inputFile array into local array
        FileReaderLIMS fr = new FileReaderLIMS(sfc, arrayOfInput);
        arrayOfInput = fr.getInputFile();
        // Printing input file to console to show input correctly
        System.out.println("Your file's contents are:");
        FileReaderLIMS.printInputFile();
        System.out.println("");
        // loop going through Arraylist of input file assigning data correctly
        putDataIntoLIMS();

        if (arrayOfUnexpectedInput.size() > 0) {
            System.out.println("Unexpected lines found: ");
            System.out.println(arrayOfUnexpectedInput);
        }

        // Opening LIMS GUI
        LibraryGui LG = new LibraryGui(mainLibrary);
        LG.setVisible(true);


        //System.out.println(authorSearch("John Smith"));

        // ensuring program is closed
        //System.exit(0);
    }

    // loop to put data into the LIMS
    public static void putDataIntoLIMS() throws ParseException {

        Thread putDataIntoLims = new Thread(new Runnable() {

            @Override
            public void run() {


                // Journal Data fields
                int journalIndex;
                String journalTitle;
                String journalGenre;
                double journalPrice;
                int journalAuthorIndex;
                Calendar datePublished = GregorianCalendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                int journalIssue;

                // Book Data fields
                int bookIndex;
                double bookPrice;
                String bookTitle;
                String bookGenre;
                int bookAuthor_Index;

                // Author Data fields
                int authorIndex;
                String authorName;
                String authorAddress;

                // creating a temp list for the extras
                List<String> extrasList = new ArrayList<String>();

                System.out.println("----Inputting Data into system: ----");
                // iterating through each Array List entry
                System.out.println("arrayOfInput's size" + arrayOfInput.size());
                for (
                        int i = 0;
                        i < arrayOfInput.size(); ++i)

                {
                    // temporary String to put each line of the ArrayList to work with
                    String tempLine = arrayOfInput.get(i);
                    //System.out.println("Line being Looked at: " + tempLine);

                    // Checking for null values
                    if (tempLine.matches("")) {
                        System.out.println("Blank found" + tempLine);
                        arrayOfUnexpectedInput.add(tempLine);
                        System.out.println("");

                        // checking for comments
                    } else if (tempLine.charAt(0) == '/' && tempLine.charAt(1) == '/') {
                        System.out.print("Comment found: ");
                        System.out.println(tempLine);
                        System.out.println("");

                        // Checking for books
                    } else if (tempLine.charAt(0) == 'b' && tempLine.charAt(1) == ' ' && tempLine.charAt(2) == ':') {
                        System.out.println("Book found:");

                        // Breaking down String into an arrayList of strings
                        List<String> list = new ArrayList<String>(Arrays.asList(tempLine.split("\\s*:\\s*")));
                        System.out.println("broken out arraylist: " + list);

                        // loading fields
                        bookIndex = Integer.parseInt(list.get(1));
                        System.out.println("bookIndex = " + bookIndex);
                        bookTitle = list.get(2);
                        System.out.println("bookTitle = " + bookTitle);
                        bookGenre = list.get(3);
                        System.out.println("bookGenre = " + bookGenre);
                        bookPrice = Double.parseDouble(list.get(4));
                        System.out.println("bookPrice = " + bookPrice);
                        bookAuthor_Index = Integer.parseInt(list.get(5));
                        System.out.println("bookAuthor_Index = " + bookAuthor_Index);

                        // Saving off extra fields for later inspection and possible inclusion
                        for (int n = 6; n < list.size(); ++n) {
                            extrasList.add(list.get(n));
                            System.out.println("Extra field: " + extrasList.get(n - 6));
                        }

                        // adding book to Library
                        Book book1 = new Book(bookIndex, bookTitle, bookGenre,
                                bookPrice, bookAuthor_Index, extrasList);

                        mainLibrary.getAuthorsBooks().put(bookIndex, book1);

                        System.out.println("");

                        // Checking for authors
                    } else if (tempLine.charAt(0) == 'a' && tempLine.charAt(1) == ' ' && tempLine.charAt(2) == ':') {
                        System.out.println("Author found:");

                        List<String> list = new ArrayList<String>(Arrays.asList(tempLine.split("\\s*:\\s*")));
                        System.out.println("broken out arraylist: " + list);

                        // loading fields
                        authorIndex = Integer.parseInt(list.get(1));
                        System.out.println("authorIndex = " + authorIndex);
                        authorName = list.get(2);
                        System.out.println("authorName = " + authorName);
                        authorAddress = list.get(3);
                        System.out.println("authorAddress = " + authorAddress);

                        // Saving off extra fields for later inspection and possible inclusion
                        for (int n = 4; n < list.size(); ++n) {
                            extrasList.add(list.get(n));
                            System.out.println("Extra field: " + extrasList.get(n - 4));
                        }

                        // adding author to Library
                        Author author1 = new Author(authorIndex, authorName, authorAddress, extrasList);
                        System.out.println("");

                        mainLibrary.getLibraryOfAuthors().put(authorIndex, author1);
                        System.out.println("author to library :" + author1);

                    } else if (tempLine.charAt(0) == 'j' && tempLine.charAt(1) == ' ' && tempLine.charAt(2) == ':') {
                        System.out.println("Journal found:");

                        List<String> list = new ArrayList<String>(Arrays.asList(tempLine.split("\\s*:\\s*")));
                        System.out.println("broken out arraylist: " + list);

                        // loading fields
                        journalIndex = Integer.parseInt(list.get(1));
                        System.out.println("journalIndex = " + journalIndex);
                        journalTitle = list.get(2);
                        System.out.println("journalTitle = " + journalTitle);
                        journalGenre = list.get(3);
                        System.out.println("journalGenre = " + journalGenre);
                        journalPrice = Double.parseDouble(list.get(4));
                        System.out.println("journalPrice = " + journalPrice);
                        journalAuthorIndex = Integer.parseInt(list.get(5));
                        System.out.println("journalAuthorIndex = " + journalAuthorIndex);
                        try {
                            datePublished.setTime(sdf.parse(list.get(6)));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        System.out.println("datePublished = " + datePublished);
                        journalIssue = Integer.parseInt(list.get(7));
                        System.out.println("journalIssue = " + journalIssue);

                        // Saving off extra fields for later inspection and possible inclusion
                        for (int n = 8; n < list.size(); ++n) {
                            extrasList.add(list.get(n));
                            System.out.println("Extra field: " + extrasList.get(n - 4));
                        }

                        System.out.println("journal date test: " + Journal.dateToString(datePublished));

                        // adding journal to Library
                        Journal journal1 = new Journal(journalIndex, journalTitle, journalGenre, journalPrice, journalAuthorIndex, datePublished, journalIssue);
                        System.out.println("");
                        System.out.println("testing date published isn't null" + datePublished);
                        System.out.println(journal1.toString());

                        mainLibrary.getAuthorsJournal().put(journalIndex, journal1);
                        //System.out.println("Journals steve look here: " + );
                    } else {

                        //Checking for anything else and putting them into an unexpected ArrayList
                        System.out.println("Unexpected Line found: " + tempLine);
                        arrayOfUnexpectedInput.add(tempLine);
                        System.out.println("");
                    }

                    //closing of loop going through arrayOfInput
                }

                // Displaying entire library
                System.out.println("Library includes: " + mainLibrary);

            }

            // closing of putDataIntoLims() method
        });
        putDataIntoLims.start();
    }


}
