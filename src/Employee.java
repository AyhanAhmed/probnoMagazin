public class Employee {
    int employeeId;
    String firstName;
    private String last_name;
    private int age;
    private double salary;

    public Employee(int employeeId, String firstName, String last_name, int age, double salary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.last_name = last_name;
        this.age = age;
        this.salary = salary;
    }

    public int getEmployee_id() {
        return employeeId;
    }

    public String getFirst_name() {
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
}
