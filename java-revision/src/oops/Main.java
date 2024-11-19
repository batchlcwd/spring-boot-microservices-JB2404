package oops;

public class Main {
    public static void main(String[] args) {
        System.out.println("going to create samosas");

        Samosa s1 = new Samosa();
        s1.showColor();
        Samosa s2 = new Samosa();
        s2.showColor();
        Samosa s3 = new Samosa("Panchmuki", "Red", "Mix", 100);
        s3.showDetail();

        System.out.println("------------");

        Child child = new Child();
        child.cycle = "HERO2";
        child.car = "TATA NEXON";
//        System.out.println(child.cycle);
//        System.out.println(child.car);
        child.showCycle();
        child.showCar();

        Child1 child1 = new Child1();
        child1.showCar();

        child.showVehicleDetail();
        System.out.println("++++++++++");
        child.showCar();


    }
}
