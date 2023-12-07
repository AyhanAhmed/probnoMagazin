import java.util.Scanner;

public class Customer {
    protected static void showCustomerMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCustomer Menu:\n" +
                    "1. Print available products (quantity at least 1)\n" +
                    "2. Search products by category\n" +
                    "3. Search products by name\n" +
                    "4. Add to shopping cart\n" +
                    "5. Calculate total price of the shopping cart\n" +
                    "6. Save and exit \n" +
                    "7. Exit");

            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Product.printAvailableProducts();
                    break;
                case 2:
                    Product.searchProductByCategory();
                    break;
                case 3:
                    Product.searchProductByName();
                    break;
                case 4:
                    ShoppingCart.addToShoppingCart();
                    break;
                case 5:
                    ShoppingCart.calculateTotalPrice();
                    break;
                case 6:
                    Product.saveProductsToFile();
                    System.out.println("Changes saved to file. Exiting the customer menu. Goodbye!");
                    System.exit(0);
                case 7:
                    System.out.println("Exiting the customer menu. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

}
