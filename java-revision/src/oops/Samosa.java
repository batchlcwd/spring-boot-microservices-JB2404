package oops;

public class Samosa {
    //properties
    private String shape;
    private String color;
    private String type;
    private int price;

    public Samosa() {
        shape = "Triangular";
        color = "Yellowish";
        type = "Aloo";
        price = 10;
        System.out.println("Default Samosa Created:");
    }

    public Samosa(String shape, String color, String type, int price) {
        this.shape = shape;
        this.color = color;
        this.type = type;
        this.price = price;
        System.out.println("Samosa created:");
    }

    //behaviours
    public void showColor() {
        System.out.println("Samosa Color is : " + color);
    }

    public void showPrice() {
        System.out.println("Samosa price is : " + price);
    }

    public void showDetail() {
        System.out.println("++++++++++++");
        System.out.println("Shape : " + shape);
        System.out.println("Color : " + color);
        System.out.println("Type : " + type);
        System.out.println("Price : " + price);
        System.out.println("_________________");
    }


}
