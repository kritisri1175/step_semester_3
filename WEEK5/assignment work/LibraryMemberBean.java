import java.util.Scanner;

class LibraryMember2 {

    private String membershipId;
    private String name;
    private boolean premiumMember;

    private String securityAnswer;

    public LibraryMember2() {
        membershipId = null;
        name = "";
        premiumMember = false;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {

        if (membershipId == null) {
            membershipId = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    public void setSecurityAnswer(String answer) {

        // Simple deterministic one-way transformation
        securityAnswer = Integer.toHexString(answer.hashCode());
    }
}

class LibraryMemberBean {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        LibraryMember2 member = new LibraryMember2();

        System.out.print("Enter membership ID: ");
        String id = sc.nextLine();
        member.setMembershipId(id);

        System.out.print("Enter member name: ");
        String name = sc.nextLine();
        member.setName(name);

        System.out.print("Is premium member? (true/false): ");
        boolean premium = sc.nextBoolean();
        member.setPremiumMember(premium);

        sc.nextLine();

        System.out.print("Enter security answer: ");
        String answer = sc.nextLine();
        member.setSecurityAnswer(answer);

        System.out.print("Enter another membership ID: ");
        String secondId = sc.nextLine();
        member.setMembershipId(secondId);

        System.out.println("\nLibrary Member Details:");
        System.out.println("Membership ID: "
                + member.getMembershipId());
        System.out.println("Name: "
                + member.getName());
        System.out.println("Premium Member: "
                + member.isPremiumMember());

        sc.close();
    }
}