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
            showMenu();
            // TODO
        } else if (userChoice == 2) {
            // TODO
            System.out.println("You are in customer mode.");
        } else {
            System.out.println("Invalid selection. The program ends.");
        }

        inputScanner.close();
    }

    private static void showMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:  \n" +
                    "1. Print all products \n" +
                    "2. Print products sorted by name \n" +
                    "3. Print products sorted by price \n" +
                    "4. Print products sorted by expiration date \n" +
                    "5. Print a specific product \n" +
                    "6. Print products with price greater than or equal to \n" +
                    "7. Print products with price less than \n" +
                    "8. Print products with quantity greater than or equal to \n" +
                    "9. Print products with quantity less than \n" +
                    "10. Add a new product \n" +
                    "11. Change product price (by ID) \n" +
                    "12. Change product quantity (by ID) \n" +
                    "13. Change product name (by ID) \n" +
                    "14. Delete a product (by ID) \n" +
                    "15. Sort employees by name \n" +
                    "16. Sort employees by salary \n" +
                    "17. Save and exit \n" +
                    "18. Exit without saving");

            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Product.printAllProducts();
                    break;
                case 2:
                    Product.printProductsSortedByName();
                    break;
                case 3:
                    Product.printProductsSortedByPrice();
                    break;
                case 4:
                    Product.printProductsSortedByExpirationDate();
                    break;
                case 5:
                    Product.printSpecificProduct();
                    break;
                case 6:
                    Product.printProductsWithPriceGreaterThanOrEqual();
                    break;
                case 7:
                    Product.printProductsWithPriceLessThan();
                    break;
                case 8:
                    Product.printProductsWithQuantityGreaterThanOrEqual();
                    break;
                case 9:
                    Product.printProductsWithQuantityLessThan();
                    break;
                case 10:
                    Product.addProduct();
                    break;
                case 11:
                    Product.changeProductPrice();
                    break;
                case 12:
                    Product.changeProductQuantity();
                    break;
                case 13:
                    Product.changeProductName();
                    break;
                case 14:
                    Product.deleteProduct();
                    break;
                case 15:
                    Employee.sortEmployeesByName();
                    break;
                case 16:
                    Employee.sortEmployeesBySalary();
                    break;
                case 17:
                    Product.saveProductsToFile();
                    System.out.println("Changes saved to file. Exiting the system. Goodbye!");
                    System.exit(0);
                case 18:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }


}

