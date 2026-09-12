class LibraryMemberQ4 {
    protected int booksBorrowed;

    public LibraryMemberQ4(String memberId, int borrowLimit) {
    }

    public String displayInfo() {
        return "General | Books: " + booksBorrowed;
    }
}

class StudentMemberQ4 extends LibraryMemberQ4 {
    private String course;

    public StudentMemberQ4(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String displayInfo() {
        return "Student | Course: " + course + " | Books: " + booksBorrowed;
    }
}

class LibraryReportQ4 {
    public static String batchPrint(LibraryMemberQ4[] members) {
        StringBuilder sb = new StringBuilder();

        for (LibraryMemberQ4 member : members) {
            sb.append(member.displayInfo());

            if (member instanceof StudentMemberQ4) {
                StudentMemberQ4 student = (StudentMemberQ4) member;
                sb.append(" [Course via downcast: ")
                        .append(student.getCourse())
                        .append("]");
            }

            sb.append(" | ");
        }

        return sb.toString();
    }
}