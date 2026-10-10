import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CampusNoticeBroadcaster {

    private static final String ARROW = "→";

    interface NotificationChannel {
        String getName();

        void send(Student student, String message);
    }

    // each channel delivers the message in its own way
    static class EmailChannel implements NotificationChannel {

        @Override
        public String getName() {
            return "Email";
        }

        @Override
        public void send(Student student, String message) {
            System.out.println("[Email " + ARROW + " " + student.getName() + "] " + message);
        }
    }

    static class SmsChannel implements NotificationChannel {

        @Override
        public String getName() {
            return "SMS";
        }

        @Override
        public void send(Student student, String message) {
            String text = message.length() > 160 ? message.substring(0, 157) + "..." : message;
            System.out.println("[SMS " + ARROW + " " + student.getName() + "] " + text);
        }
    }

    static class AppChannel implements NotificationChannel {

        @Override
        public String getName() {
            return "App";
        }

        @Override
        public void send(Student student, String message) {
            System.out.println("[App " + ARROW + " " + student.getName() + "] " + message);
        }
    }

    static class Student {

        private final String name;
        private final String department;
        private final List<NotificationChannel> preferredChannels = new ArrayList<>();

        Student(String name, String department) {
            this.name = name;
            this.department = department;
        }

        String getName() {
            return name;
        }

        String getDepartment() {
            return department;
        }

        void addPreferredChannel(NotificationChannel channel) {
            preferredChannels.add(channel);
        }

        List<NotificationChannel> getPreferredChannels() {
            return preferredChannels;
        }
    }

    static class Notice {

        private final String title;
        private final List<String> targetDepartments;

        // validation lives in the Notice itself, so an invalid notice can never exist
        Notice(String title, List<String> targetDepartments) {
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("A title is required.");
            }
            if (targetDepartments == null || targetDepartments.isEmpty()) {
                throw new IllegalArgumentException("At least one target department is required.");
            }
            this.title = title.trim();
            this.targetDepartments = new ArrayList<>(targetDepartments);
        }

        String getTitle() {
            return title;
        }

        List<String> getTargetDepartments() {
            return targetDepartments;
        }

        boolean targets(String department) {
            for (String d : targetDepartments) {
                if (d.equalsIgnoreCase(department)) {
                    return true;
                }
            }
            return false;
        }
    }

    static class NoticeBoard {

        private final List<Student> students = new ArrayList<>();

        void register(Student student) {
            students.add(student);
        }

        void post(String title, String... departments) {
            Notice notice;
            try {
                notice = new Notice(title, Arrays.asList(departments));
            } catch (IllegalArgumentException e) {
                System.out.println("Cannot post notice: " + e.getMessage());
                return;
            }
            System.out.println("Notice '" + notice.getTitle() + "' posted to "
                    + String.join(", ", notice.getTargetDepartments()) + ".");
            for (Student student : students) {
                if (notice.targets(student.getDepartment())) {
                    for (NotificationChannel channel : student.getPreferredChannels()) {
                        channel.send(student, notice.getTitle());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();

        Student asha = new Student("Asha", "CSE");
        asha.addPreferredChannel(new EmailChannel());
        asha.addPreferredChannel(new AppChannel());

        Student ravi = new Student("Ravi", "ECE");
        ravi.addPreferredChannel(new SmsChannel());

        board.register(asha);
        board.register(ravi);

        board.post("Lab Closed Tomorrow", "CSE");
        board.post("Fee Deadline Extended", "CSE", "ECE");
        board.post("Sports Day");
    }
}
