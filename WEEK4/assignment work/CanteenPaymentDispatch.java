import java.util.Scanner;

class Payment {
    public double pay(double amount) {
        System.out.println("Paid (cash): Rs " + amount);
        return amount;
    }
}

class CardPayment extends Payment {
    public double payWithProcessingFee(double amount) {
        double total = amount + (amount * 0.02);

        System.out.println("Charged (card, incl. fee): Rs " + total);

        return total;
    }
}

public class CanteenPaymentDispatch {

    public static double processTransaction(Payment payment, double amount) {
        if (payment instanceof CardPayment) {
            CardPayment cardPayment = (CardPayment) payment;
            return cardPayment.payWithProcessingFee(amount);
        } else {
            return payment.pay(amount);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Payment[] payments = new Payment[5];
        double[] amounts = new double[5];

        for (int i = 0; i < 5; i++) {
            System.out.print("Enter payment type (Card/Cash): ");
            String type = sc.next();

            if (type.equalsIgnoreCase("Card")) {
                payments[i] = new CardPayment();
            } else {
                payments[i] = new Payment();
            }

            System.out.print("Enter amount: ");
            amounts[i] = sc.nextDouble();
        }

        double totalCollected = 0;

        System.out.println("\nPayment Details:");

        for (int i = 0; i < 5; i++) {
            double chargedAmount =
                    processTransaction(payments[i], amounts[i]);

            totalCollected = totalCollected + chargedAmount;
        }

        System.out.println("Total Collected: Rs " + totalCollected);

        sc.close();
    }
}