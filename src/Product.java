public class Product {
    protected int productId;
    protected String productName;
    protected double price;
    protected int quantity;
    protected String type;
    protected String color;
    protected String expirationDate;

    public Product(int productId, String productName, double price, int quantity, String type, String color, String expirationDate) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.color = color;
        this.expirationDate = expirationDate;
    }
}