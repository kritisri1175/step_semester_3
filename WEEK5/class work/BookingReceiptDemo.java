import java.util.Scanner;

final class BookingReceipt {
    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId, String[] seatNumbers) {
        this.bookingId = bookingId;
        this.seatNumbers = seatNumbers.clone();
    }

    public String getBookingId() {
        return bookingId;
    }

    public String[] getSeatNumbers() {
        return seatNumbers.clone();
    }

    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        String[] updatedSeats = seatNumbers.clone();

        if (index >= 0 && index < updatedSeats.length) {
            updatedSeats[index] = newSeat;
        }

        return new BookingReceipt(bookingId, updatedSeats);
    }
}

class GroupBookingReceipt extends BookingReceipt {
    private final int groupSize;

    public GroupBookingReceipt(String bookingId,
                               String[] seatNumbers,
                               int groupSize) {
        super(bookingId, seatNumbers);
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}

public class BookingReceiptDemo {

    public static String processNightlySettlement(
            BookingReceipt[] receipts) {

        int processed = 0;
        int nullCount = 0;
        int groupCount = 0;
        int individualCount = 0;

        for (int i = 0; i < receipts.length; i++) {

            if (receipts[i] == null) {
                nullCount++;
                continue;
            }

            processed++;

            if (receipts[i] instanceof GroupBookingReceipt) {
                groupCount++;
            } else {
                individualCount++;
            }
        }

        return processed + " processed | "
                + nullCount + " null skipped | "
                + groupCount + " group | "
                + individualCount + " individual";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of receipts: ");
        int n = sc.nextInt();
        sc.nextLine();

        BookingReceipt[] receipts = new BookingReceipt[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter booking ID: ");
            String bookingId = sc.nextLine();

            System.out.print("Enter number of seats: ");
            int seatCount = sc.nextInt();
            sc.nextLine();

            String[] seats = new String[seatCount];

            for (int j = 0; j < seatCount; j++) {
                System.out.print("Enter seat " + (j + 1) + ": ");
                seats[j] = sc.nextLine();
            }

            System.out.print("Is this a group booking? (yes/no): ");
            String type = sc.nextLine();

            if (type.equalsIgnoreCase("yes")) {
                System.out.print("Enter group size: ");
                int groupSize = sc.nextInt();
                sc.nextLine();

                receipts[i] = new GroupBookingReceipt(
                        bookingId, seats, groupSize);
            } else {
                receipts[i] = new BookingReceipt(
                        bookingId, seats);
            }
        }

        System.out.println("\nNightly Settlement:");
        System.out.println(processNightlySettlement(receipts));

        sc.close();
    }
}