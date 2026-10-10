import java.util.Locale;

public class FitZoneMembershipDesk {

    private static final String RUPEE = "₹";
    private static final double BASE_RATE_PER_MONTH = 1000.0;

    static String money(double amount) {
        return RUPEE + String.format(Locale.US, "%,.2f", amount);
    }

    // each plan implements its own fee and discount rule
    static abstract class MembershipPlan {

        abstract String getName();

        abstract int getMonths();

        abstract double calculateFee();
    }

    static class MonthlyPlan extends MembershipPlan {

        @Override
        String getName() {
            return "Monthly";
        }

        @Override
        int getMonths() {
            return 1;
        }

        @Override
        double calculateFee() {
            return BASE_RATE_PER_MONTH * getMonths();
        }
    }

    static class QuarterlyPlan extends MembershipPlan {

        @Override
        String getName() {
            return "Quarterly";
        }

        @Override
        int getMonths() {
            return 3;
        }

        @Override
        double calculateFee() {
            return BASE_RATE_PER_MONTH * getMonths() * 0.90; // 10% off
        }
    }

    static class AnnualPlan extends MembershipPlan {

        @Override
        String getName() {
            return "Annual";
        }

        @Override
        int getMonths() {
            return 12;
        }

        @Override
        double calculateFee() {
            return BASE_RATE_PER_MONTH * getMonths() * 0.75; // 25% off
        }
    }

    static class Member {

        private final String name;

        Member(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        Membership buy(MembershipPlan plan) {
            return Membership.create(this, plan);
        }
    }

    enum Status {
        ACTIVE, FROZEN, EXPIRED;

        String label() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }

    static class Membership {

        private final Member member;
        private final MembershipPlan plan;
        private final double fee;
        private Status status = Status.ACTIVE; // changed only through the methods below

        private Membership(Member member, MembershipPlan plan) {
            this.member = member;
            this.plan = plan;
            this.fee = plan.calculateFee();
        }

        static Membership create(Member member, MembershipPlan plan) {
            Membership membership = new Membership(member, plan);
            System.out.println(plan.getName() + " membership created for " + member.getName()
                    + ". Fee: " + money(membership.fee) + ". Status: " + membership.status.label() + ".");
            return membership;
        }

        Status getStatus() {
            return status;
        }

        void checkIn() {
            if (status != Status.ACTIVE) {
                System.out.println("Check-in denied: " + member.getName() + "'s membership is " + status.label() + ".");
                return;
            }
            System.out.println(member.getName() + " checked in successfully.");
        }

        void freeze() {
            if (status == Status.EXPIRED) {
                System.out.println("Cannot freeze an Expired membership.");
                return;
            }
            if (status == Status.FROZEN) {
                System.out.println(member.getName() + "'s membership is already Frozen.");
                return;
            }
            status = Status.FROZEN;
            System.out.println(member.getName() + "'s membership frozen. Status: " + status.label() + ".");
        }

        void unfreeze() {
            if (status == Status.EXPIRED) {
                System.out.println("Cannot unfreeze an Expired membership.");
                return;
            }
            if (status != Status.FROZEN) {
                System.out.println(member.getName() + "'s membership is not Frozen.");
                return;
            }
            status = Status.ACTIVE;
            System.out.println(member.getName() + "'s membership unfrozen. Status: " + status.label() + ".");
        }

        void expire() {
            if (status == Status.EXPIRED) {
                System.out.println(member.getName() + "'s membership is already Expired.");
                return;
            }
            status = Status.EXPIRED;
            System.out.println(member.getName() + "'s membership expired. Status: " + status.label() + ".");
        }
    }

    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership ashaMembership = asha.buy(new QuarterlyPlan());
        Membership raviMembership = ravi.buy(new MonthlyPlan());

        ashaMembership.checkIn();
        ashaMembership.freeze();
        ashaMembership.checkIn();

        raviMembership.expire();
        raviMembership.freeze();
    }
}
