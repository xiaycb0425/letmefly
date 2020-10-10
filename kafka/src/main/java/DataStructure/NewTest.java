package DataStructure;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiayc
 * @date 2020/9/28 15:09
 * @desc 假设有m个数组,每个数组含有数量不定个元素(0-n),从中各取出一个,求所有的组合情况
 */
public class NewTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("a");


        List<String> list1 = new ArrayList<>();
        list1.add("4");
        list1.add("5");
        list1.add("6");
        list1.add("b");

        List<String> list2 = new ArrayList<>();
        list2.add("7");
        list2.add("8");
        list2.add("9");
        list2.add("c");

        List<String> list3 = new ArrayList<>();
        list3.add("e");
        list3.add("f");
        list3.add("g");
        list3.add("k");
        list3.add("h");

        List<List<String>> realList = new ArrayList<>();
        realList.add(list);
        realList.add(list1);
        realList.add(list2);
        realList.add(list3);

        List<String> totalList = new ArrayList<>();

        test(realList, list, "", totalList);
        System.out.println(totalList.size());
        totalList.forEach(System.out::println);

    }

    public static void test(List<List<String>> listList, List<String> list, String str, List<String> total) {
        for (int i = 0; i < listList.size(); i++) {
            if (i == listList.indexOf(list)) {
                if (CollectionUtils.isEmpty(list)) {
                    if (i < listList.size() - 1) {
                        test(listList, listList.get(i + 1), str, total);
                    } else if (i == listList.size() - 1) {
                        total.add(str);
                    }
                } else {
                    for (String s : list) {
                        s = str + s;
                        if (i < listList.size() - 1) {
                            test(listList, listList.get(i + 1), s, total);
                        } else if (i == listList.size() - 1) {
                            total.add(s);
                        }
                    }
                }
            }
        }
    }
}
