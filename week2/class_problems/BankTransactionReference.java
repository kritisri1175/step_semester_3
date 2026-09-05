import java.util.Scanner;

public class BankTransactionReference {

    public static String normalizeReference(String raw) {

        String reference = raw.trim();

        if (reference.length() < 3) {
            return reference.toUpperCase();
        }

        String bankCode = reference.substring(0, 3).toUpperCase();
        String remaining = reference.substring(3);

        return bankCode + remaining;
    }

    public static String validateAndFormat(String reference) {

        // Check length
        if (reference.length() != 14) {
            return "Invalid: wrong length";
        }

        // Check bank code
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        // Check remaining 11 characters
        for (int i = 3; i < reference.length(); i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: body must contain only digits";
            }
        }

        // Extract date and sequence
        String date = reference.substring(3, 9);
        String sequence = reference.substring(9, 14);

        // Convert ddMMyy to dd/MM/yy
        String formattedDate = date.substring(0, 2) + "/"
                + date.substring(2, 4) + "/"
                + date.substring(4, 6);

        // Build final output
        StringBuilder result = new StringBuilder();

        result.append("[")
                .append(reference.substring(0, 3))
                .append("] DATE: ")
                .append(formattedDate)
                .append(" | SEQ: ")
                .append(sequence);

        return result.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter transaction reference: ");
        String rawReference = sc.nextLine();

        String normalized = normalizeReference(rawReference);

        System.out.println(validateAndFormat(normalized));

        sc.close();
    }
}