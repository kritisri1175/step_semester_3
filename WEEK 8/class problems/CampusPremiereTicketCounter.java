import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class CampusPremiereTicketCounter {

    private static final String RUPEE = "₹";
    private static final int MAX_SEATS_PER_BOOKING = 6;

    static String money(double amount) {
        return RUPEE + String.format(Locale.US, "%,.2f", amount);
    }

    static abstract class Seat {

        private final String id;

        Seat(String id) {
            this.id = id;
        }

        String getId() {
            return id;
        }

        // each seat category defines its own price
        abstract double getPrice();
    }

    static class RegularSeat extends Seat {

        RegularSeat(String id) {
            super(id);
        }

        @Override
        double getPrice() {
            return 150.0;
        }
    }

    static class PremiumSeat extends Seat {

        PremiumSeat(String id) {
            super(id);
        }

        @Override
        double getPrice() {
            return 250.0;
        }
    }

    static class ReclinerSeat extends Seat {

        ReclinerSeat(String id) {
            super(id);
        }

        @Override
        double getPrice() {
            return 400.0;
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

    static class Show {

        private final String title;
        private final LocalDateTime startTime;
        private final Map<String, Seat> seats = new LinkedHashMap<>();
        private final Set<String> bookedSeatIds = new HashSet<>();

        Show(String title, LocalDateTime startTime) {
            this.title = title;
            this.startTime = startTime;
        }

        String getTitle() {
            return title;
        }

        LocalDateTime getStartTime() {
            return startTime;
        }

        void addSeat(Seat seat) {
            seats.put(seat.getId(), seat);
        }

        boolean isBooked(String seatId) {
            return bookedSeatIds.contains(seatId);
        }

        void markBooked(List<Seat> toBook) {
            for (Seat seat : toBook) {
                bookedSeatIds.add(seat.getId());
            }
        }

        void release(List<Seat> toRelease) {
            for (Seat seat : toRelease) {
                bookedSeatIds.remove(seat.getId());
            }
        }

        Seat getSeat(String seatId) {
            return seats.get(seatId);
        }
    }

    static class Booking {

        private final Customer customer;
        private final Show show;
        private final List<Seat> seats;
        private boolean cancelled = false;

        private Booking(Customer customer, Show show, List<Seat> seats) {
            this.customer = customer;
            this.show = show;
            this.seats = seats;
        }

        // checks everything first, so a failed booking never holds any seat
        static Booking book(Customer customer, Show show, String... seatIds) {
            if (seatIds.length == 0) {
                System.out.println("Select at least one seat.");
                return null;
            }
            if (seatIds.length > MAX_SEATS_PER_BOOKING) {
                System.out.println("Cannot book more than " + MAX_SEATS_PER_BOOKING + " seats in one booking.");
                return null;
            }
            List<Seat> chosen = new ArrayList<>();
            Set<String> seen = new HashSet<>();
            for (String seatId : seatIds) {
                Seat seat = show.getSeat(seatId);
                if (seat == null) {
                    System.out.println("Seat " + seatId + " does not exist for this show.");
                    return null;
                }
                if (!seen.add(seatId) || show.isBooked(seatId)) {
                    System.out.println("Seat " + seatId + " is already booked for this show.");
                    return null;
                }
                chosen.add(seat);
            }
            Booking booking = new Booking(customer, show, chosen);
            show.markBooked(chosen);
            System.out.println("Booking confirmed for " + customer.getName() + ": " + booking.seatList()
                    + ". Total: " + money(booking.getTotal()) + ".");
            return booking;
        }

        double getTotal() {
            double total = 0;
            for (Seat seat : seats) {
                total += seat.getPrice();
            }
            return total;
        }

        private String seatList() {
            StringBuilder sb = new StringBuilder();
            for (Seat seat : seats) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(seat.getId());
            }
            return sb.toString();
        }

        void cancel(LocalDateTime now) {
            if (cancelled) {
                System.out.println("Booking is already cancelled.");
                return;
            }
            if (!now.isBefore(show.getStartTime())) {
                System.out.println("Cannot cancel: the show has already started.");
                return;
            }
            cancelled = true;
            show.release(seats);
            System.out.println(customer.getName() + "'s booking cancelled. Seats " + seatList() + " released.");
        }
    }

    public static void main(String[] args) {
        Show show = new Show("Campus Premiere (7 PM)", LocalDateTime.of(2027, 3, 15, 19, 0));
        show.addSeat(new RegularSeat("A1"));
        show.addSeat(new RegularSeat("A2"));
        show.addSeat(new PremiumSeat("F5"));
        show.addSeat(new ReclinerSeat("R1"));

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Booking ashaBooking = Booking.book(asha, show, "A1", "A2", "F5");
        Booking.book(ravi, show, "A2");
        Booking.book(ravi, show, "R1");
        ashaBooking.cancel(LocalDateTime.of(2027, 3, 15, 15, 0));
        Booking.book(neha, show, "A2");
    }
}
