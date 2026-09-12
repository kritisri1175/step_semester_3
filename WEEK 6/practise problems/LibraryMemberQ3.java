class LibraryMemberQ3 {
    private int[] fineHistory = new int[10];
    private int fineCount;

    public LibraryMemberQ3(String memberId, int borrowLimit) {
    }

    protected void chargeFine(int amount) {
        fineHistory[fineCount++] = amount;
    }

    public int[] getFineHistory() {
        return java.util.Arrays.copyOf(fineHistory, fineCount);
    }

    public int getTotalFine() {
        int total = 0;

        for (int i = 0; i < fineCount; i++) {
            total += fineHistory[i];
        }

        return total;
    }
}

class StudentMemberQ3 extends LibraryMemberQ3 {
    public StudentMemberQ3(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
    }

    @Override
    protected void chargeFine(int amount) {
        super.chargeFine(amount / 2);
    }
}