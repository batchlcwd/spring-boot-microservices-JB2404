package exception;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


//        String name = scanner.nextLine();
//        System.out.println("hi " + name);

        System.out.println("Enter you age: ");

        try (Scanner scanner = new Scanner(System.in);) {
            ///error prone code
            //error hone ki chances
            int age = scanner.nextInt();
            System.out.println("your age is " + age);

            if (age < 18) {

                AgeInvalidException ageInvalidException = new AgeInvalidException();
                throw ageInvalidException;

            }

            System.out.println("Voted ");
        } catch (AgeInvalidException ex) {
            System.out.println("you age is invalid !!");
            System.out.println(ex.getMessage());
        } catch (InputMismatchException ex) {
            //handling error code
            System.out.println("Input is wrong");
            System.out.println("Invalid Input");
        } catch (Exception ex) {
            System.out.println("Error !!");
            System.out.println(ex.getMessage());
        } finally {
            System.out.println("final statement");
        }
        System.out.println("Other important code");
        System.out.println("Other important code");
        System.out.println("Thank you for using program");

    }
}
