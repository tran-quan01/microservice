package quan.example.demo.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamToMapExample {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("quan", 35, "male"),
                new Person("huong", 30, "female"),
                new Person("trang", 30, "female"),
                new Person("phong", 5, "male"),
                new Person("tung", 32, "male"),
                new Person("bao", 30, "male")
        );

        // Create map with key is name, value is age
        Map<String, Integer> nameToAgeMap = people.stream()
                .collect(Collectors.toMap(p -> p.name, p-> p.age));
        System.out.println(nameToAgeMap);

        //Tuoi, danh sach nguoi
        Map<Integer, List<Person>> groupByAge = people.stream()
                .collect(Collectors.groupingBy(p -> p.age));
        System.out.println(groupByAge);

        Map<Integer, List<Person>> groupByAgeAsc = people.stream().collect(Collectors.groupingBy(
                Person::getAge,
                TreeMap::new,
                Collectors.toList()));
        System.out.println("group by age and order by age: "+ groupByAgeAsc);

        // add more extra each people
        List<String> flatMap = people.stream().flatMap(p -> Stream.of(p.name, p.name+"_extra"))
                .collect(Collectors.toList());
        System.out.println(flatMap);

        // List name have people more than 25
        System.out.println("People more than 25 age: ");
        List<String> ageMoreThan25 = people.stream().filter(p -> p.age > 25)
                .map(p -> p.name)
                .sorted().collect(Collectors.toList());
        System.out.println(ageMoreThan25);

        List<String> ageMoreThan30 = people.stream().filter(p -> p.age >= 30)
                .map(Person::getName)
                .sorted()
                .toList();
        System.out.println("People more than 30 age: "+ ageMoreThan30);

        // findFist is have age = 30
        Optional<Person> personFirst = people.stream().filter(p -> p.getAge() == 30).findFirst();
        personFirst.ifPresent(System.out::println);

        // order by age
        List<Person> orderByAge = people.stream().sorted(Comparator.comparing(Person::getAge).reversed()).toList();
        System.out.println("List order by age desc: "+ orderByAge);

        //count the number people have age = 35
        long count35 = people.stream().filter(p -> p.age == 35).count();
        System.out.println("The number of person have age equals 35: " + count35);

        //partitionBy
        Map<Boolean, List<Person>> result = people.stream().collect(Collectors.partitioningBy(p -> p.age > 18));
        System.out.println("Age > 18 :" + result.get(true));
        System.out.println("Age < 18 :" + result.get(false));

        //parallel Stream
        people.parallelStream().forEach(p -> System.out.println(p.toString() + " - " + Thread.currentThread().getName()));

        //count name start with t
        long countT = people.stream().filter(p -> p.name.startsWith("t") && p.age > 18).count();
        System.out.println("So ten bat dau with t : " + countT);

        //Map<tuổi, tên ghép lại>
        Map<Integer, String> ageToNames = people.stream()
                .collect(Collectors.groupingBy(
                   p -> p.age, Collectors.mapping(p -> p.name, Collectors.joining(", "))
                ));
        System.out.println(ageToNames);

        //double
        double avgAge = people.stream()
                .mapToInt(p-> p.age)
                .average()
                .orElse(0);
        System.out.println("Do tuoi trung binh : " + avgAge);

        List<List<String>> list = List.of( List.of("a", "b"), List.of("c", "d") );
        List<String> flat = list.stream().flatMap(Collection::stream).toList();
        System.out.println("One list: " + flat);


        Map<Integer, List<Person>> listGroup = people.stream()
                .collect(Collectors.groupingBy(Person::getAge))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .sorted(Comparator.comparing(Person::getName))
                                .toList()
                ));
        System.out.println("List person sorted: " + listGroup);

        Map<String, List<Person>> listGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().sorted(Comparator.comparing(Person::getAge))
                                .toList()
                ));
        System.out.println("List person sorted follow gender and age: " + listGender);

        Map<String, Double> avgAgeByGender = people.stream().collect(Collectors.groupingBy(Person::getGender, Collectors.averagingInt(Person::getAge)));
        System.out.println("Avg age by gender : " + avgAgeByGender);
    }
}
