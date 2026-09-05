import java.util.Scanner;

class MovieTicket {
    private String seatNumber;
    String screenId;              // default
    protected double ticketPrice;
    public String movieTitle;

    public MovieTicket(String seatNumber, String screenId,
                       double ticketPrice, String movieTitle) {
        this.seatNumber = seatNumber;
        this.screenId = screenId;
        this.ticketPrice = ticketPrice;
        this.movieTitle = movieTitle;
    }
}

public class AccessChecker {

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

    public static String summarizeBatch(String[][] attempts) {
        int allowed = 0;
        int denied = 0;

        for (int i = 0; i < attempts.length; i++) {
            String result = classifyAccess(attempts[i][0], attempts[i][1]);

            if (result.equals("ALLOWED")) {
                allowed++;
            } else {
                denied++;
            }
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of access attempts: ");
        int n = sc.nextInt();

        String[][] attempts = new String[n][2];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter field modifier (private/default/protected/public): ");
            attempts[i][0] = sc.next();

            System.out.print("Enter context: ");
            attempts[i][1] = sc.next();
        }

        System.out.println("\nResults:");

        for (int i = 0; i < n; i++) {
            System.out.println(classifyAccess(
                    attempts[i][0],
                    attempts[i][1]));
        }

        System.out.println("\n" + summarizeBatch(attempts));

        sc.close();
    }
}