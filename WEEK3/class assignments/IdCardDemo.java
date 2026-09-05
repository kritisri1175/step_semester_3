import java.util.Scanner;

class IdCard {

    String name;
    int booksIssued;

    IdCard(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
    }
}

public class IdCardDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        System.out.print("Enter initial books issued: ");
        int booksIssued = sc.nextInt();

        // Create first object
        IdCard ravi = new IdCard(name, booksIssued);

        // Second variable points to same object
        IdCard duplicate = ravi;

        System.out.print("Enter new books issued through duplicate: ");
        duplicate.booksIssued = sc.nextInt();

        // Create separate object
        IdCard separate = new IdCard(name, duplicate.booksIssued);

        System.out.println("\nOutput:");

        System.out.println(name + "'s booksIssued (via first variable): "
                + ravi.booksIssued);

        System.out.println("duplicate == ravi: "
                + (duplicate == ravi));

        System.out.println("separate == ravi: "
                + (separate == ravi));

        sc.close();
    }
}