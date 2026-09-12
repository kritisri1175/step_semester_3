class GymMemberQ5 {
    private static int counter = 2000;

    public final String membershipNumber;

    private int monthlyFee;
    private int feesPaid;

    public GymMemberQ5(int monthlyFee) {
        this.monthlyFee = monthlyFee;
        counter++;
        membershipNumber = "GYM-" + counter;
    }

    public void payFee(int amount) {
        feesPaid += amount;
    }

    public void payFee(int amount, String mode) {
        payFee(amount);
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }

        return code.charAt(0) == 'G'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }

    public static int getMembersEnrolled() {
        return counter - 2000;
    }

    public static String processWeeklyCheckIn(GymMemberQ5[] members) {
        int processed = 0;
        int skipped = 0;
        int group = 0;
        int individual = 0;

        for (GymMemberQ5 member : members) {
            if (member == null) {
                skipped++;
            } else {
                processed++;

                if (member instanceof GroupClassMemberQ5) {
                    group++;
                } else {
                    individual++;
                }
            }
        }

        return processed + " processed | "
                + skipped + " null skipped | "
                + group + " group | "
                + individual + " individual";
    }
}

class GroupClassMemberQ5 extends GymMemberQ5 {
    private String className;

    public GroupClassMemberQ5(int monthlyFee, String className) {
        super(monthlyFee);
        this.className = className;
    }
}