import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Sample input (one operation per line):
 *   ADD 21CS01 Asha
 *   ADD 21CS01 Asha
 *   ADD 21CS02 Ravi
 *   CONTAINS 21CS02 Ravi
 *   CONTAINS 21CS03 Priya
 */
public class StudentClubRegistration {

    static class Student {

        private final String rollNumber;
        private final String name;

        Student(String rollNumber, String name) {
            this.rollNumber = rollNumber;
            this.name = name;
        }

        String getRollNumber() {
            return rollNumber;
        }

        String getName() {
            return name;
        }

        // only the roll number decides whether two students are the same
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Student)) {
                return false;
            }
            Student other = (Student) o;
            return rollNumber.equals(other.rollNumber);
        }

        // must agree with equals(), otherwise HashSet could miss duplicates
        @Override
        public int hashCode() {
            return rollNumber.hashCode();
        }
    }

    static String joinFrom(String[] tokens, int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < tokens.length; i++) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append(tokens[i]);
        }
        return sb.toString();
    }

    static void run(List<String> lines) {
        Set<Student> members = new HashSet<>();

        // the member count is printed right after the last ADD operation
        int lastAdd = -1;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).trim().toUpperCase().startsWith("ADD")) {
                lastAdd = i;
            }
        }

        for (int i = 0; i < lines.size(); i++) {
            String[] t = lines.get(i).trim().split("\\s+");
            if (t.length < 2) {
                continue;
            }
            // a new object is created for every operation, even for the same student
            Student student = new Student(t[1], joinFrom(t, 2));

            if (t[0].equalsIgnoreCase("ADD")) {
                System.out.println(members.add(student) ? "Added" : "duplicate rejected");
                if (i == lastAdd) {
                    System.out.println("member count " + members.size());
                }
            } else if (t[0].equalsIgnoreCase("CONTAINS")) {
                System.out.println("contains: " + members.contains(student));
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
