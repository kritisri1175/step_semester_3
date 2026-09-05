import java.util.Scanner;

class Participant {
    String name;
    String teamName;
    boolean registered;

    public Participant(String name, String teamName) {
        this.name = name;
        this.teamName = teamName;
        this.registered = true;
    }

    public Participant(String name) {
        this(name, "Unassigned");
    }

    public void printStatus() {
        System.out.println(name + " | " + teamName + " | Registered: " + registered);
    }
}

public class HackathonRegistration {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of participants: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[] names = new String[n];
        String[] teamNames = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter participant name: ");
            names[i] = sc.nextLine();

            System.out.print("Enter team name (leave blank if solo): ");
            teamNames[i] = sc.nextLine();
        }

        System.out.println("\nParticipant Status:");

        for (int i = 0; i < n; i++) {
            Participant participant;

            if (teamNames[i].isEmpty()) {
                participant = new Participant(names[i]);
            } else {
                participant = new Participant(names[i], teamNames[i]);
            }

            participant.printStatus();
        }

        sc.close();
    }
}