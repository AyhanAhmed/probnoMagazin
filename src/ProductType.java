public enum ProductType {
    food,
    drinks,
    sanitary,
    makeup,
    others;



    public ProductType getDescription() {
        for(ProductType type : ProductType.values()){
        return type;}
        return null;
    }
}
