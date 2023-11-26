import java.util.Collections;
import java.util.Comparator;

public class Employee {
    int employeeId;
    String firstName;
    private String last_name;
    private int age;
    protected double salary;

    public Employee(int employeeId, String firstName, String last_name, int age, double salary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.last_name = last_name;
        this.age = age;
        this.salary = salary;
    }


    static void sortEmployeesByName() {
        Collections.sort(Main.employees, Comparator.comparing(e -> e.firstName));
        System.out.println("Employees sorted by name.");
    }

    static void sortEmployeesBySalary() {
        Collections.sort(Main.employees, Comparator.comparingInt(e -> (int) e.salary));
        System.out.println("Employees sorted by salary.");
    }
}
