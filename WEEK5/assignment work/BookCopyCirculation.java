import java.util.Scanner;

class BookInventory {

    private int copiesTotal;
    private int copiesAvailable;

    public BookInventory(int copiesTotal) {

        if (copiesTotal <= 0) {
            throw new IllegalArgumentException(
                    "copiesTotal must be positive"
            );
        }

        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    public void checkOut() {

        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    public void checkIn() {

        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }
}

public class BookCopyCirculation {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total number of copies: ");
        int total = sc.nextInt();

        try {

            BookInventory book = new BookInventory(total);

            System.out.print("Enter number of check-outs: ");
            int checkOutCount = sc.nextInt();

            for (int i = 0; i < checkOutCount; i++) {
                book.checkOut();
            }

            System.out.println(
                    "Copies available after check-outs: "
                            + book.getCopiesAvailable()
            );

            System.out.print("Enter number of check-ins: ");
            int checkInCount = sc.nextInt();

            for (int i = 0; i < checkInCount; i++) {
                book.checkIn();
            }

            System.out.println(
                    "Copies available after check-ins: "
                            + book.getCopiesAvailable()
            );

        } catch (IllegalArgumentException e) {

            System.out.println("Construction rejected");
        }

        sc.close();
    }
}