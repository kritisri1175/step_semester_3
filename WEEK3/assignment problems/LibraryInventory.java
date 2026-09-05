import java.util.Scanner;

class BookInventory {
    String title;
    String author;
    int copiesAvailable;

    BookInventory(String title, String author, int copiesAvailable) {
        this.title = title;
        this.author = author;
        this.copiesAvailable = copiesAvailable;
    }

    void printEntry() {
        System.out.println(title + " by " + author
                + " - " + copiesAvailable + " copies available");
    }
}

public class LibraryInventory {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BookInventory[] books = new BookInventory[4];

        for (int i = 0; i < 4; i++) {

            System.out.print("Enter book title: ");
            String title = sc.nextLine();

            System.out.print("Enter author: ");
            String author = sc.nextLine();

            System.out.print("Enter copies available: ");
            int copies = sc.nextInt();
            sc.nextLine();

        }}}