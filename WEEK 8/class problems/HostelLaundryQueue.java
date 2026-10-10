import java.util.Locale;

public class HostelLaundryQueue {

    private static final String RUPEE = "₹";

    static String money(double amount) {
        return RUPEE + String.format(Locale.US, "%,.2f", amount);
    }

    // each wash type defines its own duration and charge
    static abstract class WashType {

        abstract String getName();

        abstract int getDurationMinutes();

        abstract double getCharge();
    }

    static class QuickWash extends WashType {

        @Override
        String getName() {
            return "Quick";
        }

        @Override
        int getDurationMinutes() {
            return 30;
        }

        @Override
        double getCharge() {
            return 20.0;
        }
    }

    static class NormalWash extends WashType {

        @Override
        String getName() {
            return "Normal";
        }

        @Override
        int getDurationMinutes() {
            return 45;
        }

        @Override
        double getCharge() {
            return 30.0;
        }
    }

    static class HeavyWash extends WashType {

        @Override
        String getName() {
            return "Heavy";
        }

        @Override
        int getDurationMinutes() {
            return 60;
        }

        @Override
        double getCharge() {
            return 45.0;
        }
    }

    static class Student {

        private final String name;

        Student(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }
    }

    static class WashCycle {

        private final Student student;
        private final WashingMachine machine;
        private final WashType washType;

        WashCycle(Student student, WashingMachine machine, WashType washType) {
            this.student = student;
            this.machine = machine;
            this.washType = washType;
        }

        Student getStudent() {
            return student;
        }

        WashingMachine getMachine() {
            return machine;
        }

        WashType getWashType() {
            return washType;
        }

        double getCharge() {
            return washType.getCharge();
        }
    }

    static class WashingMachine {

        private final String id;
        private boolean busy = false; // only this class can change it
        private WashCycle currentCycle;

        WashingMachine(String id) {
            this.id = id;
        }

        String getId() {
            return id;
        }

        boolean isBusy() {
            return busy;
        }

        WashCycle startWash(Student student, WashType washType) {
            if (busy) {
                System.out.println("Machine " + id + " is currently busy.");
                return null;
            }
            currentCycle = new WashCycle(student, this, washType);
            busy = true;
            System.out.println(washType.getName() + " wash started on " + id + " for " + student.getName()
                    + " (" + washType.getDurationMinutes() + " min). Charge: " + money(currentCycle.getCharge()) + ".");
            return currentCycle;
        }

        void completeCycle() {
            if (!busy) {
                System.out.println(id + " has no wash in progress.");
                return;
            }
            currentCycle = null;
            busy = false;
            System.out.println(id + " cycle completed. " + id + " is now free.");
        }
    }

    public static void main(String[] args) {
        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        m1.startWash(asha, new QuickWash());
        m1.startWash(ravi, new HeavyWash());
        m2.startWash(ravi, new HeavyWash());
        m1.completeCycle();
        m1.startWash(neha, new NormalWash());
    }
}
