import java.util.Scanner;

class Course {

    String code;
    String title;
    int credits;
    int labCredits;

    public Course(String code, String title, int credits, int labCredits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.labCredits = labCredits;
    }

    public Course(String code, String title, int credits) {
        this(code, title, credits, 0);
    }

    public int totalCredits() {
        return credits + labCredits;
    }
}

public class CourseDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Theory-only course
        System.out.println("Enter theory-only course details:");

        System.out.print("Enter code: ");
        String code1 = sc.nextLine();

        System.out.print("Enter title: ");
        String title1 = sc.nextLine();

        System.out.print("Enter credits: ");
        int credits1 = sc.nextInt();
        sc.nextLine();

        Course theoryCourse = new Course(code1, title1, credits1);

        // Course with lab
        System.out.println("\nEnter course with lab details:");

        System.out.print("Enter code: ");
        String code2 = sc.nextLine();

        System.out.print("Enter title: ");
        String title2 = sc.nextLine();

        System.out.print("Enter credits: ");
        int credits2 = sc.nextInt();

        System.out.print("Enter lab credits: ");
        int labCredits = sc.nextInt();

        Course labCourse = new Course(code2, title2, credits2, labCredits);

        System.out.println("\nTotal Credits:");

        System.out.println(code1 + " total credits: "
                + theoryCourse.totalCredits());

        System.out.println(code2 + " total credits: "
                + labCourse.totalCredits());

        sc.close();
    }
}