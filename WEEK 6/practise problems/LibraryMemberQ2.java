class LibraryMemberQ2 {
    protected int booksBorrowed;

    public LibraryMemberQ2(String memberId, int borrowLimit) {
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public void displayInfo() {
        System.out.println("General Member | Books Borrowed: " + booksBorrowed);
    }

    public static String classifyGeneration(LibraryMemberQ2 member) {
        if (member instanceof HonorsStudentMemberQ2) {
            return "Multilevel descendant (3 generations deep)";
        }
        if (member instanceof FacultyMemberQ2) {
            return "Hierarchical sibling (independent branch)";
        }
        return "General member";
    }

    public static int getTotalBooksBorrowed(LibraryMemberQ2[] members) {
        int total = 0;
        for (LibraryMemberQ2 member : members) {
            total += member.getBooksBorrowed();
        }
        return total;
    }
}

class StudentMemberQ2 extends LibraryMemberQ2 {
    protected String course;

    public StudentMemberQ2(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public void displayInfo() {
        System.out.println("Student Member | Course: " + course + " | Books Borrowed: " + booksBorrowed);
    }
}

class HonorsStudentMemberQ2 extends StudentMemberQ2 {
    private int bonusLimit;

    public HonorsStudentMemberQ2(String memberId, int borrowLimit, String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    public void displayInfo() {
        System.out.println("Honors Student Member | Course: " + course + " | Bonus Limit: " + bonusLimit + " | Books Borrowed: " + booksBorrowed);
    }
}

class FacultyMemberQ2 extends LibraryMemberQ2 {
    private String department;

    public FacultyMemberQ2(String memberId, int borrowLimit, String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }

    public void displayInfo() {
        System.out.println("Faculty Member | Department: " + department + " | Books Borrowed: " + booksBorrowed);
    }
}