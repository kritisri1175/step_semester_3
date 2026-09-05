import java.util.Scanner;

class MessWallet {

    private double balance;

    public MessWallet(double openingBalance) {

        if (openingBalance < 0) {
            System.out.println("Warning: Negative opening balance. Balance set to 0.");
            balance = 0;
        } else {
            balance = openingBalance;
        }
    }

    public void topUp(double amount) {

        if (amount <= 0) {
            System.out.println("Top-up rejected: amount must be greater than 0");
        } else {
            balance = balance + amount;
            System.out.println("Balance after top-up: " + balance);
        }
    }

    public void deduct(double amount) {

        if (amount <= 0) {
            System.out.println("Deduction rejected: amount must be greater than 0");
        } else if (amount > balance) {
            System.out.println("Deduct rejected: insufficient balance");
        } else {
            balance = balance - amount;
            System.out.println("Balance after deduction: " + balance);
        }
    }

    public double getBalance() {
        return balance;
    }
}

public class MessWalletDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter opening balance: ");
        double openingBalance = sc.nextDouble();

        MessWallet wallet = new MessWallet(openingBalance);

        System.out.print("Enter top-up amount: ");
        double topUpAmount = sc.nextDouble();

        wallet.topUp(topUpAmount);

        System.out.print("Enter deduction amount: ");
        double deductAmount = sc.nextDouble();

        wallet.deduct(deductAmount);

        System.out.println("Final balance: " + wallet.getBalance());

        sc.close();
    }
}
