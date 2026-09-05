import java.util.Scanner;

final class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        this.bookIds = bookIds.clone();
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {

        String[] updatedBookIds = bookIds.clone();

        if (index >= 0 && index < updatedBookIds.length) {
            updatedBookIds[index] = newId;
        }

        return new LoanReceipt(memberId, updatedBookIds);
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceiptgit  {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId,
                                    String[] bookIds,
                                    String roomNumber) {

        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}

class CirculationLedger {

    static String branchCode;

    static {
        branchCode = "PTL-001";
    }

    public static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (int i = 0; i < receipts.length; i++) {

            if (receipts[i] == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipts[i] instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | "
                + regular + " regular";
    }
}

class LoanReceiptDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of receipts: ");
        int n = sc.nextInt();
        sc.nextLine();

        LoanReceipt[] receipts = new LoanReceipt[n];

        for (int i = 0; i < n; i++) {

            System.out.print("Enter member ID: ");
            String memberId = sc.nextLine();

            System.out.print("Enter number of books: ");
            int bookCount = sc.nextInt();
            sc.nextLine();

            String[] bookIds = new String[bookCount];

            for (int j = 0; j < bookCount; j++) {

                System.out.print("Enter book ID " + (j + 1) + ": ");
                bookIds[j] = sc.nextLine();
            }

            System.out.print(
                    "Is this Reference-Only receipt? (yes/no): ");
            String choice = sc.nextLine();

            if (choice.equalsIgnoreCase("yes")) {

                System.out.print("Enter room number: ");
                String roomNumber = sc.nextLine();

                receipts[i] = new ReferenceOnlyLoanReceipt(
                        memberId,
                        bookIds,
                        roomNumber
                );

            } else {

                receipts[i] = new LoanReceipt(
                        memberId,
                        bookIds
                );
            }
        }

        System.out.println("\nNightly Circulation:");
        System.out.println(
                CirculationLedger.processNightlyCirculation(receipts)
        );

        sc.close();
    }
}