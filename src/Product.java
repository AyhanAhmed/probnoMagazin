import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Product {
    protected int productId;
    protected String productName;
    protected double price;
    protected int quantity;
    protected ProductType type;
    protected String color;
    protected String expirationDate;

    public ProductType getType() {
        return type;
    }

    public Product(int productId, String productName, double price, int quantity, ProductType type, String color, String expirationDate) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.color = color;
        this.expirationDate = expirationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    static void printAllProducts() {
        for (Product product : Main.products) {
            printProductDetails(product);
        }
    }

    static void printProductsSortedByName() {
        List<Product> sortedProducts = new ArrayList<>(Main.products);
        Collections.sort(sortedProducts, Comparator.comparing(p -> p.productName));
        for (Product product : sortedProducts) {
            printProductDetails(product);
        }
    }

    static void printProductsSortedByPrice() {
        List<Product> sortedProducts = new ArrayList<>(Main.products);
        Collections.sort(sortedProducts, Comparator.comparingDouble(p -> p.price));
        for (Product product : sortedProducts) {
            printProductDetails(product);
        }
    }

    static void printProductsSortedByExpirationDate() {
        List<Product> sortedProducts = new ArrayList<>(Main.products);
        Collections.sort(sortedProducts, Comparator.comparing(p -> p.expirationDate));
        for (Product product : sortedProducts) {
            printProductDetails(product);
        }
    }

    static void printSpecificProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product ID or name: ");
        String query = scanner.next();

        for (Product product : Main.products) {
            if (String.valueOf(product.productId).equals(query) || product.productName.equals(query)) {
                printProductDetails(product);
                return;
            }
        }

        System.out.println("Product not found.");
    }

    private static void printProductDetails(Product product) {
        System.out.println();
        System.out.print("Product ID: " + product.productId);
        System.out.print("   Product Name: " + product.productName);
        System.out.print("   Price: " + product.price);
        System.out.print("   Quantity: " + product.quantity);
        System.out.print("   Type: " + product.type);
        System.out.print("   Color: " + product.color);
        System.out.print("   Expiration Date: " + product.expirationDate);
    }

    static void printProductsWithPriceGreaterThanOrEqual() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum price: ");
        double minPrice = scanner.nextDouble();

        for (Product product : Main.products) {
            if (product.price >= minPrice) {
                printProductDetails(product);
            }
        }
    }

    static void printProductsWithPriceLessThan() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter maximum price: ");
        double maxPrice = scanner.nextDouble();

        for (Product product : Main.products) {
            if (product.price < maxPrice) {
                printProductDetails(product);
            }
        }
    }

    static void printProductsWithQuantityGreaterThanOrEqual() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum quantity: ");
        int minQuantity = scanner.nextInt();

        for (Product product : Main.products) {
            if (product.quantity >= minQuantity) {
                printProductDetails(product);
            }
        }
    }

    static void printProductsWithQuantityLessThan() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter maximum quantity: ");
        int maxQuantity = scanner.nextInt();

        for (Product product : Main.products) {
            if (product.quantity < maxQuantity) {
                printProductDetails(product);
            }
        }
    }

    static void addProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name: ");
        String name = scanner.next();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter product quantity: ");
        int quantity = scanner.nextInt();
        ProductType type = null;
        while (type == null) {
            System.out.print("Enter product type (food, drinks, sanitary, makeup, others): ");
            String typeInput = scanner.next();
            try {
                type = ProductType.valueOf(typeInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid product type. Please enter a valid product type.");
            }
        }
        System.out.print("Enter product color: ");
        String color = scanner.nextLine();
        System.out.print("Enter product expiration date: ");
        String expirationDate = scanner.nextLine();

        int newProductId = Main.products.size() + 1;
        Product newProduct = new Product(newProductId, name, price, quantity, type, color, expirationDate);
        Main.products.add(newProduct);

        System.out.println("Product added successfully.");
    }

    static void changeProductPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product ID to change price: ");
        int productId = scanner.nextInt();
        System.out.print("Enter new price: ");
        double newPrice = scanner.nextDouble();

        for (Product product : Main.products) {
            if (product.productId == productId) {
                product.price = newPrice;
                System.out.println("Price changed successfully.");
                return;
            }
        }

        System.out.println("Product not found.");
    }

    static void changeProductQuantity() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product ID to change quantity: ");
        int productId = scanner.nextInt();
        System.out.print("Enter new quantity: ");
        int newQuantity = scanner.nextInt();

        for (Product product : Main.products) {
            if (product.productId == productId) {
                product.quantity = newQuantity;
                System.out.println("Quantity changed successfully.");
                return;
            }
        }

        System.out.println("Product not found.");
    }

    static void changeProductName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product ID to change name: ");
        int productId = scanner.nextInt();
        System.out.print("Enter new name: ");
        String newName = scanner.next();

        for (Product product : Main.products) {
            if (product.productId == productId) {
                product.productName = newName;
                System.out.println("Name changed successfully.");
                return;
            }
        }

        System.out.println("Product not found.");
    }

    static void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product ID to delete: ");
        int productId = scanner.nextInt();

        for (Product product : Main.products) {
            if (product.productId == productId) {
                Main.products.remove(product);
                System.out.println("Product deleted successfully.");
                return;
            }
        }

        System.out.println("Product not found.");
    }

    static void saveProductsToFile() {
        try {
            LocalDateTime timestamp = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String filename = timestamp.format(formatter) + ".csv";

            FileWriter writer = new FileWriter(filename);

            writer.write("id,name,price,quantity,type,color,expires_in\n");

            for (Product product : Main.products) {
                writer.write(String.format("%d,%s,%.2f,%d,%s,%s,%s\n",
                        product.productId, product.productName, product.price, product.quantity, product.type, product.color, product.expirationDate));
            }

            writer.close();

            System.out.println("Products saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving products to file: " + e.getMessage());
        }
    }

    protected static void printAvailableProducts() {
        System.out.println("Available Products:");
        for (Product product : Main.products) {
            if (product.quantity >= 1) {
                printProductDetails(product);
            }
        }
    }

    protected static void searchProductByCategory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product category to search: ");
        String categoryInput = scanner.next();

        System.out.println("Search Results:");
        for (Product product : Main.products) {
            if (product.quantity >= 1 && product.getType().toString().equals(categoryInput)) {
                printProductDetails(product);
            }
        }
    }

    protected static void searchProductByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name to search: ");
        String nameInput = scanner.next().toLowerCase();

        System.out.println("Search Results:");
        for (Product product : Main.products) {
            if (product.quantity >= 1 && (product.productName.toLowerCase().contains(nameInput) || product.productName.toLowerCase().equals(nameInput))) {
                printProductDetails(product);
            }
        }
    }

    protected static void addToShoppingCart() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product ID to add to the shopping cart: ");
        int productId = scanner.nextInt();

        Product productToAdd = null;

        for (Product product : Main.products) {
            if (product.productId == productId && product.quantity >= 1) {
                productToAdd = product;
                break;
            }
        }

        if (productToAdd == null) {
            System.out.println("Product not found or not available in sufficient quantity.");
            return;
        }

        System.out.print("Enter quantity to add to the shopping cart: ");
        int quantityToAdd = scanner.nextInt();

        ShoppingCart.addProduct(productToAdd, quantityToAdd);
        System.out.println("Product added to the shopping cart.");
    }

    protected static void calculateTotalPrice() {
        double totalPrice = ShoppingCart.calculateTotalPrice();
        System.out.println("Total Price of the Shopping Cart: $" + totalPrice);
    }

    protected static void showMenu() {
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

