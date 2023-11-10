import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Product> products = new ArrayList<>();
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
                int employeeId= Integer.parseInt(columns[0]);
                String firstName = columns[1];
                String lastName = columns[2];
                int age = Integer.parseInt(columns[3]);
                int salary = Integer.parseInt(columns[4]);

                System.out.println("Id: " + employeeId + ", First name: " + firstName +
                        " , Last name: "+ lastName + " , Age: " + age + " , Salary: " + salary);
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
                int productId= Integer.parseInt(columns[0]);
                String productName = columns[1];
                double price= Double.parseDouble(columns[2]);
                int quantity= Integer.parseInt(columns[3]);
                String type = columns[4];
                String color = columns[5];
                String expirationDate = columns[6];

                System.out.println("Id: " + productId + ", name: " + productName +
                        " , Price: "+ price + " , Quantity: " + quantity + " , Type: " + type
                        + " , Color: " + color+ " , Expiration Date: " + expirationDate);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("Select a mode: \n 1. Employee \n 2. Customer" );
        int userChoice = inputScanner.nextInt();
        if (userChoice == 1) {
            // TODO
            System.out.println("You are in employee mode.");
        } else if (userChoice == 2) {
            // TODO
            System.out.println("You are in customer mode.");
        } else {
            System.out.println("Invalid selection. The program ends.");
        }

        inputScanner.close();
    }

}
