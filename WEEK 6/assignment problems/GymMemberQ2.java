class GymMemberQ2 {
    protected int sessionsAttended;

    public GymMemberQ2(String memberId, int monthlyFee) {
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public void displayInfo() {
        System.out.println("Standard Member | Sessions: " + sessionsAttended);
    }

    public static String classifyGeneration(GymMemberQ2 member) {
        if (member instanceof EliteMemberQ2) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (member instanceof GroupClassMemberQ2) {
            return "Hierarchical sibling (independent branch)";
        }

        return "Standard member";
    }

    public static int getTotalSessionsAttended(GymMemberQ2[] members) {
        int total = 0;

        for (GymMemberQ2 member : members) {
            total += member.getSessionsAttended();
        }

        return total;
    }
}

class PremiumMemberQ2 extends GymMemberQ2 {
    protected String trainerName;

    public PremiumMemberQ2(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    @Override
    public void displayInfo() {
        System.out.println("Premium Member | Trainer: " + trainerName + " | Sessions: " + sessionsAttended);
    }
}

class EliteMemberQ2 extends PremiumMemberQ2 {
    private String lockerNumber;

    public EliteMemberQ2(String memberId, int monthlyFee, String trainerName, String lockerNumber) {
        super(memberId, monthlyFee, trainerName);
        this.lockerNumber = lockerNumber;
    }

    @Override
    public void displayInfo() {
        System.out.println("Elite Member | Trainer: " + trainerName + " | Locker: " + lockerNumber + " | Sessions: " + sessionsAttended);
    }
}

class GroupClassMemberQ2 extends GymMemberQ2 {
    private String className;

    public GroupClassMemberQ2(String memberId, int monthlyFee, String className) {
        super(memberId, monthlyFee);
        this.className = className;
    }

    @Override
    public void displayInfo() {
        System.out.println("Group Class Member | Class: " + className + " | Sessions: " + sessionsAttended);
    }
}