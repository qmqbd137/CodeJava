package chapter1.collecting;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author QMQ_bd137
 * @create_time is 2021-06-25 20:50
 */
public class CollectingResults{
    public static Stream<String> noVowels() throws IOException {
        var content = Files.readString(Paths.get("D:\\文件\\Java资料\\workspace\\Core_Java_代码练习\\volumeⅡ\\gutenberg\\alice30.txt"), StandardCharsets.UTF_8);
        List<String> listStream = List.of(content.split("\\PL+"));
        Stream<String> stringStream = listStream.stream();
        return stringStream.map( s -> s.replaceAll("aeiouAEIOU",""));
    }
    public static <T> void show (String label, Set<T> set){
        System.out.print(label + ":" + set.getClass().getName());
        System.out.println("["
        + set.stream().limit(10).map(Object::toString).collect(Collectors.joining(","))
        + "]");
    }
    public static void main(String[] args) throws IOException {
        Iterator<Integer> iterator = Stream.iterate(0, n -> n + 1).limit(10).iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
        System.out.println("Object array:" + numbers);

        try {
            var number = (Integer) numbers[0];
            System.out.println("number:" + number);
            System.out.println("The following statement throws an exception:");
            var number2 = (Integer[]) numbers;

        }
        catch (ClassCastException ex){
            System.out.println(ex);
        }

        Integer[] integers = Stream.iterate(0, n -> n + 1)
                                    .limit(10)
                                    .toArray(Integer[]::new);
        System.out.println("Integer array:" + integers);

        Set<String> noVowelSet = noVowels().collect(
                Collectors.toSet());
        show("noVowelSet" , noVowelSet);

        TreeSet<String> noVowelTreeSet = noVowels().collect(
                Collectors.toCollection(TreeSet::new));
        show("novowelTreeset" , noVowelTreeSet);

        String result = noVowels().limit(10).collect(Collectors.joining());
        System.out.println("Joining:" + result);
        result = noVowels().limit(10).collect(Collectors.joining(","));
        System.out.println("Joining with commas:" + result);
        IntSummaryStatistics summary = noVowels().collect(Collectors.summarizingInt(String::length));
        double averageWordLength = summary.getAverage();
        double maxWordLength = summary.getMax();
        System.out.println("Average word length : " + averageWordLength);
        System.out.println("Max word length : " + maxWordLength);
        System.out.println("forEach : ");
        noVowels().limit(10).forEach(System.out::println);
    }
}
