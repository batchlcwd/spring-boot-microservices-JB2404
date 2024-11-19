public class Basics {
    public static void main(String[] args) {


        int x = 4;
        Integer x1 = 90;


        System.out.println(40);
        System.out.println(40.7);
        System.out.println("We are learning java basics");
        System.out.println("kamal bisht");
        System.out.print("First ");
        System.out.print("Second");
        System.out.println();
        System.out.println(x);
        x = 50;
        System.out.println(x);
        x = 24224;
        System.out.println(x);
        double marks = 90.5;
        char ch = 'A';
        boolean isActive = false;

        System.out.println("user is active " + isActive);
        if (isActive) {
            System.out.println("user is active");
        } else {
            System.out.println("User is not active");
        }

//         8 primitive type

//        Not Primitive  / User Defined Data types / Reference types / class

        Student st1 = new Student();
        st1.rollNumber = 12;
        st1.name = "Roshan";
        st1.age = 25;
        st1.address = "DELHI";

        Student st2 = new Student();
        st2.rollNumber = 14;
        st2.name = "Vivek";
        st2.address = "PUNE";
        st2.age = 24;

        System.out.println(st1.name);
        System.out.println(st2.name);
        System.out.println(st1.age);
        System.out.println(st2.age);


    }
}
