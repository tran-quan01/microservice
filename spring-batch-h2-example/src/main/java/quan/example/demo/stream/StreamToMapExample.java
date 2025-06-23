package quan.example.demo.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamToMapExample {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("quan", 35),
                new Person("huong", 30),
                new Person("trang", 30),
                new Person("phong", 5),
                new Person("tung", 35)
        );

        // Create map with key is name, value is age
        Map<String, Integer> nameToAgeMap = people.stream()
                .collect(Collectors.toMap(p -> p.name, p-> p.age));
        System.out.println(nameToAgeMap);

        //Tuoi, danh sach nguoi
        Map<Integer, List<Person>> groupByAge = people.stream()
                .collect(Collectors.groupingBy(p -> p.age));
        System.out.println(groupByAge);

        // add more extra each people
        List<String> flatMap = people.stream().flatMap(p -> Stream.of(p.name, p.name+"_extra"))
                .collect(Collectors.toList());
        System.out.println(flatMap);

        // List name have people more than 25
        List<String> ageMoreThan25 = people.stream().filter(p -> p.age > 25)
                .map(p -> p.name)
                .sorted().collect(Collectors.toList());
        System.out.println(ageMoreThan25);

        //partitionBy
        Map<Boolean, List<Person>> result = people.stream().collect(Collectors.partitioningBy(p -> p.age > 18));
        System.out.println("> 18 :" + result.get(true));
        System.out.println("< 18 :" + result.get(false));

        //parallel Stream
        people.parallelStream().forEach(
                p -> {
                    System.out.println(p.toString() + " - " + Thread.currentThread().getName());
                }
        );

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
    }
}
