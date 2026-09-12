class LibraryMemberQ1 {
    private String memberId;
    private int borrowLimit;
    private int booksBorrowed;

    public LibraryMemberQ1(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
            throw new IllegalArgumentException();
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public static String enrollBatch(String[] memberIds, int borrowLimit) {
        int enrolled = 0;
        int rejected = 0;

        for (String id : memberIds) {
            try {
                new LibraryMemberQ1(id, borrowLimit);
                enrolled++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Enrolled: " + enrolled + " | Rejected: " + rejected;
    }
}

class StudentMemberQ1 extends LibraryMemberQ1 {
    private String course;

    public StudentMemberQ1(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }
}