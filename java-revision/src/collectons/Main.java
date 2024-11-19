package collectons;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        //homo- same type of element
        List<String> names = new ArrayList<>();
        names.add("Arman");
        names.add("Shubham");
        names.add("Sandeep");
        names.add("Arman");

        for (String name : names) {
            System.out.println(name);
        }

//        names.forEach(System.out::println);
        System.out.println(names.get(0));
        System.out.println(names.size());

        //hetro- different types of element
//        List names1 = new ArrayList();
//        names1.add("Aramn");
//        names1.add("Subham");
//        names1.add("Sanip");
//        names1.add(23);
//        names1.add(34.435);
        System.out.println("---------");

        Set<String> nameSet = new HashSet<>();
        nameSet.addAll(names);
        nameSet.forEach(item -> System.out.println(item));

        System.out.println("------------------");
        Map<String, Integer> friendsAge = new HashMap<>();

        friendsAge.put("Kamal", 35);
        friendsAge.put("Pramod", 20);
        friendsAge.put("Irfan", 10);
        friendsAge.put("Kamal", 50);
        System.out.println(friendsAge.get("Kamal"));

        friendsAge.forEach((key, value) -> System.out.println(key + " -> " + value));

        Set<String> strings = friendsAge.keySet();
        Collection<Integer> values = friendsAge.values();

    }
}
