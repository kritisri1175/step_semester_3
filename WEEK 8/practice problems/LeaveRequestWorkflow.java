import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class LeaveRequestWorkflow {

    enum Status {
        PENDING, APPROVED, REJECTED;

        String label() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }

    static abstract class Employee {

        private final String name;

        Employee(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        // each employee type supplies its own leave rule
        abstract boolean isLeaveAllowed(int days);
    }

    static class FullTimeEmployee extends Employee {

        FullTimeEmployee(String name) {
            super(name);
        }

        @Override
        boolean isLeaveAllowed(int days) {
            return days <= 20;
        }
    }

    static class PartTimeEmployee extends Employee {

        PartTimeEmployee(String name) {
            super(name);
        }

        @Override
        boolean isLeaveAllowed(int days) {
            return days <= 10;
        }
    }

    static class Contractor extends Employee {

        Contractor(String name) {
            super(name);
        }

        @Override
        boolean isLeaveAllowed(int days) {
            return days <= 5;
        }
    }

    static class Reviewer {

        private final String name;

        Reviewer(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        void approve(LeaveRequest request) {
            request.approve(this);
        }

        void reject(LeaveRequest request) {
            request.reject(this);
        }
    }

    static class LeaveRequest {

        private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH);

        private final Employee employee;
        private final LocalDate start;
        private final LocalDate end;
        private Status status = Status.PENDING;

        private LeaveRequest(Employee employee, LocalDate start, LocalDate end) {
            this.employee = employee;
            this.start = start;
            this.end = end;
        }

        // submission is the only way to create a request, so the policy is always checked
        static LeaveRequest submit(Employee employee, LocalDate start, LocalDate end) {
            int days = (int) ChronoUnit.DAYS.between(start, end) + 1;
            if (days <= 0 || !employee.isLeaveAllowed(days)) {
                System.out.println("Leave request rejected by policy for " + employee.getName() + ".");
                return null;
            }
            LeaveRequest request = new LeaveRequest(employee, start, end);
            System.out.println("Leave request submitted for " + employee.getName() + " (" + request.period()
                    + "). Status: " + request.status.label() + ".");
            return request;
        }

        private String period() {
            return start.format(FMT) + "-" + end.getDayOfMonth();
        }

        Status getStatus() {
            return status;
        }

        void approve(Reviewer reviewer) {
            changeStatus(Status.APPROVED);
        }

        void reject(Reviewer reviewer) {
            changeStatus(Status.REJECTED);
        }

        // used when someone tries to push the request back to an earlier state
        void changeStatus(Status newStatus) {
            if (status != Status.PENDING) {
                System.out.println("Cannot change leave request status from " + status.label()
                        + " to " + newStatus.label() + ".");
                return;
            }
            if (newStatus == Status.PENDING) {
                return;
            }
            status = newStatus;
            System.out.println(employee.getName() + "'s leave request (" + period() + ") "
                    + newStatus.label().toLowerCase() + ". Status: " + newStatus.label() + ".");
        }
    }

    public static void main(String[] args) {
        FullTimeEmployee john = new FullTimeEmployee("John");
        PartTimeEmployee jane = new PartTimeEmployee("Jane");
        Reviewer alice = new Reviewer("Alice");
        Reviewer bob = new Reviewer("Bob");

        LeaveRequest johnRequest = LeaveRequest.submit(john, LocalDate.of(2027, 1, 1), LocalDate.of(2027, 1, 5));
        alice.approve(johnRequest);

        LeaveRequest janeRequest = LeaveRequest.submit(jane, LocalDate.of(2027, 2, 10), LocalDate.of(2027, 2, 11));
        bob.reject(janeRequest);

        johnRequest.changeStatus(Status.PENDING);
    }
}
