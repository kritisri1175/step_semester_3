import java.util.Scanner;

class SrmStudent {

    String name;

    static String collegeName;
    static String academicYear;

    // Static block runs only once
    static {
        collegeName = "SRM Institute of Science and Technology";
        academicYear = "2026-2027";

        System.out.println("College info loaded");
    }

    public SrmStudent(String name) {
        this.name = name;
    }

    public void printConfirmation() {
        System.out.println("Student record created: " + name);
    }
}

public class SrmStudentDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        SrmStudent[] students = new SrmStudent[n];

        for (int i = 0; i < n; i++) {

            System.out.print("Enter student name: ");
            String name = sc.nextLine();

            students[i] = new SrmStudent(name);
        }

        System.out.println("\nStudent Records:");

        for (SrmStudent student : students) {
            student.printConfirmation();
        }

        sc.close();
    }
}