import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * Name and ID : Zaid Minhas 40243097
 * COMP 249
 *  Assignment 4
 *  Due Date: 17 April 2023
 */

public class Driver {

     // -----------------------------------------------------
     // Assignment 4
     // Part: 1
     // Written by: Zaid Minhas 40243097
     // -----------------------------------------------------
    public static void main(String[] args) throws FileNotFoundException {
	ArrayList<Book>  arrLst = getBooks();
	
	/**
	 * Create error outputfile
	 */
	WriteErrors(arrLst);
	
	/**
	 * Create and initialize book linked list
	 */
	System.out.println("YearError File Created");		
	BookList bkLst = new BookList();
	for (int i = arrLst.size() -1 ; i >= 0; i--) {
		bkLst.AddToStart(arrLst.get(i));
	}
	
	/**
	 * initial run 
	 */
	System.out.println("Here are the contents of the list");
	System.out.println("=================================");
	bkLst.displayContent();
	Scanner s = new Scanner(System.in);
	System.out.println();
	
	while (true) {
	    /**
	     * Display main menu and validation rules for choice
	     */
	    DisplayMenu();
	    System.out.print("Enter your selection: ");
	    int choice;
	    while (true) {
		try {
		    choice = s.nextInt();
		    if (choice < 1 || choice >8 ) {
			throw new InputMismatchException();
			}
		    break;
		    }
		catch (InputMismatchException IME) {
		    System.out.println("Incorrect input!");
		    System.out.println("Please choose between 1 to 8");
		    s.nextLine();
		    }
		}
	    switch (choice){
		
	    /**
	     * Create a txt file containing all the books from a year
	     */
	    case 1:
		int yr;
		while (true) {
		    
		    System.out.print("Enter the year who's records you'd like to extract: ");
		    try {
			yr = s.nextInt();
			break;
			}
		    catch(InputMismatchException IME) {
			System.out.println("Invalid year syntax entered");
			s.nextLine();
		    }
		}
				
		bkLst.storeRecordsByYear(yr);
		bkLst.displayContent();
		break;
            
		/**
		 * Delete all consecutively repeating records
		 */
	    case 2:
		bkLst.delConsecutiveRepeatedRecords();
		System.out.println("Here are some of the contents of the list after removing consecutive duplicates");
		System.out.println("================================================================================");
		bkLst.displayContent();
		break;

		/**
		 * Create a linked list of a specific author(s)
		 */
	    case 3:
		System.out.print("Please enter the name of the author to create an extracted list : ");
		s.nextLine();
		String auth = s.nextLine();
				
		BookList authList = bkLst.extractAuthList(auth);
		System.out.println("Here are the contents of the author list: " + auth);
		authList.displayContent();
		break;
		
		/**
		 * Insert a book before a given ISBN
		 */
	    case 4:
		Book b1;
		while (true) {
		    try {
			System.out.println("Enter the title of the book: ");
			s.nextLine();
			String title = s.nextLine();
			System.out.println("Enter the author's name: ");
			String author = s.nextLine();
			System.out.println("Enter the price: ");
			double price = s.nextDouble();
			System.out.println("Enter the ISBN: ");
			long isbn = s.nextLong();
			System.out.println("Enter the genre: ");
			s.nextLine();
			String genre =  s.nextLine();
			System.out.println("Enter the year of publication: ");
			int year = s.nextInt();
			b1 = new Book(title, author, price, isbn, genre, year);
			break;
		    }
		    catch(InputMismatchException IME) {
			System.out.println("Invalid item entered");
			}
		    }
				
		System.out.println("Please enter the ISBN you want to insert a node in before:");
		while (true) {
		    try {
			long ISBN = s.nextLong();
			if (bkLst.insertBefore(ISBN, b1)) {
			    bkLst.displayContent();
			}
			else {
			    System.out.println("ISBN could not be found");
			}
			break;
		    }
		    catch (InputMismatchException IME) {
			System.out.println("Invalid ISBN syntax entered");
			System.out.println("Enter ISBN");
			s.nextLine();
		    }
		}
		break;
		
		/**
		 * Insert a book between 2 ISBNS
		 */
	    case 5:
		Book b2;
		while (true) {
		    try {
			System.out.println("Enter the title of the book: ");
			s.nextLine();
			String title = s.nextLine();
			System.out.println("Enter the author's name: ");
			String author = s.nextLine();
			System.out.println("Enter the price: ");
			double price = s.nextDouble();
			System.out.println("Enter the ISBN: ");
			long isbn = s.nextLong();
			System.out.println("Enter the genre: ");
			s.nextLine();
			String genre =  s.nextLine();
			System.out.println("Enter the year of publication: ");
			int year = s.nextInt();
			b2 = new Book(title, author, price, isbn, genre, year);
			break;
		    }
		    catch(InputMismatchException IME) {
			System.out.println("Invalid item syntax entered");
		    }
		}
    
		Long ISBN1 = null;
		Long ISBN2 = null;
    
		while (true) {
		    try {
			System.out.println("Please enter the 2 ISBNs you want to insert a node in between");
			System.out.print("First ISBN: ");
			ISBN1 = s.nextLong();
			System.out.print("Second ISBN: ");
			ISBN2 = s.nextLong();
			if (bkLst.insertBetween(ISBN1, ISBN2, b2)) {
			    bkLst.displayContent();
			}
			else {
			    System.out.println("Not all ISBNs could be found");
			}
			break;
		    }
		    catch(InputMismatchException IME) {
			System.out.println("Invalid ISBN syntax entered");
		    }
		}
		break;

		/**
		 * Swap 2 ISBNs 
		 */
	    case 6:
		while (true) {
		    try {
			System.out.println("Enter two ISBN to swap their locations");
			System.out.print("First ISBN: ");
			long swap1 = s.nextLong();
			System.out.print("Second ISBN: ");
			long swap2 = s.nextLong();
			if (bkLst.swap(swap1, swap2)){
			    System.out.println("Record with ISBN, " + swap1 + " and " + swap2 + "were found and swapped. Here are the contents of the list are rearranging the record");
			    System.out.println("============================================================================================================");
			}
			else {
			    System.out.println("Not all ISBNs could be found");
			}
			bkLst.displayContent();
			break;
		    }
		    catch(InputMismatchException IME) {
			System.out.println("Invalid ISBN syntax entered");
			s.nextLine();
			}
		    }
			
		break;
		
		/**
		 * Create output file of all books in linked list
		 */
	    case 7:
		bkLst.commit();
		System.out.println("File Updated_Books.txt has been created");
		bkLst.displayContent();
		break;
		
		/**
		 * Terminate the program
		 */
	    case 8:
		System.out.println("Thank you for using the program");
		bkLst.displayContent();
		System.out.println("**Program terminated**");
		s.close();
		System.exit(0);
		break;
		}
	    }		
	}
	
    
    /**
     * Write the errors found in @param BookArray into a file.
     * @throws FileNotFoundException if file cannot be created
     */
	public static void WriteErrors(ArrayList<Book> BookArray) throws FileNotFoundException {
		ArrayList<Book> err = new ArrayList<Book>();
		for (int i = 0; i < BookArray.size(); i++) {
			Book book = BookArray.get(i);
			if (book.getYear() > 2023) {
				BookArray.remove(i);
				err.add(book);
			}
		}
		
		if (err.size() > 0) {
			FileOutputStream fos = new FileOutputStream("YearErr.txt");
			PrintWriter pw = new PrintWriter(fos);
			for (Book book : err) {
				pw.println(book);
			}
			pw.close();
		}
	}
	
	/**
	 * 
	 * create objects of type Book and @return a ArrayList with these books
	 * @throws FileNotFoundException
	 */
	public static ArrayList<Book> getBooks() throws FileNotFoundException {
		FileInputStream f = new FileInputStream("Books.txt");
		Scanner s = new Scanner(f);
		ArrayList<Book> b = new ArrayList<Book>();
		while (s.hasNextLine()) {
			String text = s.nextLine();
			String[] splitBook = text.split(",");
			
			String title = splitBook[0];
			String author = splitBook[1];
			double price = Double.parseDouble(splitBook[2]);
			long isbn = Long.parseLong(splitBook[3]);
			String genre =  splitBook[4];
			int year = Integer.parseInt(splitBook[5]);
			Book book = new Book(title, author, price, isbn, genre, year);
			
			b.add(book);
		}
		s.close();
		return b;
	}

	/**
	 * Display the main menu options
	 */
	public static void DisplayMenu() {
		System.out.println("Tell me what you want to do? Let's Chat since this is trending now! Here are some options:");
		System.out.println("\t1) Give me a year # and I would extract all records of that year and store them in a file for that year;\n"
				+ "\t2) Ask me to delete all consecutive repeated records;\n"
				+ "\t3) Give me an author name and I will create a new list with the records of this author and display them;\n"
				+ "\t4) Give me an ISBN number and a Book object, and I will insert Node with the book before the record with this ISBN;\n"
				+ "\t5) Give me 2 ISBN numbers and a Book object, and I will insert a Node between them, if I find them!\n"
				+ "\t6) Give me 2 ISBN numbers and I will swap them in the list for rearrangement of records; of course if they exist!\n"
				+ "\t7) Tell me to COMMIT! Your command is my wish. I will commit your list to a file called Updated_Books;\n"
				+ "\t8) Tell me to STOP TALKING. Remember, if you do not commit, I will not!");
		
	}
	
}
