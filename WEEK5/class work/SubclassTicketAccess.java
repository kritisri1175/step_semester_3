import java.util.Scanner;

class MovieTicket {
    private String seatNumber;
    String screenId;
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

class PremiumMovieTicket extends MovieTicket {

    public PremiumMovieTicket(String seatNumber, String screenId,
                              double ticketPrice, String movieTitle) {
        super(seatNumber, screenId, ticketPrice, movieTitle);
    }
}

public class SubclassTicketAccess {

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

            if (accessorContext.equals(
                    "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")) {
                return "DENIED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter field modifier: ");
        String modifier = sc.next();

        System.out.println("Contexts:");
        System.out.println("1. SAME_CLASS");
        System.out.println("2. SAME_PACKAGE");
        System.out.println("3. DIFFERENT_PACKAGE");
        System.out.println("4. SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE");
        System.out.println("5. SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE");

        System.out.print("Enter context: ");
        String context = sc.next();

        String result = classifyAccess(modifier, context);

        System.out.println("Access: " + result);

        sc.close();
    }
}