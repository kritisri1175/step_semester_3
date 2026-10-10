import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AssignmentSubmissionPortal {

    static String formatMarks(double marks) {
        if (marks == Math.floor(marks)) {
            return String.valueOf((int) marks);
        }
        return String.format("%.1f", marks);
    }

    static abstract class Assignment {

        private final String title;
        private final LocalDate dueDate;
        private final int maxMarks;

        Assignment(String title, LocalDate dueDate, int maxMarks) {
            this.title = title;
            this.dueDate = dueDate;
            this.maxMarks = maxMarks;
        }

        String getTitle() {
            return title;
        }

        int getMaxMarks() {
            return maxMarks;
        }

        // the assignment knows its own due date, so it works out the late days
        long daysLate(LocalDate submittedOn) {
            long days = ChronoUnit.DAYS.between(dueDate, submittedOn);
            return Math.max(days, 0);
        }

        // each assignment type supplies its own penalty rule (fraction lost per day late)
        abstract double getPenaltyPerDay();

        double getPenaltyFraction(long daysLate) {
            return Math.min(1.0, getPenaltyPerDay() * daysLate);
        }

        double applyLatePenalty(double awardedMarks, long daysLate) {
            return awardedMarks * (1.0 - getPenaltyFraction(daysLate));
        }
    }

    static class CodingAssignment extends Assignment {

        CodingAssignment(String title, LocalDate dueDate, int maxMarks) {
            super(title, dueDate, maxMarks);
        }

        @Override
        double getPenaltyPerDay() {
            return 0.10;
        }
    }

    static class WrittenAssignment extends Assignment {

        WrittenAssignment(String title, LocalDate dueDate, int maxMarks) {
            super(title, dueDate, maxMarks);
        }

        @Override
        double getPenaltyPerDay() {
            return 0.20;
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

    enum Status {
        SUBMITTED, GRADED;

        String label() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }

    static class Submission {

        private final Student student;
        private final Assignment assignment;
        private LocalDate submissionDate;
        private Status status;
        private double finalMarks;

        private Submission(Student student, Assignment assignment, LocalDate submissionDate) {
            this.student = student;
            this.assignment = assignment;
            this.submissionDate = submissionDate;
            this.status = Status.SUBMITTED;
        }

        static Submission submit(Student student, Assignment assignment, LocalDate date) {
            Submission submission = new Submission(student, assignment, date);
            long late = assignment.daysLate(date);
            String timing = late == 0 ? "on time" : late + (late == 1 ? " day late" : " days late");
            System.out.println(student.getName() + "'s submission for '" + assignment.getTitle()
                    + "' received (" + timing + "). Status: " + submission.status.label() + ".");
            return submission;
        }

        Status getStatus() {
            return status;
        }

        void resubmit(LocalDate date) {
            if (status == Status.GRADED) {
                System.out.println("Cannot resubmit: '" + assignment.getTitle() + "' has already been graded.");
                return;
            }
            this.submissionDate = date;
            System.out.println(student.getName() + " resubmitted '" + assignment.getTitle() + "'.");
        }

        // final marks are worked out at grading time, with the late penalty applied
        void grade(double awardedMarks) {
            if (status == Status.GRADED) {
                System.out.println("Submission is already graded.");
                return;
            }
            long late = assignment.daysLate(submissionDate);
            finalMarks = assignment.applyLatePenalty(awardedMarks, late);
            status = Status.GRADED;

            String message = student.getName() + " graded: " + formatMarks(finalMarks) + "/" + assignment.getMaxMarks();
            if (late > 0) {
                long percent = Math.round(assignment.getPenaltyFraction(late) * 100);
                message += " after " + percent + "% late penalty";
            }
            System.out.println(message + ". Status: " + status.label() + ".");
        }
    }

    public static void main(String[] args) {
        Assignment linkedList = new CodingAssignment("Linked List Lab", LocalDate.of(2027, 3, 10), 50);
        Assignment essay = new WrittenAssignment("Design Essay", LocalDate.of(2027, 3, 12), 50);

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Submission ashaSubmission = Submission.submit(asha, linkedList, LocalDate.of(2027, 3, 10));
        Submission raviSubmission = Submission.submit(ravi, essay, LocalDate.of(2027, 3, 14));

        ashaSubmission.grade(45);
        raviSubmission.grade(40);

        ashaSubmission.resubmit(LocalDate.of(2027, 3, 11));
    }
}
