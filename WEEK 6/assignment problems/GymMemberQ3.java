class GymMemberQ3 {
    private int[] lateFeeHistory = new int[10];
    private int feeCount;

    public GymMemberQ3(String memberId, int monthlyFee) {
    }

    protected void chargeLateFee(int amount) {
        lateFeeHistory[feeCount++] = amount;
    }

    public int[] getLateFeeHistory() {
        return java.util.Arrays.copyOf(lateFeeHistory, feeCount);
    }

    public int getTotalLateFees() {
        int total = 0;

        for (int i = 0; i < feeCount; i++) {
            total += lateFeeHistory[i];
        }

        return total;
    }
}

class PremiumMemberQ3 extends GymMemberQ3 {
    public PremiumMemberQ3(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
    }

    @Override
    protected void chargeLateFee(int amount) {
        super.chargeLateFee(amount / 2);
    }
}