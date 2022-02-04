package pack;

import javax.jws.soap.SOAPBinding;
import java.util.*;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main{
    public static void main(String[] args) {
        int[] ar = new int[]{1,2,3,4,5};
        IntStream stream = Arrays.stream(ar);
        stream.filter(in -> in % 2 == 0).filter(value -> value <=6).forEach(value -> System.out.println(value));
        //stream.forEach(value -> System.out.println(value));
        //
        /*IntStream.of(1,2,3,4,5);*/
        System.out.println();
        int n = IntStream.of(ar).max().getAsInt();
        System.out.println(n);
        //
        List<Integer> list = Arrays.asList(10,2,3,3,5);
        list.stream().forEach(System.out::println);
        //
        "qwerty".chars().forEach(value -> System.out.println((char)value));
        //
        list.stream()
                //.distinct() // distinct - убирает повторы.
                //.limit(3)
                //skip(3)
                //.sorted()
                //.sorted(Comparator.reverseOrder())
                //.unordered() // !!!
                .forEach(System.out::println);
                System.out.println();
                //
                // User
        User[] array =
        Stream.of(
                new User("Vova", 18, true),
                new User("Mova", 19, false),
                new User("Sova", 30, false)
        )
                .filter(user -> user.getAge()>=18)
                //.forEach(System.out::println);
                .toArray(new IntFunction<User[]>() {
                    @Override
                    public User[] apply(int value) {
                        return new User[value];
                    }
                });
        System.out.println(Arrays.toString((array)));
        System.out.println(array.getClass());
        //
        System.out.println();
        List<User> users = Arrays.asList(
                new User("Vova", 18, true),
                new User("Mova01", 19, false),
                new User("Sova", 30, false)
        );
        User[] userArr =
                users.toArray(new User[0]);
        System.out.println(Arrays.toString(userArr));
        //
        ArrayList<User> collect = (ArrayList<User>) users.stream()
                .collect(Collectors.toList());
        //
        HashSet<User> hashSet = (HashSet<User>) users.stream()
                .collect(Collectors.toSet());
        //
        HashMap<String, User> hashMap =
                (HashMap<String, User>)users.stream()
                .collect(Collectors.toMap(
                User::getName, user -> user
        ));
        System.out.println(hashMap);
        //
        users.stream()
                .peek(user -> user.setName((user.getName()+"*")))
                .collect(Collectors.toList());
        System.out.println(collect);
        //
        System.out.println();
        List<String> collect2 = users.stream()
                .map(User::getName)
                .filter(s -> s.matches("^[A-Za-z*]*"))
                .collect(Collectors.toList());
        System.out.println(collect2);
        //
        System.out.println();
        OptionalInt min = users.stream()
                .mapToInt(User::getAge)
                .min();
        if (min.isPresent())
            System.out.println(min.getAsInt());
        //
        int asInt = users.stream()
                .mapToInt(User::getAge).min().getAsInt();
        //
        Optional<User> first =  users.stream()
                //.filter(user -> !user.isActivated())
                .filter(User::isActivated)
                .filter(((Predicate<User>) User::isActivated).negate())
                .findFirst();
        if (first.isPresent())
            System.out.println(first);
        //
        System.out.println();
        String s3 = users.stream()
                .map(User::getName)
                .reduce((s, s2) -> s + "\n" +s2)
                .get();
        System.out.println(s3);
        System.out.println();
        //
        list.stream()
                .map(integer -> integer*10)
                .forEach(System.out::println);
        System.out.println();
        //
        int sum = list.stream()
                .mapToInt(value -> value)
                .sum();
        System.out.println(sum);
        System.out.println();
        //
        double av = list.stream()
                .mapToInt(value -> value)
                .average().getAsDouble();
        System.out.println(av);
        System.out.println();
        //
        Integer integer1 = list.stream()
                .reduce((integer, integer2) -> integer * integer2)
                .get();
        System.out.println(integer1);
        //
        Stream.of(
        "Hello Java. StreamAPI".split("\\.")
        )
                .map(s -> s.trim()) // trim - уберает пробелы.
                .forEach(System.out::println);
    }
}

