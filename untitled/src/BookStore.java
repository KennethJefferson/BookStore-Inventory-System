// Import necessary classes from Java's utility library.
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * A simple data class to represent a Book.
 * This class will hold attributes of each book in our bookstore.
 */
class Book {
    // Attributes of a book
    String isbn;     // International Standard Book Number, a unique identifier for the book
    String title;    // Name of the book
    String author;   // Name of the person who wrote the book
    int quantity;    // Number of copies of the book in the inventory
    double price;    // Cost of the book

    /**
     * Constructor - A method that gets called when a new Book object is created.
     * It initializes the attributes with the provided values.
     */
    public Book(String isbn, String title, String author, int quantity, double price) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.price = price;
    }

    // This method returns a String representation of the Book object.
    @Override
    public String toString() {
        return "ISBN: " + isbn + ", Title: " + title + ", Author: " + author + ", Quantity: " + quantity + ", Price: $" + price;
    }
}

/**
 * Main class for the Bookstore Inventory Program.
 * This is where most of our operations like adding, updating, and listing books will be defined.
 */
public class BookStore {

    // This list will hold all the books in our inventory.
    static List<Book> inventory = new ArrayList<>();

    // A tool for getting input from the user.
    static Scanner sc = new Scanner(System.in);

    /**
     * Function to add a new book to the inventory.
     * It prompts the user for book details and adds it to our list.
     */
    public static void addBook() {
        // Get book details
        Scanner sc = new Scanner(System.in);

        // Validation for ISBN input
        String isbn = "";
        while (isbn.isEmpty()) {
            System.out.print("Enter ISBN: ");
            isbn = sc.nextLine().trim();
            if (isbn.isEmpty()) {
                System.out.println("ISBN cannot be empty. Please enter again.");
            }
        }

        // Validation for title input
        String title = "";
        while (title.isEmpty()) {
            System.out.print("Enter title: ");
            title = sc.nextLine().trim();
            if (title.isEmpty()) {
                System.out.println("Title cannot be empty. Please enter again.");
            }
        }

        // Validation for author input
        String author = "";
        while (author.isEmpty()) {
            System.out.print("Enter author: ");
            author = sc.nextLine().trim();
            if (author.isEmpty()) {
                System.out.println("Author cannot be empty. Please enter again.");
            }
        }

        // Validation for quantity input
        int quantity = -1;
        while (quantity < 0) {
            System.out.print("Enter quantity: ");
            try {
                quantity = sc.nextInt();
                if (quantity < 0) {
                    System.out.println("Quantity cannot be negative. Please enter again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number for quantity!");
                sc.nextLine();  // Clear invalid input
            }
        }

        // Validation for price input
        double price = -1.0;
        while (price < 0) {
            System.out.print("Enter price: ");
            try {
                price = sc.nextDouble();
                if (price < 0) {
                    System.out.println("Price cannot be negative. Please enter again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number for price!");
                sc.nextLine();  // Clear invalid input
            }
        }

        sc.nextLine();  // Consume leftover newline

        // Create new book object
        Book book = new Book(isbn, title, author, quantity, price);

        // Check if ISBN already exists
        if(searchByIsbn(isbn) != null) {
            System.out.println("Error! ISBN already exists.");
        }
        else {
            // Add new book to inventory
            inventory.add(book);
            System.out.println("Book added!");
        }
    }

    /**
     * Function to update details of an existing book.
     * It prompts the user for the ISBN, finds the book, and allows them to update its details.
     */
    public static void updateBook() {
        try {
            System.out.print("Enter ISBN to update: ");
            String isbn = sc.nextLine();

            // Search for the book using the provided ISBN
            Book book = searchByIsbn(isbn);

            // If we can't find the book, inform the user
            if(book == null) {
                System.out.println("Book not found!");
                return;
            }

            // Otherwise, prompt the user for updated details
            System.out.print("Enter updated title: ");
            book.title = sc.nextLine();
            System.out.print("Enter updated author: ");
            book.author = sc.nextLine();
            System.out.print("Enter updated quantity: ");
            book.quantity = sc.nextInt();
            sc.nextLine(); // Consume any leftover characters
            System.out.print("Enter updated price: ");
            book.price = sc.nextDouble();
            sc.nextLine(); // Consume any leftover characters

            System.out.println("Book updated!");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter the correct format.");
            sc.nextLine(); // Clear any invalid input
        }
    }

    /**
     * Function to search for a book by its ISBN.
     * @param isbn - the unique identifier we're searching for
     * @return - the book object if found, otherwise null
     */
    public static Book searchByIsbn(String isbn) {
        for(Book book : inventory) {  // Loop through each book in our inventory
            if(book.isbn.equals(isbn)) {  // If the book's ISBN matches the one we're looking for
                return book;  // Return that book
            }
        }
        // If we go through the whole list and don't find the ISBN, return null
        return null;
    }

    /**
     * Function to allow the user to search for a book by its ISBN and see its details.
     */
    public static void searchBook() {
        System.out.print("Enter ISBN to search: ");
        String isbn = sc.nextLine();
        Book book = searchByIsbn(isbn);

        if(book == null) {
            System.out.println("Book not found!");
        } else {
            System.out.println(book);  // Print the details of the found book
        }
    }

    // Function to delete a book by ISBN
    public static void deleteBook() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ISBN of the book to delete: ");
        String isbn = sc.nextLine();

        // Search book by ISBN
        Book book = searchByIsbn(isbn);

        if(book == null) {
            System.out.println("Book not found!");
        } else {
            inventory.remove(book);
            System.out.println("Book deleted successfully!");
        }
    }

    /**
     * Function to list all books in the inventory.
     */
    public static void listAllBooks() {
        if (inventory.isEmpty()) {
            System.out.println("No books in the inventory.");
            return;
        }

        // Loop through each book in the inventory and print its details
        for(Book book : inventory) {
            System.out.println(book);
        }
    }

    /**
     * The main function - this is where our program starts.
     */
    public static void main(String[] args) {
        // Keep presenting the menu to the user until they choose to exit
        while(true) {
            // Print the menu options
            System.out.println("\nChoose an option:");
            System.out.println("1. Add book");
            System.out.println("2. Update book");
            System.out.println("3. Search book");
            System.out.println("4. List all books");
            System.out.println("5. Exit");

            try {
                int choice = sc.nextInt();  // Get the user's choice
                sc.nextLine();  // Consume any leftover characters

                // Execute the appropriate action based on the user's choice
                switch(choice) {
                    case 1:
                        addBook();
                        break;
                    case 2:
                        updateBook();
                        break;
                    case 3:
                        searchBook();
                        break;
                    case 4:
                        listAllBooks();
                        break;
                    case 5:
                        deleteBook();  // New menu option for delete
                        break;
                    case 6:
                        System.exit(0);  // Exit the program
                    default:
                        System.out.println("Invalid choice! Please select a valid option.");  // Inform user of invalid choice
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!");  // If the user doesn't enter a number for the menu choice
                sc.nextLine();  // Clear any invalid input
            }
        }
    }
}
