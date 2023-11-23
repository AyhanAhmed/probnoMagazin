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
                String type = columns[4];
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
                    "15. Sort products by name \n" +
                    "16. Sort products by price \n" +
                    "17. Exit");

            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    printAllProducts();
                    break;
                case 2:
                    printProductsSortedByName();
                    break;
                case 3:
                    printProductsSortedByPrice();
                    break;
                case 4:
                    printProductsSortedByExpirationDate();
                    break;
                case 5:
                    printSpecificProduct();
                    break;
                case 6:
                    printProductsWithPriceGreaterThanOrEqual();
                    break;
                case 7:
                    printProductsWithPriceLessThan();
                    break;
                case 8:
                    printProductsWithQuantityGreaterThanOrEqual();
                    break;
                case 9:
                    printProductsWithQuantityLessThan();
                    break;
                case 10:
                    addProduct();
                    break;
                case 11:
                    changeProductPrice();
                    break;
                case 12:
                    changeProductQuantity();
                    break;
                case 13:
                    changeProductName();
                    break;
                case 14:
                    deleteProduct();
                    break;
                case 15:
                    sortProductsByName();
                    break;
                case 16:
                    sortProductsByPrice();
                    break;
                case 17:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void printAllProducts() {
        for (Product product : products) {
            printProductDetails(product);
        }
    }

    private static void printProductsSortedByName() {
        List<Product> sortedProducts = new ArrayList<>(products);
        Collections.sort(sortedProducts, Comparator.comparing(p -> p.productName));
        for (Product product : sortedProducts) {
            printProductDetails(product);
        }
    }

    private static void printProductsSortedByPrice() {
        List<Product> sortedProducts = new ArrayList<>(products);
        Collections.sort(sortedProducts, Comparator.comparingDouble(p -> p.price));
        for (Product product : sortedProducts) {
            printProductDetails(product);
        }
    }

    private static void printProductsSortedByExpirationDate() {
        List<Product> sortedProducts = new ArrayList<>(products);
        Collections.sort(sortedProducts, Comparator.comparing(p -> p.expirationDate));
        for (Product product : sortedProducts) {
            printProductDetails(product);
        }
    }

    private static void printSpecificProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product ID or name: ");
        String query = scanner.next();

        for (Product product : products) {
            if (String.valueOf(product.productId).equals(query) || product.productName.equals(query)) {
                printProductDetails(product);
                return;
            }
        }

        System.out.println("Product not found.");
    }

    private static void printProductDetails(Product product) {
        System.out.println("Product ID: " + product.productId);
        System.out.println("Product Name: " + product.productName);
        System.out.println("Price: " + product.price);
        System.out.println("Expiration Date: " + product.expirationDate);
        System.out.println("--------------------");
    }
    private static void printProductsWithPriceGreaterThanOrEqual() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum price: ");
        double minPrice = scanner.nextDouble();

        for (Product product : products) {
            if (product.price >= minPrice) {
                printProductDetails(product);
            }
        }
    }

    private static void printProductsWithPriceLessThan() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter maximum price: ");
        double maxPrice = scanner.nextDouble();

        for (Product product : products) {
            if (product.price < maxPrice) {
                printProductDetails(product);
            }
        }
    }

    private static void printProductsWithQuantityGreaterThanOrEqual() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum quantity: ");
        int minQuantity = scanner.nextInt();

        for (Product product : products) {
            if (product.quantity >= minQuantity) {
                printProductDetails(product);
            }
        }
    }

    private static void printProductsWithQuantityLessThan() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter maximum quantity: ");
        int maxQuantity = scanner.nextInt();

        for (Product product : products) {
            if (product.quantity < maxQuantity) {
                printProductDetails(product);
            }
        }
    }

    private static void addProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name: ");
        String name = scanner.next();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter product quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter product type: ");
        String type = scanner.nextLine();
        System.out.print("Enter product color: ");
        String color = scanner.nextLine();
        System.out.print("Enter product expiration date: ");
        String expirationDate = scanner.nextLine();

        int newProductId = products.size() + 1;
        Product newProduct = new Product(newProductId, name, price, quantity, type, color, expirationDate);
        products.add(newProduct);

        System.out.println("Product added successfully.");
    }

    private static void changeProductPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product ID to change price: ");
        int productId = scanner.nextInt();
        System.out.print("Enter new price: ");
        double newPrice = scanner.nextDouble();

        for (Product product : products) {
            if (product.productId == productId) {
                product.price = newPrice;
                System.out.println("Price changed successfully.");
                return;
            }
        }

        System.out.println("Product not found.");
    }

    private static void changeProductQuantity() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product ID to change quantity: ");
        int productId = scanner.nextInt();
        System.out.print("Enter new quantity: ");
        int newQuantity = scanner.nextInt();

        for (Product product : products) {
            if (product.productId == productId) {
                product.quantity = newQuantity;
                System.out.println("Quantity changed successfully.");
                return;
            }
        }

        System.out.println("Product not found.");
    }

    private static void changeProductName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product ID to change name: ");
        int productId = scanner.nextInt();
        System.out.print("Enter new name: ");
        String newName = scanner.next();

        for (Product product : products) {
            if (product.productId == productId) {
                product.productName = newName;
                System.out.println("Name changed successfully.");
                return;
            }
        }

        System.out.println("Product not found.");
    }

    private static void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product ID to delete: ");
        int productId = scanner.nextInt();

        for (Product product : products) {
            if (product.productId == productId) {
                products.remove(product);
                System.out.println("Product deleted successfully.");
                return;
            }
        }

        System.out.println("Product not found.");
    }

    private static void sortProductsByName() {
        Collections.sort(products, Comparator.comparing(p -> p.productName));
        System.out.println("Products sorted by name.");
    }

    private static void sortProductsByPrice() {
        Collections.sort(products, Comparator.comparingDouble(p -> p.price));
        System.out.println("Products sorted by price.");
    }
}

