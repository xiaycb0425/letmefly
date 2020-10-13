package com.kingstar.algorithm.sort.impl;

import com.kingstar.algorithm.sort.IArraySort;

import java.util.Arrays;

/**
 * @author xiayc
 * @date 2020/10/12 16:31
 * @desc 归并排序
 */
public class MergeSort implements IArraySort {

    /**
     * 2. 算法步骤
     * 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列；
     * <p>
     * 设定两个指针，最初位置分别为两个已经排序序列的起始位置；
     * <p>
     * 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置；
     * <p>
     * 重复步骤 3 直到某一指针达到序列尾；
     * <p>
     * 将另一序列剩下的所有元素直接复制到合并序列尾。
     *
     * @param sourceArray
     * @return
     */
    @Override
    public int[] sort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int[] arr1 = Arrays.copyOfRange(arr, 0, (int) Math.floor(arr.length / 2));

        int[] arr2 = Arrays.copyOfRange(arr, (int) Math.floor(arr.length / 2), arr.length);


        return mergeTwoArray(arr1, arr2);
    }

    private int[] mergeTwoArray(int[] leftArr, int[] rightArr) {
        int[] newArr = new int[leftArr.length + rightArr.length];
        // 向下递归
        if (leftArr.length > 1) {
            int[] arr1 = Arrays.copyOfRange(leftArr, 0, (int) Math.floor(leftArr.length / 2));
            int[] arr2 = Arrays.copyOfRange(leftArr, (int) Math.floor(leftArr.length / 2), leftArr.length);
            leftArr = mergeTwoArray(arr1, arr2);
        }

        if (rightArr.length > 1) {
            int[] arr1 = Arrays.copyOfRange(rightArr, 0, (int) Math.floor(rightArr.length / 2));
            int[] arr2 = Arrays.copyOfRange(rightArr, (int) Math.floor(rightArr.length / 2), rightArr.length);
            rightArr = mergeTwoArray(arr1, arr2);
        }
        // 给新数组赋值
        int m = 0, i = 0, j = 0;
        while (i < leftArr.length && j < rightArr.length) {
            newArr[m++] = leftArr[i] > rightArr[j] ? rightArr[j++] : leftArr[i++];
        }
        while (i < leftArr.length) {
            newArr[m++] = leftArr[i++];
        }

        while (j < rightArr.length) {
            newArr[m++] = rightArr[j++];
        }

        return newArr;
    }

    public static void main(String[] args) throws Exception {
        MergeSort mergeSort = new MergeSort();
        int[] sourceArray = {6, 4, 7, 3, 8, 14, 67, 26, 54, 555, 3, 5, 1, 55, 22, 676, 66666, 34, 0, 87, 45, 23};
        // int[] sourceArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] sort = mergeSort.sort(sourceArray);
        for (int i : sort) {
            System.out.println(i);
        }
    }
}
