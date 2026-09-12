class GymMemberQ4 {
    protected int sessionsAttended;

    public GymMemberQ4(String memberId, int monthlyFee) {
    }

    public String displayInfo() {
        return "Standard | Sessions: " + sessionsAttended;
    }
}

class PremiumMemberQ4 extends GymMemberQ4 {
    private String trainerName;

    public PremiumMemberQ4(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public String displayInfo() {
        return "Premium | Trainer: " + trainerName + " | Sessions: " + sessionsAttended;
    }
}

class GymReportQ4 {
    public static String batchPrint(GymMemberQ4[] members) {
        StringBuilder sb = new StringBuilder();

        for (GymMemberQ4 member : members) {
            sb.append(member.displayInfo());

            if (member instanceof PremiumMemberQ4) {
                PremiumMemberQ4 premium = (PremiumMemberQ4) member;

                sb.append(" [Trainer via downcast: ")
                        .append(premium.getTrainerName())
                        .append("]");
            }

            sb.append(" | ");
        }

        return sb.toString();
    }
}