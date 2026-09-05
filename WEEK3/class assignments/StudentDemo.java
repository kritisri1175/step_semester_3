import java.util.Scanner;

class Student {

    // Instance fields
    String name;
    int attendance;

    // Static fields
    static String collegeName = "SRM Institute of Science and Technology";
    static int studentCount = 0;

    Student(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;

        studentCount++;
    }

    // Static method
    static void printCollegeInfo() {
        System.out.println("College: " + collegeName);
        System.out.println("Students created: " + studentCount);
    }
}

public class StudentDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first student name: ");
        String name1 = sc.nextLine();

        System.out.print("Enter first student attendance: ");
        int attendance1 = sc.nextInt();
        sc.nextLine();

        Student student1 = new Student(name1, attendance1);

        System.out.print("Enter second student name: ");
        String name2 = sc.nextLine();

        System.out.print("Enter second student attendance: ");
        int attendance2 = sc.nextInt();

        Student student2 = new Student(name2, attendance2);

        System.out.println("\nCollege Information:");

        // Calling static method using class name
        Student.printCollegeInfo();

        sc.close();
    }
}