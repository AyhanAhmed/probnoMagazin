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

    public int getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLast_name() {
        return last_name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    static void sortEmployeesByName() {
        Collections.sort(Main.employees, Comparator.comparing(e -> e.firstName));
        System.out.println("Employees sorted by name.");
        for (Employee employee : Main.employees) {
            System.out.println(employee.getEmployeeId() + " " + employee.getFirstName() + " " + employee.getLast_name() + " " + employee.getAge() + " " + employee.getSalary());
        }
    }

    static void sortEmployeesBySalary() {
        Collections.sort(Main.employees, Comparator.comparingInt(e -> (int) e.salary));
        System.out.println("Employees sorted by salary.");
        for (Employee employee : Main.employees) {
            System.out.println(employee.getEmployeeId() + " " + employee.getFirstName() + " " + employee.getLast_name() + " " + employee.getAge() + " " + employee.getSalary());
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
