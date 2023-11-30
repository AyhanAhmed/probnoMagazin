import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCart {
    private static List<Product> productsInCart = new ArrayList<>();

    public static List<Product> getProductsInCart() {
        return productsInCart;
    }

    public static void addToShoppingCart() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product ID to add to the shopping cart: ");
        int productId = scanner.nextInt();

        Product productToAdd = getProductById(productId);

        System.out.print("Enter product quantity: ");
        int quantity = scanner.nextInt();
        if (productToAdd != null && productToAdd.getQuantity() >= quantity) {
            productToAdd.setQuantity(productToAdd.getQuantity() - quantity);

            ShoppingCart.addProduct(productToAdd, quantity);

            System.out.println("Product added to the shopping cart successfully.");
        } else {
            System.out.println("Error: Product not available or insufficient quantity.");
        }
    }

    private static Product getProductById(int productId) {
        for (Product product : Main.products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }
    public static void addProduct(Product product, int quantity) {
        Product productInCart = new Product(product.productId, product.productName, product.price, quantity, product.getType(),product.color, product.expirationDate);
        productsInCart.add(productInCart);
    }

    public static double calculateTotalPrice() {
        double totalPrice = 0.0;

        for (Product product : productsInCart) {
            totalPrice += product.price * product.quantity;
        }
        System.out.println("The total price of products in the cart is " + totalPrice);

        return totalPrice;
    }
}
