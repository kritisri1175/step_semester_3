import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * Sample input (one operation per line):
 *   PASS Bike KA01 Asha
 *   PASS ECar KA02 Ravi
 *   CHARGE KA02
 *   CHARGE KA01
 */
public class CampusVehiclePassSystem {

    // only electric vehicles promise this behavior
    interface Chargeable {
        String bookChargingBay();
    }

    static abstract class Vehicle {

        private final String passNumber;
        private final String owner;

        Vehicle(String passNumber, String owner) {
            this.passNumber = passNumber;
            this.owner = owner;
        }

        String getPassNumber() {
            return passNumber;
        }

        String getOwner() {
            return owner;
        }

        abstract String getTypeName();

        abstract int getPassFee();
    }

    static class Bike extends Vehicle {

        Bike(String passNumber, String owner) {
            super(passNumber, owner);
        }

        @Override
        String getTypeName() {
            return "Bike";
        }

        @Override
        int getPassFee() {
            return 300;
        }
    }

    static class Car extends Vehicle {

        Car(String passNumber, String owner) {
            super(passNumber, owner);
        }

        @Override
        String getTypeName() {
            return "Car";
        }

        @Override
        int getPassFee() {
            return 1000;
        }
    }

    static class EBike extends Vehicle implements Chargeable {

        EBike(String passNumber, String owner) {
            super(passNumber, owner);
        }

        @Override
        String getTypeName() {
            return "EBike";
        }

        @Override
        int getPassFee() {
            return 300;
        }

        @Override
        public String bookChargingBay() {
            return getPassNumber() + " charging bay allotted";
        }
    }

    static class ECar extends Vehicle implements Chargeable {

        ECar(String passNumber, String owner) {
            super(passNumber, owner);
        }

        @Override
        String getTypeName() {
            return "ECar";
        }

        @Override
        int getPassFee() {
            return 1000;
        }

        @Override
        public String bookChargingBay() {
            return getPassNumber() + " charging bay allotted";
        }
    }

    static Vehicle createVehicle(String type, String passNumber, String owner) {
        // accepts "EBike" as well as "E-Bike"
        switch (type.replace("-", "").toLowerCase()) {
            case "bike":
                return new Bike(passNumber, owner);
            case "car":
                return new Car(passNumber, owner);
            case "ebike":
                return new EBike(passNumber, owner);
            case "ecar":
                return new ECar(passNumber, owner);
            default:
                return null;
        }
    }

    static void run(List<String> lines) {
        Map<String, Vehicle> vehicles = new LinkedHashMap<>();

        for (String line : lines) {
            String[] t = line.trim().split("\\s+");
            if (t[0].equalsIgnoreCase("PASS") && t.length >= 4) {
                Vehicle vehicle = createVehicle(t[1], t[2], t[3]);
                if (vehicle == null) {
                    System.out.println("Unknown vehicle type: " + t[1]);
                    continue;
                }
                vehicles.put(vehicle.getPassNumber(), vehicle);
                System.out.println(vehicle.getPassNumber() + " (" + vehicle.getTypeName() + ") pass fee "
                        + vehicle.getPassFee());
            } else if (t[0].equalsIgnoreCase("CHARGE") && t.length >= 2) {
                Vehicle vehicle = vehicles.get(t[1]);
                if (vehicle == null) {
                    System.out.println(t[1] + " not found");
                } else if (vehicle instanceof Chargeable) {
                    System.out.println(((Chargeable) vehicle).bookChargingBay());
                } else {
                    System.out.println(vehicle.getPassNumber() + " rejected: charging unsupported");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                lines.add(line);
            }
        }
        run(lines);
    }
}
