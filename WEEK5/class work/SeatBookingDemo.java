import java.util.Scanner;

class CineScreen {
    private int seatsTotal;
    private int seatsAvailable;

    public CineScreen(int seatsTotal) {
        if (seatsTotal <= 0) {
            throw new IllegalArgumentException(
                    "seatsTotal must be greater than 0");
        }

        this.seatsTotal = seatsTotal;
        this.seatsAvailable = seatsTotal;
    }

    public void bookSeat() {
        if (seatsAvailable > 0) {
            seatsAvailable--;
        }
    }

    public void cancelBooking() {
        if (seatsAvailable < seatsTotal) {
            seatsAvailable++;
        }
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }
}

public class SeatBookingDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total number of seats: ");
        int totalSeats = sc.nextInt();

        try {
            CineScreen screen = new CineScreen(totalSeats);

            System.out.print("Enter number of bookings: ");
            int bookings = sc.nextInt();

            for (int i = 0; i < bookings; i++) {
                screen.bookSeat();
            }

            System.out.println(
                    "Available seats after bookings: "
                            + screen.getSeatsAvailable());

            System.out.print("Enter number of cancellations: ");
            int cancellations = sc.nextInt();

            for (int i = 0; i < cancellations; i++) {
                screen.cancelBooking();
            }

            System.out.println(
                    "Available seats after cancellations: "
                            + screen.getSeatsAvailable());

        } catch (IllegalArgumentException e) {
            System.out.println("Construction rejected");
        }

        sc.close();
    }
}