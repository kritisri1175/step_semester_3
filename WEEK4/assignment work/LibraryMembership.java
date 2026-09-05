import java.util.Scanner;

class MembershipCard {
    static String libraryName;
    static String validUntil;

    String studentName;

    static {
        libraryName = "SRM Central Library";
        validUntil = "May 2027";

        System.out.println("Library info loaded");
    }

    public MembershipCard(String studentName) {
        this.studentName = studentName;
    }

    public void printConfirmation() {
        System.out.println("Membership card issued: " + studentName);
    }
}

public class LibraryMembership {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[] names = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter student name: ");
            names[i] = sc.nextLine();
        }

        System.out.println();

        for (int i = 0; i < n; i++) {
            MembershipCard card = new MembershipCard(names[i]);
            card.printConfirmation();
        }

        sc.close();
    }
}