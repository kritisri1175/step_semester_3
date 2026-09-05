import java.util.Scanner;

class LibraryMember {
    private String membershipPin;
    String branchCode;              // default access
    protected double finesOwed;
    public String displayName;

    public LibraryMember(String membershipPin, String branchCode,
                         double finesOwed, String displayName) {
        this.membershipPin = membershipPin;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}

class AccessChecker1 {

    public static String classifyAccess(String fieldModifier,
                                        String accessorContext) {

        if (fieldModifier.equals("private")) {
            if (accessorContext.equals("SAME_CLASS")) {
                return "ALLOWED";
            } else {
                return "DENIED";
            }
        }

        if (fieldModifier.equals("default")) {
            if (accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            } else {
                return "DENIED";
            }
        }

        if (fieldModifier.equals("protected")) {
            if (accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            } else {
                return "DENIED";
            }
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    public static String summarizeByModifier(String[][] attempts) {

        String[] modifiers = {"private", "default", "protected", "public"};

        int[] allowed = new int[4];
        int[] denied = new int[4];

        for (int i = 0; i < attempts.length; i++) {

            String modifier = attempts[i][0];
            String context = attempts[i][1];

            String result = classifyAccess(modifier, context);

            for (int j = 0; j < modifiers.length; j++) {

                if (modifier.equals(modifiers[j])) {

                    if (result.equals("ALLOWED")) {
                        allowed[j]++;
                    } else {
                        denied[j]++;
                    }

                    break;
                }
            }
        }

        return "private: " + allowed[0] + " allowed / " + denied[0] + " denied"
                + " | default: " + allowed[1] + " allowed / " + denied[1] + " denied"
                + " | protected: " + allowed[2] + " allowed / " + denied[2] + " denied"
                + " | public: " + allowed[3] + " allowed / " + denied[3] + " denied";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of attempts: ");
        int n = sc.nextInt();

        String[][] attempts = new String[n][2];

        for (int i = 0; i < n; i++) {

            System.out.print("Enter modifier (private/default/protected/public): ");
            attempts[i][0] = sc.next();

            System.out.print("Enter context: ");
            attempts[i][1] = sc.next();
        }

        System.out.println("\nAccess Results:");

        for (int i = 0; i < n; i++) {
            System.out.println(
                    classifyAccess(attempts[i][0], attempts[i][1])
            );
        }

        System.out.println("\nSummary:");
        System.out.println(summarizeByModifier(attempts));

        sc.close();
    }
}