public class Product {
    private int product_id;
    private String name;
    private double price;
    private int quantity;
    private String type;
    private String color;
    private String expires_in;

    public Product(int product_id, String name, double price, int quantity, String type, String color, String expires_in) {
        this.product_id = product_id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.color = color;
        this.expires_in = expires_in;
    }
}