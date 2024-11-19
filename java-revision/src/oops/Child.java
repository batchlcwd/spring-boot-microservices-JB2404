package oops;

public class Child extends Parent {

    String cycle="HERO";
    String car="BMW";

    public void showCycle(){
        System.out.println(cycle);
    }

    public void showVehicleDetail(){
        System.out.println("Printing all vehicle detail");
        System.out.println(cycle);
        System.out.println(this.car);
        System.out.println(super.car);
    }

    @Override
    public void showCar()
    {
        System.out.println("showing our own new car");
        System.out.println(this.car);
    }

}
