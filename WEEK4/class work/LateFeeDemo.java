import java.util.Scanner;

class FeeAccount {

    String regNo;
    double totalFees;

    public FeeAccount(String regNo, double totalFees) {
        this.regNo = regNo;
        this.totalFees = totalFees;
    }

    // final method cannot be overridden
    public final double calculateLateFee(int daysLate) {
        return totalFees * daysLate / 100.0;
    }

    // final method cannot be overridden
    public final void printSummary(int daysLate) {

        if (daysLate <= 0) {
            System.out.println(regNo + " - On time, no late fee");
        } else {

            double lateFee = calculateLateFee(daysLate);

            System.out.println(regNo
                    + " | Total Fee: Rs " + totalFees
                    + " | Late Fee: Rs " + lateFee);
        }
    }
}

public class LateFeeDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of accounts: ");
        int n = sc.nextInt();

        FeeAccount[] accounts = new FeeAccount[n];
        int[] daysLate = new int[n];

        for (int i = 0; i < n; i++) {

            System.out.print("Enter registration number: ");
            String regNo = sc.next();

            System.out.print("Enter total fees: ");
            double totalFees = sc.nextDouble();

            System.out.print("Enter days late: ");
            daysLate[i] = sc.nextInt();

            accounts[i] = new FeeAccount(regNo, totalFees);
        }

        System.out.println("\nFee Summary:");

        // Single pass
        for (int i = 0; i < n; i++) {
            accounts[i].printSummary(daysLate[i]);
        }

        sc.close();
    }
}