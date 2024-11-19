package oops;

import java.beans.Visibility;

public class BankMain {
    public static void main(String[] args) {
        System.out.println("starting bank app");

        Bank bank = new PNB();
        Bank bank1 = new ICICI();
        bank.getRateOfInterest();
        bank1.getRateOfInterest();
        Vikash vikash = new Vikash();
        vikash.goGetMoneyFromBank(new ICICI());


    }
}
