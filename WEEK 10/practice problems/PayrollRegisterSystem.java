import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/*
 * Sample input (one staff member per line):
 *   FullTime Asha 50000
 *   PartTime Ravi 80 300
 *   Intern Neha 15000
 */
public class PayrollRegisterSystem {

    static String formatPay(double pay) {
        if (pay == Math.rint(pay)) {
            return String.valueOf((long) pay);
        }
        return String.format(Locale.US, "%.2f", pay);
    }

    static abstract class Staff {

        private final String name;

        Staff(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        abstract String getType();

        abstract double calculatePay();

        // readable text form of the payslip
        @Override
        public String toString() {
            return "Payslip[name=" + name + ", type=" + getType() + ", pay=" + formatPay(calculatePay()) + "]";
        }
    }

    static class FullTime extends Staff {

        private final double salary;

        FullTime(String name, double salary) {
            super(name);
            this.salary = salary;
        }

        @Override
        String getType() {
            return "FullTime";
        }

        @Override
        double calculatePay() {
            return salary;
        }
    }

    static class PartTime extends Staff {

        private final double hours;
        private final double rate;

        PartTime(String name, double hours, double rate) {
            super(name);
            this.hours = hours;
            this.rate = rate;
        }

        @Override
        String getType() {
            return "PartTime";
        }

        @Override
        double calculatePay() {
            return hours * rate;
        }
    }

    static class Intern extends Staff {

        private final double stipend;

        Intern(String name, double stipend) {
            super(name);
            this.stipend = stipend;
        }

        @Override
        String getType() {
            return "Intern";
        }

        @Override
        double calculatePay() {
            return stipend;
        }
    }

    static Staff parse(String line) {
        String[] t = line.trim().split("\\s+");
        if (t.length >= 3 && t[0].equalsIgnoreCase("FullTime")) {
            return new FullTime(t[1], Double.parseDouble(t[2]));
        }
        if (t.length >= 4 && t[0].equalsIgnoreCase("PartTime")) {
            return new PartTime(t[1], Double.parseDouble(t[2]), Double.parseDouble(t[3]));
        }
        if (t.length >= 3 && t[0].equalsIgnoreCase("Intern")) {
            return new Intern(t[1], Double.parseDouble(t[2]));
        }
        return null;
    }

    static void run(List<String> lines) {
        // all staff are stored in one array and processed through the Staff type
        Staff[] register = new Staff[lines.size()];
        int count = 0;
        for (String line : lines) {
            Staff staff = parse(line);
            if (staff != null) {
                register[count++] = staff;
            }
        }

        double total = 0;
        Staff top = null;
        for (int i = 0; i < count; i++) {
            System.out.println(register[i]);
            total += register[i].calculatePay();
            // strict ">" keeps the first one when pay is tied
            if (top == null || register[i].calculatePay() > top.calculatePay()) {
                top = register[i];
            }
        }

        if (top != null) {
            System.out.println("Total " + formatPay(total));
            System.out.println("top earner " + top.getName());
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
