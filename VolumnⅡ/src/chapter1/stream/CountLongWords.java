package chapter1.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author QMQ_bd137
 * @create_time is 2021-06-18 16:20
 */
public class CountLongWords {
    public static void main(String[] args) throws IOException {
        var content = new String(Files.readAllBytes(
                Paths.get("D:\\Program Files\\eclipse\\workspace\\java核心卷代码\\volumeⅡ\\gutenberg\\alice30.txt")),
                StandardCharsets.UTF_8);
        List<String> words = List.of(content.split("\\PL+"));

        long count = 0;
        for (String w : words) {
            if (w.length() > 12) {
                count++;
            }
        }
        System.out.println(count);

        count = words.stream().filter(w -> w.length() > 12).count();
        System.out.println(count);

        count = words.parallelStream().filter(w -> w.length() > 12).count();
        System.out.println(count);
    }
}
