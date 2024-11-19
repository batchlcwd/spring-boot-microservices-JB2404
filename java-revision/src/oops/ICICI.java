package oops;

public class ICICI implements Bank {
    @Override
    public int getRateOfInterest() {
        System.out.println("Rate of interset : " + 8);
        return 8;
    }

    @Override
    public int getMoney() {
        System.out.println("getting money from ICICI");
        return 0;
    }
}
