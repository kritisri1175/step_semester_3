import java.util.ArrayList;
import java.util.List;

public class VehicleRentalSystem {

    static abstract class Vehicle {

        private final String name;
        private boolean available = true;

        Vehicle(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        boolean isAvailable() {
            return available;
        }

        void markRented() {
            available = false;
        }

        void markReturned() {
            available = true;
        }

        abstract double calculateCharge(int days);
    }

    static class Sedan extends Vehicle {

        Sedan(String name) {
            super(name);
        }

        @Override
        double calculateCharge(int days) {
            return 50.0 * days;
        }
    }

    static class SUV extends Vehicle {

        SUV(String name) {
            super(name);
        }

        @Override
        double calculateCharge(int days) {
            return 80.0 * days;
        }
    }

    static class Truck extends Vehicle {

        Truck(String name) {
            super(name);
        }

        @Override
        double calculateCharge(int days) {
            return 100.0 * days + 50.0; // flat handling fee
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

    static class Rental {

        private final Vehicle vehicle;
        private final Customer customer;
        private final int days;
        private final double charge;
        private boolean active = true;

        Rental(Vehicle vehicle, Customer customer, int days) {
            this.vehicle = vehicle;
            this.customer = customer;
            this.days = days;
            this.charge = vehicle.calculateCharge(days);
        }

        Vehicle getVehicle() {
            return vehicle;
        }

        Customer getCustomer() {
            return customer;
        }

        int getDays() {
            return days;
        }

        double getCharge() {
            return charge;
        }

        boolean isActive() {
            return active;
        }

        void close() {
            active = false;
        }
    }

    static class RentalService {

        private final List<Rental> rentals = new ArrayList<>();

        Rental rentVehicle(Customer customer, Vehicle vehicle, int days) {
            if (!vehicle.isAvailable()) {
                System.out.println(vehicle.getName() + " is currently unavailable.");
                return null;
            }
            Rental rental = new Rental(vehicle, customer, days);
            vehicle.markRented();
            rentals.add(rental);
            System.out.println(vehicle.getName() + " rented successfully by " + customer.getName()
                    + ". Rental charge: $" + String.format("%.2f", rental.getCharge()));
            return rental;
        }

        void returnVehicle(Customer customer, Vehicle vehicle) {
            for (Rental rental : rentals) {
                if (rental.isActive() && rental.getVehicle() == vehicle && rental.getCustomer() == customer) {
                    rental.close();
                    vehicle.markReturned();
                    System.out.println(vehicle.getName() + " returned by " + customer.getName() + ".");
                    return;
                }
            }
            System.out.println("No active rental of " + vehicle.getName() + " found for " + customer.getName() + ".");
        }
    }

    public static void main(String[] args) {
        RentalService service = new RentalService();

        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");

        Vehicle sedanA = new Sedan("Sedan A");
        Vehicle suvB = new SUV("SUV B");

        service.rentVehicle(c1, sedanA, 3);
        service.rentVehicle(c2, sedanA, 2);
        service.returnVehicle(c1, sedanA);
        service.rentVehicle(c3, suvB, 5);
    }
}
