package oops;

public class PNB implements Bank {

    @Override
    public int getRateOfInterest() {
        System.out.println("Rate of interest : " + 10);
        return 10;
    }

    @Override
    public int getMoney() {
        System.out.println("getting money from PNB Bank");
        return 0;
    }
}
