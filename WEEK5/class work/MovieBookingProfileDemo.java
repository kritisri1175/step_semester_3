import java.util.Scanner;

class MovieBookingProfile {
    private String name;
    private boolean confirmed;
    private String otp;

    public MovieBookingProfile() {
        name = "";
        confirmed = false;
    }

    public MovieBookingProfile(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setOtp(String otp) {
        if (otp.matches("\\d{4,6}")) {
            this.otp = otp;
        } else {
            System.out.println("Invalid OTP");
        }
    }
}

public class MovieBookingProfileDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        MovieBookingProfile profile =
                new MovieBookingProfile(name);

        System.out.print("Confirm booking? (true/false): ");
        boolean confirmed = sc.nextBoolean();

        profile.setConfirmed(confirmed);

        System.out.print("Enter OTP: ");
        String otp = sc.next();

        profile.setOtp(otp);

        System.out.println("\nProfile Details:");
        System.out.println("Name: " + profile.getName());
        System.out.println("Confirmed: " + profile.isConfirmed());

        sc.close();
    }
}