package machine;

public enum Product{
    COKE("Coke",10000),PEPSI("Pepsi",10000),SODA("Soda",20000);
    private String name;
    private int price;
    Product(String name,int price){
        this.name=name;
        this.price=price;
    }
    public String getName(){
        return this.name;
    }
    public int getPrice(){
        return this.price;
    }
}
