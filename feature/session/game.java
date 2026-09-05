
import java.util.*;

class game {

    public static String getResult(String player, String computer) {
        if (player.equals(computer)) {
            return "Draw";
        }

        if ((player.equals("Rock") && computer.equals("Scissors")) ||
                (player.equals("Paper") && computer.equals("Rock")) ||
                (player.equals("Scissors") && computer.equals("Paper"))) {
            return "Win";
        }

        return "Loss";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        String[] moves = {"Rock", "Paper", "Scissors"};

        int wins = 0;
        int losses = 0;
        int draws = 0;

        System.out.println("Rock-Paper-Scissors Game");

        for (int round = 1; round <= 5; round++) {
            System.out.print("Round " + round + " - Enter Rock, Paper, or Scissors: ");
            String player = sc.nextLine();

            player = player.substring(0, 1).toUpperCase() +
                    player.substring(1).toLowerCase();

            String computer = moves[random.nextInt(3)];

            String result = getResult(player, computer);

            if (result.equals("Win")) {
                wins++;
            } else if (result.equals("Loss")) {
                losses++;
            } else {
                draws++;
            }

            System.out.println("Computer Move: " + computer);
            System.out.println("Result: " + result);
            System.out.println();
        }

        double winPercentage = (wins / 5.0) * 100;

        System.out.println("Round Summary");
        System.out.println("--------------------------------");
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.println("Draws: " + draws);
        System.out.printf("Win Percentage: %.2f%%%n", winPercentage);
    }
}
