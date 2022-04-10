package chapter1.stream;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author QMQ_bd137
 * @create_time is 2021-06-19 16:59
 */
public class CreateStreams {
    public static <T> void show(String title, Stream<T> stream) {
        final int SIZE = 10;
        List<T> firstElements = stream
                .limit(SIZE + 1)
                .collect(Collectors.toList());
        System.out.println(title + ":");
        for (int i = 0; i < firstElements.size(); i++) {
            if (i > 0) {
                System.out.println(",");
            }
            if (i < SIZE) {
                System.out.println(firstElements.get(i));
            } else {
                System.out.println("...");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("D:\\文件\\Java资料\\workspace\\Core_Java_代码练习\\volumeⅡ\\gutenberg\\alice30.txt");
        var content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        String[] wordsArrays = content.split("\\PL+");

        Stream<String> wordsStreams = Stream.of(wordsArrays);
        show("Stream.of",wordsStreams);

        Stream<String> song = Stream.of(
                "gently",
                "down" ,
                "the" ,
                "stream");
        show("song",song);

        Stream<String> silence = Stream.empty();
        show("silence", silence);

        Stream<String> echo = Stream.generate(() -> "Echo");
        show("Echo", echo);

        Stream<Double> randoms = Stream.generate(Math::random);
        show("Random", randoms);

        BigInteger limit = new BigInteger("10");
        Stream<BigInteger> bigIntegerStream = Stream.iterate(
                                                BigInteger.ONE,
                                                n -> n.compareTo(limit)<0,
                                                n-> n.add(BigInteger.ONE));
        show("iterate",bigIntegerStream);

        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(content);
        show("wordsAnotherWay", wordsAnotherWay);

        try (Stream<String> lines = Files.lines(path,StandardCharsets.UTF_8)){
            show("lines", lines);
        }

        Iterable<Path> iterable = FileSystems.getDefault().getRootDirectories();
        Stream<Path> rootDirectories = StreamSupport.stream(iterable.spliterator(),false);
        show("rootDirectories", rootDirectories);

        Iterator<Path> iterator = Paths.get("usr/share/dict/words").iterator();
        Stream<Path> pathComponents = StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iterator, Spliterator.ORDERED), false
        );
        show("pathComponents",pathComponents);
    }
}
