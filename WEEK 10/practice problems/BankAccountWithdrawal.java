import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * Sample input (one operation per line):
 *   Savings S1 5000
 *   WITHDRAW S1 4500
 *   Current C1 2000
 *   WITHDRAW C1 6000
 *   WITHDRAW S1 3000
 *   WITHDRAW C1 2000
 */
public class BankAccountWithdrawal {

    static abstract class Account {

        private final String id;
        protected long balance;

        Account(String id, long initialBalance) {
            this.id = id;
            this.balance = initialBalance;
        }

        String getId() {
            return id;
        }

        // each account type has its own rule; returns the rejection reason, or null if allowed
        abstract String checkRule(long amount);

        String withdraw(long amount) {
            if (amount <= 0) {
                return id + " rejected: invalid amount";
            }
            String reason = checkRule(amount);
            if (reason != null) {
                return id + " rejected: " + reason;
            }
            balance -= amount;
            return id + " balance " + balance;
        }
    }

    static class SavingsAccount extends Account {

        private static final long MIN_BALANCE = 1000;

        SavingsAccount(String id, long initialBalance) {
            super(id, initialBalance);
        }

        @Override
        String checkRule(long amount) {
            if (balance - amount < MIN_BALANCE) {
                return "minimum balance " + MIN_BALANCE;
            }
            return null;
        }
    }

    static class CurrentAccount extends Account {

        private static final long OVERDRAFT_LIMIT = 5000;

        CurrentAccount(String id, long initialBalance) {
            super(id, initialBalance);
        }

        @Override
        String checkRule(long amount) {
            if (balance - amount < -OVERDRAFT_LIMIT) {
                return "overdraft limit " + OVERDRAFT_LIMIT;
            }
            return null;
        }
    }

    static void run(List<String> lines) {
        Map<String, Account> accounts = new LinkedHashMap<>();

        for (String line : lines) {
            String[] t = line.trim().split("\\s+");
            if (t.length < 3) {
                continue;
            }
            String command = t[0];
            String id = t[1];
            long value = Long.parseLong(t[2]);

            if (command.equalsIgnoreCase("Savings")) {
                accounts.putIfAbsent(id, new SavingsAccount(id, value));
            } else if (command.equalsIgnoreCase("Current")) {
                accounts.putIfAbsent(id, new CurrentAccount(id, value));
            } else if (command.equalsIgnoreCase("WITHDRAW")) {
                Account account = accounts.get(id);
                if (account == null) {
                    System.out.println("Account not found");
                } else {
                    System.out.println(account.withdraw(value));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                lines.add(line);
            }
        }
        run(lines);
    }
}
