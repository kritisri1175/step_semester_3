class LibraryMemberQ5 {
    private static int count = 100;

    public final String memberNumber;
    private int borrowLimit;
    private int booksBorrowed;

    public LibraryMemberQ5(int borrowLimit) {
        this.borrowLimit = borrowLimit;
        count++;
        memberNumber = "LIB-" + count;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public void borrowBook(String genre) {
        borrowBook();
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }

        return code.charAt(0) == 'R'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }

    public static int getMembersEnrolled() {
        return count - 100;
    }

    public static String processNightlyAudit(LibraryMemberQ5[] members) {
        int processed = 0;
        int skipped = 0;
        int faculty = 0;
        int regular = 0;

        for (LibraryMemberQ5 member : members) {
            if (member == null) {
                skipped++;
            } else {
                processed++;

                if (member instanceof FacultyMemberQ5) {
                    faculty++;
                } else {
                    regular++;
                }
            }
        }

        return processed + " processed | "
                + skipped + " null skipped | "
                + faculty + " faculty | "
                + regular + " regular";
    }
}

class FacultyMemberQ5 extends LibraryMemberQ5 {
    private String department;

    public FacultyMemberQ5(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }
}