package com.kingstar.algorithm.sort.impl;

import com.kingstar.algorithm.sort.IArraySort;

import java.util.Arrays;

/**
 * @author xiayc
 * @date 2020/10/10 9:35
 * @desc 冒泡排序
 */
public class BubbleSort implements IArraySort {

    /*1. 算法步骤
    比较相邻的元素。如果第一个比第二个大，就交换他们两个。

    对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。

    针对所有的元素重复以上的步骤，除了最后一个。

    持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。*/

    @Override
    public int[] sort(int[] sourceArray) {
        int[] newArray = Arrays.copyOf(sourceArray, sourceArray.length);
        for (int j = 1; j < newArray.length; j++) {
            boolean flag = false;
            for (int i = 0; i < newArray.length - j; i++) {
                if (newArray[i] > newArray[i + 1]) {
                    int temp = newArray[i + 1];
                    newArray[i + 1] = newArray[i];
                    newArray[i] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }

        return newArray;
    }

    public static void main(String[] args) throws Exception {
        BubbleSort bubbleSort = new BubbleSort();
        // int[] sourceArray = {6, 4, 7, 3, 8, 14, 67, 26, 54};
        int[] sourceArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] sort = bubbleSort.sort(sourceArray);
        for (int i : sort) {
            System.out.println(i);
        }
    }
}
