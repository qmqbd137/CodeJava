package chapter1.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * @author QMQ_bd137
 * @create_time is 2021-06-20 20:11
 */
public class getSubstreamAndConcatStream {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("D:\\文件\\Java资料\\workspace\\Core_Java_代码练习\\volumeⅡ\\gutenberg\\alice30.txt");
        var contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

        //使用Stream.limit()方法可以获取有限流
        Stream<Double> randoms = Stream.generate(Math::random).limit(100);
        //Comparator.reverseOrder() 添加该方法可以改变sorted()方法默认的顺序排序
        Stream<Double> SortRandoms = Stream.generate(Math::random).limit(100).sorted(Comparator.reverseOrder());
        //生成100个随机数的流并逐个分行打印
        System.out.println("开始打印随机生成的序列");
        randoms.forEach(System.out::println);
        //stream.sort()方法对流中元素进行排序
        System.out.println("开始打印顺序排序的序列");
        SortRandoms.forEach(System.out::println);
        //判断条件为 s -> s.length() < 10 判断将流的元素是否是小于10的短串
        //判定为短串则送入待take空间，直到遇到不满足条件的流元素，之后获取待获取区域的元素，丢弃剩下的元素。
        Stream<String> words = Stream.of(contents.split("\\PL+")).takeWhile(s -> s.length() < 10);
        //判断条件为s -> s.trim().length() == 0 该段首先将流的各个元素前后空格去除，之后再判断其字符串长度是否为0 若为零则意味着该字符串为空串
        //判定为空串则送入待drop空间，直到遇到不满足条件的流元素，之后丢弃待抛弃区域的元素，返回剩下的元素。
        Stream<String> words1 = Stream.of(contents.split("\\PL+")).dropWhile(s -> s.trim().length() == 0);
        System.out.println("开始打印获取满足条件的前置流");
        words.forEach(System.out::println);
        System.out.println("打印结束");
        System.out.println("开始打印去除满足条件的后置流");
        words1.forEach(System.out::println);
        System.out.println("打印结束");
    }
}
