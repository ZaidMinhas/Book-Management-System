import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * 
 * Name and ID : Zaid Minhas 40243097
 * COMP 249
 *  Assignment 4
 *  Due Date: 17 April 2023
 */



public class BookList {
    // -----------------------------------------------------
    // Assignment 4
    // Part: 1
    // Written by: Zaid Minhas 40243097
    // -----------------------------------------------------
	Node head;
	
	/**
	 * Constructor initializes head to null 
	 */
	public BookList() {
		head = null;
	}
	
	/**
	 * Book passed as parameter will become new head
	 * @param b
	 */
	public void AddToStart(Book book) {
		/**
		 *  If linked list is empty, then make head link to head
		 */
		if (head == null) {
			head  = new Node(book, head);
			head.next = head;
		}
		else {
			/**
			 * Create new head and link tail to head
			 */
			Node last = findLastNode();
			head  = new Node(book, head);
			last.next = head;
		}
	}

	/**
	 * Add @param book to the end of the linked list 
	 * This is done by using the findLastNode method and using.next = new node( @param book, head);
	 */
	public void addToLast(Book book) {
		
		if (head == null) {
			AddToStart(book);
		}
		else {
			Node last = findLastNode();
			last.next = new Node(book, head);
		}
	}
	/**
	 * @return the tail of the linked list by checking which nodes next node is the head
	 * This is done by running a loop that stops when curr is head so prev must be the tail
	 */
	private Node findLastNode(){ 
		Node prev = head;
		Node curr = head.next;  
		/**
		 *  head is not null so head.next works
		 */
		while(curr != head ){
			/**
			 *  If curr is head then prev is last element
			 */
			prev = curr; 
			curr = curr.next;
			
		}
		return prev; 
		/**
		 * prev’ references the last node;
		 */
	}
	
	
	/**
	 * Finds all books published in the year @param yr
	 * file name yr.txt, example 2019.txt
	 * if no records found, no file created 
	 * @throws FileNotFoundException 
	 */
	public void storeRecordsByYear(int yr) throws FileNotFoundException {
		ArrayList<Book> yearBook = new ArrayList<Book>();
		
		Node temp = head;
		while (true) {
			Book b = temp.getBook();
			int year = b.getYear();
			if (year == yr) {
				yearBook.add(b);
			}
			
			if (temp.next == head) {
				break;
			}
			temp = temp.next;
		}
		
		if (yearBook.size() > 0) {
			FileOutputStream fos = new FileOutputStream("Years/" + yr + ".txt");
			PrintWriter pw = new PrintWriter(fos);
			for (Book i : yearBook) {
				pw.println(i);
			}
			pw.close();
			System.out.println("File " + yr + ".txt created" );
		}
		else {
			System.out.println("No books of year " + yr + " could be found" );
		}
	}
	
	/**
	 * search through list for first occurrence of book object with ISBN @param isbn
	 * and if found, it will insert a new node with book @param b before it and @return true
	 * else do nothing and @return false
	 */
	public boolean insertBefore(long isbn, Book b) {
		Node prev = head;
		Node curr = head;
		
		while (true) {
			Book book = curr.getBook();
			if (book.getIsbn() == isbn) {
				if (curr == head) {
					AddToStart(b);
				}
				else {
					prev.next = new Node(b, curr);
				}
				
				return true;
				
			}
			prev = curr;
			curr = curr.next;
			
			if (curr == head) {
				return false;
			}
		}
		
	}
	
	/**
	 * Search first occurrence of first two consecutive books 
	 * with ISBN @param isbn1 and @param isbn2
	 * and then inserts Book @param b between them
	 * finally @return true but if nothing has been inserted
	 * then @return false
	 */
	public boolean insertBetween(long isbn1, long isbn2, Book b) {
		Node prev = findLastNode();
		Node curr = head;
		
		while (true) {
			Book book1 = prev.getBook();
			Book book2 = curr.getBook();
			
			if (book1.getIsbn() == isbn1 && book2.getIsbn() == isbn2) {
				prev.next = new Node(b, curr);
				return true;	
			}

			prev = curr;
			curr = curr.next;
			
			if (curr == head) {
				return false;
			}
		}

	}
//	
	/**
	 * Find all consecutive  repeated books and remove them leaving one 
	 * @return
	 */
	public boolean delConsecutiveRepeatedRecords() {
		Node temp = head;
		if (temp == null)
			return false;
		
		while (true) {
			Node check = temp.next;
			while (check != temp) {
				if (!temp.getBook().equals(check.getBook())) {
					break;
				}
				if (check == head) {
					head = check.next;
				}
				check = check.next;
			}
			temp.next = check;	
			
			if (temp == null || temp.next == head) {
				break;
			}
			temp = temp.next;
		}
		return true;
	}
	
	/**
	 * Display the books in the list
	 * seperated by "==>\n"
	 * with last line "==> head"
	 * 
	 */
	public void displayContent() {
		
		Node temp = head;
		if (temp != null) {
			while (true) {
				System.out.println(temp.getBook() + " ==>");
				if (temp.next == head) {
					break;
				}
				temp = temp.next;
				
			}
			System.out.println("==> head");
		}
		
	}
	
	/**
	 * @return a new BookList that have @param aut as an author
	 */
	public BookList extractAuthList(String aut) {
		BookList AuthList = new BookList();
		Node temp = head;
		while (true) {
			Book b = temp.getBook();
			if (aut.compareTo(b.getAuthors()) == 0) {
				AuthList.addToLast(b);
			}
			if (temp.next == head) {
				break;
			}
			temp = temp.next;
		}
		return AuthList;
		
		
	}

	/**
	 * Swap 2 nodes that have the ISBNs @param isbn1 and @param isbn2
	 * and @return true if nodes have been successfully swapped else false
	 */
	public boolean swap(long isbn1, long isbn2) {
	    /**
	     * For both Nodes to be swapped
	     * We are interested in the previous node as well as the node containing the ISBN itself
	     *  prev1 -> curr1 (curr1 contains isbn1)
	     *  prev2 -> curr2 (curr1 contains isbn2)
	     */
	    Node prev1 = null;
	    Node curr1 = null;
		
	    Node prev2 = null;
	    Node curr2 = null;
	
	    /**
	     * These variables will be used to cycle through the linked list
	     * curr is also prev.next
	     */
	    Node prev = findLastNode();
	    Node curr = head;
	
	    while (true) {
		Book b = curr.getBook();

		//===========================================
		/**
		 * The first condition checks if this node contains the ISBN
		 * the second condition checks if this is the first occurrence or not
		 * if not then it should not be null since the code assigns a value to it on first occurrence
		 */
		if (b.getIsbn() == isbn1 && prev1 == null) {
		    prev1 = prev;
		    curr1 = prev.next;
		    }
			
		if (b.getIsbn() == isbn2 && prev2 == null) {
		    prev2 = prev;
		    curr2 = prev.next;
		}
		//===========================================
		
		/**
		 * This condition runs when both ISBNs have been found
		 * we can also use curr1 and curr2 instead
		 */
		if (prev1 != null && prev2 != null) {
		    
		    /**
		     * If both ISBNs exist and are same then swapping wont make any changes
		     */
		    if (isbn1 == isbn2) {
			return true;
			}
		    
		    /**
		     * The code runs fine if the node after isbn1 contains isbn2
		     * however there is a logic error when the node after isbn2 contains isbn1
		     * which removes one of the nodes completely
		     * a way to avoid this is to just swap prev1, prev2 and curr1, curr2
		     */
		    if (prev2 == prev1.next) {
			Node temp = prev1;
			prev1 = prev2;
			curr1 = prev2.next;
				
			prev2 = temp;
			curr2 = temp.next;
				
		    }
		    
		    /**
		     * If any of the nodes is a head then the new head will be the other node
		     */
		    if (curr1 == head) {
			head = curr1;
			}
		    else if(curr2 == head) {
			head = curr1;
			}
		    
		    //==========================
		    
		    /**
		     * A -> B -> C -> D
		     * swap A and C
		     * 
		     * This is the main swap
		     * It takes the first nodes prev node and points it to the second node
		     * D -> C
		     * and makes the second nodes prev node point to the first node
		     * B -> A
		     */
		    
		    prev1.next = curr2;
		    prev2.next = curr1;
		    
		    /**
		     * finally it will make the first node point to what the second node is pointing to
		     * B -> A -> D
		     * and make the second node point to what the first node was initially pointing to
		     * D -> C -> B
		     * (since A was initially the head, now C is the new head, check above if statement)
		     * C -> B -> A -> D -> head
		     */
		    
		    Node temp = curr1.next;
		    curr1.next = curr2.next;
		    curr2.next = temp.next;
		    //==========================
		    
		    /**
		     * return true because the values have been swapped 
		     */
		    return true;
		    }
		
		/**
		 * Cycle through the linked list
		 */
		prev = curr;
		curr = curr.next;

		/**
		 * If the curr is the head, that 
		 * means that we have gone through the whole linked list without finding both ISBNS
		 * So return false
		 */
		if (curr == head){
		    return false;
		    }
		}
	    }
	
	/**
	 * Creates a new file called Update_Books.txt
	 * and write the books in BookList to it
	 * If already created it will overwrite it
	 * @throws FileNotFoundException 
	 */
	public void commit() throws FileNotFoundException {
		Node temp = head;
		FileOutputStream fos = new FileOutputStream("Update_Books.txt");
		PrintWriter pw = new PrintWriter(fos);

		while (true) {
			pw.println(temp.getBook());
			temp = temp.next;
			
			if (temp == head) {
				break;
			}
		}
		pw.close();
		
		
	}
	
	private class Node{
		private Book b;
		private Node next;
		
		public Node() {
			b = null;
			next = null;
		}
		
		public Node(Book b, Node next) {
			this.b = b;
			this.next = next;
		}
		
		public Book getBook() {
			return b;
		}
		
	}
}