import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HotelBookingSystem {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH);

    static String period(LocalDate start, LocalDate end) {
        return start.format(FMT) + "-" + end.getDayOfMonth();
    }

    static abstract class Room {

        private final int number;
        private final List<Reservation> reservations = new ArrayList<>();

        Room(int number) {
            this.number = number;
        }

        int getNumber() {
            return number;
        }

        abstract String getCategory();

        abstract double calculatePrice(int nights);

        String getLabel() {
            return getCategory() + " Room " + number;
        }

        // two stays overlap when each one starts before the other ends
        boolean isAvailable(LocalDate start, LocalDate end) {
            for (Reservation r : reservations) {
                if (r.isActive() && start.isBefore(r.getEnd()) && r.getStart().isBefore(end)) {
                    return false;
                }
            }
            return true;
        }

        void addReservation(Reservation reservation) {
            reservations.add(reservation);
        }
    }

    static class StandardRoom extends Room {

        StandardRoom(int number) {
            super(number);
        }

        @Override
        String getCategory() {
            return "Standard";
        }

        @Override
        double calculatePrice(int nights) {
            return 100.0 * nights;
        }
    }

    static class DeluxeRoom extends Room {

        DeluxeRoom(int number) {
            super(number);
        }

        @Override
        String getCategory() {
            return "Deluxe";
        }

        @Override
        double calculatePrice(int nights) {
            return 180.0 * nights;
        }
    }

    static class Suite extends Room {

        Suite(int number) {
            super(number);
        }

        @Override
        String getCategory() {
            return "Suite";
        }

        @Override
        double calculatePrice(int nights) {
            return 300.0 * nights + 50.0; // flat amenities fee
        }
    }

    static class Customer {

        private final String name;

        Customer(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }
    }

    static class Reservation {

        private final Customer customer;
        private final Room room;
        private final LocalDate start;
        private final LocalDate end;
        private final double price;
        private boolean active = true;

        Reservation(Customer customer, Room room, LocalDate start, LocalDate end) {
            this.customer = customer;
            this.room = room;
            this.start = start;
            this.end = end;
            this.price = room.calculatePrice((int) ChronoUnit.DAYS.between(start, end));
        }

        Customer getCustomer() {
            return customer;
        }

        Room getRoom() {
            return room;
        }

        LocalDate getStart() {
            return start;
        }

        LocalDate getEnd() {
            return end;
        }

        double getPrice() {
            return price;
        }

        boolean isActive() {
            return active;
        }

        // cancellation is allowed up to one day before the stay begins
        LocalDate getCancellationDeadline() {
            return start.minusDays(1);
        }

        boolean cancel(LocalDate today) {
            if (!active) {
                System.out.println("Reservation is already cancelled.");
                return false;
            }
            if (today.isAfter(getCancellationDeadline())) {
                System.out.println("Cannot cancel: the cancellation deadline has passed.");
                return false;
            }
            active = false;
            System.out.println("Reservation for " + customer.getName() + ", " + room.getLabel()
                    + " (" + period(start, end) + ") cancelled successfully.");
            return true;
        }
    }

    static class BookingService {

        void checkAvailability(Room room, LocalDate start, LocalDate end) {
            if (room.isAvailable(start, end)) {
                System.out.println(room.getLabel() + " is available from " + range(start, end) + ".");
            } else {
                System.out.println(room.getLabel() + " is not available from " + range(start, end) + ".");
            }
        }

        String range(LocalDate start, LocalDate end) {
            return start.format(FMT) + " to " + end.format(FMT);
        }

        Reservation reserve(Customer customer, Room room, LocalDate start, LocalDate end) {
            if (!room.isAvailable(start, end)) {
                System.out.println(room.getLabel() + " is not available from " + range(start, end) + ".");
                return null;
            }
            Reservation reservation = new Reservation(customer, room, start, end);
            room.addReservation(reservation);
            System.out.println("Reservation confirmed for " + customer.getName() + ", " + room.getLabel()
                    + " (" + period(start, end) + "). Price: $" + String.format("%.2f", reservation.getPrice()));
            return reservation;
        }
    }

    public static void main(String[] args) {
        BookingService service = new BookingService();

        Customer a = new Customer("Customer A");
        Customer b = new Customer("Customer B");
        Customer c = new Customer("Customer C");

        Room standard101 = new StandardRoom(101);
        Room deluxe201 = new DeluxeRoom(201);

        LocalDate today = LocalDate.of(2026, 12, 20);

        service.checkAvailability(standard101, LocalDate.of(2027, 1, 1), LocalDate.of(2027, 1, 5));
        Reservation resA = service.reserve(a, standard101, LocalDate.of(2027, 1, 1), LocalDate.of(2027, 1, 5));
        service.reserve(b, standard101, LocalDate.of(2027, 1, 3), LocalDate.of(2027, 1, 7));
        resA.cancel(today);
        service.reserve(c, deluxe201, LocalDate.of(2027, 2, 10), LocalDate.of(2027, 2, 12));
    }
}
