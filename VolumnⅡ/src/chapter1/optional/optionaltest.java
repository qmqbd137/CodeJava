package chapter1.optional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * @author QMQ_bd137
 * @create_time is 2021-05-23 17:08
 * Optional<T>的概念和使用法则
 * 概念：
 * Optional本身单词的含义是“可选的”
 * 而Optional<T>是一个包装器对象，它要么包装了T类型的对象，要么没有包装任何对象
 */
public class optionaltest {
    public static void main(String[] args) throws IOException {
        var content = Files.readString(Paths.get("D:\\Program Files\\eclipse\\workspace\\java核心卷代码\\volumeⅡ\\gutenberg\\alice30.txt"));
//        System.out.println(content.toCharArray());
        List<String> wordlists = List.of(content.split("\\PL"));//\\PL 以非字符分割
//        System.out.println(wordlists);
        Optional<String> optionalwordlist = wordlists.stream().filter(w -> w.contains("fred")).findFirst();
        System.out.println(optionalwordlist.orElse("No word") + " contains fred");

        Optional<String> optionalString = Optional.empty();
        //当值不存在时，产生orElse里面的值来替代
        String result = optionalString.orElse("N/A");
        System.out.println("optionalString is " + result);
        //安全使用Optional.get()的用法，防止Optional为null时抛出NoSuchElementsException异常
        result = optionalString.orElseGet(() -> Locale.getDefault().getDisplayName());
        System.out.println("result: " + result);

        try {
            result = optionalString.orElseThrow(IllegalStateException::new);
            System.out.println("result: " + result);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        optionalwordlist = wordlists.stream()
                .filter(s -> s.contains("red"))
                .findFirst();
        System.out.println("optionalwordlist have value?" + optionalwordlist.isPresent() + optionalwordlist);

        var results = new HashSet<String>();
        optionalwordlist.ifPresent(results::add);
        System.out.println("results is" + results.toString());
        Optional<Boolean> added = optionalwordlist.map(results::add);
        System.out.println(added);
        System.out.println(inverse(4.0).flatMap(optionaltest::squareRoot));
        System.out.println(inverse(-1.0).flatMap(optionaltest::squareRoot));
        System.out.println(inverse(0.0).flatMap(optionaltest::squareRoot));
        Optional<Double> result2 = Optional.of(2.0)
                .flatMap(optionaltest::inverse).flatMap(optionaltest::squareRoot);
        System.out.println(result2);
    }

    public static Optional<Double> inverse(Double x) {
        return x == 0 ? Optional.empty() : Optional.of(1 / x);
    }

    public static Optional<Double> squareRoot(Double x) {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }
}
