package com.kingstar.algorithm.sort.impl;

import com.kingstar.algorithm.sort.IArraySort;

import java.util.Arrays;

/**
 * @author xiayc
 * @date 2020/10/10 13:38
 * @desc 希尔排序
 */
public class ShellSort implements IArraySort {
    /**
     * <p>
     * 希尔排序
     * 依次缩小增量值,
     * </p>
     *
     * @param sourceArray
     * @return
     */
    @Override
    public int[] sort(int[] sourceArray) {
        int[] newArray = Arrays.copyOf(sourceArray, sourceArray.length);

        for (int gap = newArray.length / 2; gap > 0; gap /= 2) {

            for (int i = gap; i < newArray.length; i++) {
                int temp = newArray[i];
                int j = i - gap;

                while (j >= 0 && newArray[j] > temp) {
                    newArray[j + gap] = newArray[j];
                    j -= gap;
                }
                newArray[j + gap] = temp;
            }
        }

        return newArray;
    }

    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();
        int[] sourceArray = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
        // int[] sourceArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] sort = shellSort.sort(sourceArray);
        for (int i : sort) {
            System.out.println(i);
        }
    }
}
