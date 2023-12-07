import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    protected static List<Employee> employees = new ArrayList<>();
    protected static List<Product> products = new ArrayList<>();


    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Enter the name of the CSV file for employees: ");
        String fileNameEmployees = inputScanner.nextLine();
        String csvFileEmployees = fileNameEmployees + ".csv";

        try {
            File file = new File(csvFileEmployees);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] columns = line.split(",");
                int employeeId = Integer.parseInt(columns[0]);
                String firstName = columns[1];
                String lastName = columns[2];
                int age = Integer.parseInt(columns[3]);
                int salary = Integer.parseInt(columns[4]);

                employees.add(new Employee(employeeId, firstName, lastName, age, salary));
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.print("Enter the name of the CSV file for products: ");
        String fileNameProducts = inputScanner.nextLine();
        String csvFileProducts = fileNameProducts + ".csv";

        try {
            File file = new File(csvFileProducts);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] columns = line.split(",");
                int productId = Integer.parseInt(columns[0]);
                String productName = columns[1];
                double price = Double.parseDouble(columns[2]);
                int quantity = Integer.parseInt(columns[3]);
                ProductType type = ProductType.valueOf(columns[4]);
                String color = "";
                if (columns.length > 5 && !columns[5].isEmpty()) {
                    color = columns[5];
                }
                String expirationDate = "";

                if (columns.length > 6 && !columns[6].isEmpty()) {
                    expirationDate = columns[6];
                }

                products.add(new Product(productId, productName, price, quantity, type, color, expirationDate));
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("Select a mode: \n 1. Employee \n 2. Customer");
        int userChoice = inputScanner.nextInt();
        if (userChoice == 1) {

            boolean successfulLogin = false;

            do {
                System.out.print("Enter your id: ");
                int id = inputScanner.nextInt();
                inputScanner.nextLine();

                System.out.print("Enter your name: ");
                String name = inputScanner.nextLine();

                if (CheckEmployeeInfo.checkEmployeeCredentials(id, name)) {
                    System.out.println("Successful login as an employee.");
                    successfulLogin = true;
                } else {
                    System.out.println("Login failed. Please try again later..");
                }
            } while (!successfulLogin);
            System.out.println("You are in employee mode.");
            Product.showMenu();
            // TODO
        } else if (userChoice == 2) {
            // TODO
            System.out.println("You are in customer mode.");
            Customer.showCustomerMenu();
        } else {
            System.out.println("Invalid selection. The program ends.");
        }

        inputScanner.close();
    }
}

