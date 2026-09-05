import java.util.Scanner;

class LibraryBook {

    String title;
    String isbn;

    // Constructor with ISBN
    public LibraryBook(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    // Constructor without ISBN
    public LibraryBook(String title) {
        this(title, "PENDING");
    }

    public void printStatus() {
        System.out.println(title + " | " + isbn + " | Catalogued: true");
    }
}

public class LibraryBookDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of books: ");
        int n = sc.nextInt();
        sc.nextLine();

        LibraryBook[] books = new LibraryBook[n];

        for (int i = 0; i < n; i++) {

            System.out.print("Enter book title: ");
            String title = sc.nextLine();

            System.out.print("Does the book have an ISBN? (yes/no): ");
            String choice = sc.nextLine();

            if (choice.equalsIgnoreCase("yes")) {

                System.out.print("Enter ISBN: ");
                String isbn = sc.nextLine();

                books[i] = new LibraryBook(title, isbn);

            } else {

                books[i] = new LibraryBook(title);
            }
        }

        System.out.println("\nBook Status:");

        // Single pass
        for (LibraryBook book : books) {
            book.printStatus();
        }

        sc.close();
    }
}