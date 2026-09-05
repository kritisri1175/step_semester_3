import java.util.Scanner;

class LibraryMember1 {
    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember1(String membershipPin, String branchCode,
                         double finesOwed, String displayName) {
        this.membershipPin = membershipPin;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}

class PremiumLibraryMember extends LibraryMember {

    public PremiumLibraryMember(String membershipPin,
                                String branchCode,
                                double finesOwed,
                                String displayName) {

        super(membershipPin, branchCode, finesOwed, displayName);
    }
}

public class ReferenceDeskAccess {

    public static String classifyAccess(String fieldModifier,
                                        String accessorContext) {

        if (fieldModifier.equals("private")) {

            if (accessorContext.equals("SAME_CLASS")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("default")) {

            if (accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")
                    || accessorContext.equals(
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    public static String firstDeniedAttempt(String[][] attempts) {

        for (int i = 0; i < attempts.length; i++) {

            String modifier = attempts[i][0];
            String context = attempts[i][1];

            String result = classifyAccess(modifier, context);

            if (result.equals("DENIED")) {

                return modifier
                        + " via "
                        + context
                        + " (attempt #"
                        + (i + 1)
                        + ")";
            }
        }

        return "None Denied";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of attempts: ");
        int n = sc.nextInt();

        String[][] attempts = new String[n][2];

        for (int i = 0; i < n; i++) {

            System.out.print("Enter modifier: ");
            attempts[i][0] = sc.next();

            System.out.print("Enter context: ");
            attempts[i][1] = sc.next();
        }

        System.out.println("\nFirst Denied Attempt:");

        System.out.println(firstDeniedAttempt(attempts));

        sc.close();
    }
}