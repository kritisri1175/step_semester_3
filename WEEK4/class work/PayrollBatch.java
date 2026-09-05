import java.util.Scanner;

class Employee {

    String empId;
    double salary;

    // Constructor
    public Employee(String empId, double salary) {
        this.empId = empId;
        this.salary = salary;
    }

    // Parameter and field have the same name
    public void raiseSalary(double salary) {
        this.salary = this.salary + salary;
    }

    public void printSalary() {
        System.out.println(empId + " | Final Salary: Rs " + salary);
    }
}

public class PayrollBatch {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of employees: ");
        int n = sc.nextInt();

        Employee[] employees = new Employee[n];

        for (int i = 0; i < n; i++) {

            System.out.print("Enter employee ID: ");
            String empId = sc.next();

            System.out.print("Enter starting salary: ");
            double salary = sc.nextDouble();

            employees[i] = new Employee(empId, salary);
        }

        System.out.print("Enter bonus amount: ");
        double bonus = sc.nextDouble();

        // Single pass
        for (Employee employee : employees) {
            employee.raiseSalary(bonus);
            employee.printSalary();
        }

        sc.close();
    }
}