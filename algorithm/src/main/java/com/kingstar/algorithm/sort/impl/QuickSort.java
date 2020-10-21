package com.kingstar.algorithm.sort.impl;

import com.kingstar.algorithm.sort.IArraySort;

/**
 * @author xiayc
 * @date 2020/10/13 15:41
 * @desc 快速排序
 */
public class QuickSort implements IArraySort {
    /**
     * 算法思路
     * 从数列中挑出一个元素，称为 "基准"（pivot）;
     * <p>
     * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
     * 在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
     * <p>
     * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
     *
     * @param sourceArray
     * @return
     */
    @Override
    public int[] sort(int[] sourceArray) {
       // int[] newArr = Arrays.copyOf(sourceArray, sourceArray.length);
        // 挑出基准元素
        int pivot = sourceArray[0];
        int m = 0;
        int k = 0;
        for (int i = 1; i < sourceArray.length; i++) {
            if (sourceArray[i] < pivot) {
                m++;
            } else if (sourceArray[i] > pivot) {
                k++;
            }
        }

        int[] arr1 = new int[m];
        int[] arr2 = new int[k];

        int a = 0;
        int b = 0;
        for (int i = 1; i < sourceArray.length; i++) {
            if (sourceArray[i] < pivot) {
                arr1[a++] = sourceArray[i];

            } else if (sourceArray[i] > pivot) {
                arr2[b++] = sourceArray[i];
            }
        }

        if (arr1.length > 1) {
            arr1 = sort(arr1);
        }
        if (arr2.length > 1) {
            arr2 = sort(arr2);
        }

        // 将arr1 arr2组合
        // int[] newArr1 = Arrays.copyOf(sourceArray, sourceArray.length);
        int w = 0;
        for (int i : arr1) {
            sourceArray[w++] = i;
        }

        int count = sourceArray.length - arr1.length - arr2.length;
        for (int i = 0; i < count; i++) {
            sourceArray[w++] = pivot;
        }

        for (int i : arr2) {
            sourceArray[w++] = i;
        }

        return sourceArray;
    }

    public static void main(String[] args) throws Exception {
        QuickSort quickSort = new QuickSort();
        int[] sourceArray = {6, 4, 7, 3, 0, 8, 14, 3, 67, 26, 14, 54};
        // int[] sourceArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] sort = quickSort.sort(sourceArray);
        for (int i : sort) {
            System.out.println(i);
        }
    }
}
