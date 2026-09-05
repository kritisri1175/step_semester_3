import java.util.Scanner;

class Employee {

    String empId;
    String empName;
    double salary;
    boolean isIntern;

    // Constructor for permanent employee
    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.isIntern = false;
    }

    // Constructor for intern
    public Employee(String empId, String empName) {
        this(empId, empName, 0);
        this.isIntern = true;
    }

    public void printProfile() {
        System.out.println(empId + " | "
                + empName + " | Rs "
                + salary + " | Intern: "
                + isIntern);
    }
}

public class EmployeeDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Permanent Employee
        System.out.println("Enter Permanent Employee Details:");

        System.out.print("Enter Employee ID: ");
        String empId1 = sc.nextLine();

        System.out.print("Enter Employee Name: ");
        String empName1 = sc.nextLine();

        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();
        sc.nextLine();

        Employee permanent = new Employee(empId1, empName1, salary);

        // Intern
        System.out.println("\nEnter Intern Details:");

        System.out.print("Enter Employee ID: ");
        String empId2 = sc.nextLine();

        System.out.print("Enter Employee Name: ");
        String empName2 = sc.nextLine();

        Employee intern = new Employee(empId2, empName2);

        System.out.println("\nEmployee Profiles:");

        permanent.printProfile();
        intern.printProfile();

        sc.close();
    }
}