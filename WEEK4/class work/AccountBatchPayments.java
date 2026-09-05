import java.util.Scanner;

class FeeAccount1 {

    String accountType;

    public FeeAccount(String accountType) {
        this.accountType = accountType;
    }
}

class HostelFeeAccount extends FeeAccount {

    public HostelFeeAccount() {
        super("Hostel");
    }
}

public class AccountBatchPayments {

    public static void processPayment(FeeAccount account, double amount) {

        if (account instanceof HostelFeeAccount) {
            System.out.println("Paid in two installments (hostel account)");
        } else {
            System.out.println("Paid in one go (day-scholar account)");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of accounts: ");
        int n = sc.nextInt();

        FeeAccount[] accounts = new FeeAccount[n];

        int hostelCount = 0;
        int dayScholarCount = 0;

        for (int i = 0; i < n; i++) {

            System.out.print("Enter account type (Hostel/FeeAccount): ");
            String type = sc.next();

            if (type.equalsIgnoreCase("Hostel")) {
                accounts[i] = new HostelFeeAccount();
            } else {
                accounts[i] = new FeeAccount("FeeAccount");
            }
        }

        System.out.print("Enter payment amount: ");
        double amount = sc.nextDouble();

        // Single pass
        for (FeeAccount account : accounts) {

            processPayment(account, amount);

            if (account instanceof HostelFeeAccount) {
                hostelCount++;
            } else {
                dayScholarCount++;
            }
        }

        System.out.println("\nHostel accounts processed: " + hostelCount
                + " | Day-scholar accounts processed: "
                + dayScholarCount);

        sc.close();
    }
}