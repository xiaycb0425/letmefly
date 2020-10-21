package DataStructure;

import java.util.HashSet;

/**
 * @author xiayc
 * @date 2020/10/18 13:29
 */
public class HashSetTest {
    public static void main(String[] args) {
        HashSet<String> sets = new HashSet<>();
        sets.add("1bb");
        sets.add("bb");
        sets.add("2");
        sets.add("3");
        sets.add("4");
        sets.add("4aa");
       sets.forEach(System.out::println);
    }
}
