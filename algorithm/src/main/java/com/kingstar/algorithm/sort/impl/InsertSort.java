package com.kingstar.algorithm.sort.impl;

import com.kingstar.algorithm.sort.IArraySort;

import java.util.Arrays;

/**
 * @author xiayc
 * @date 2020/10/10 10:44
 * @desc 插入排序
 */
public class InsertSort implements IArraySort {
    /**
     * 算法思路
     * 1. 选择一个元素作为有序序列,未排序的 遍历其中每个元素,找到合适的位置插入
     * <p>
     * 将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。
     * 从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。（如果待插入的元素与有序序列中的某个元素相等，
     * 则将待插入元素插入到相等元素的后面。）
     * </p>
     *
     * @param sourceArray
     * @return
     * @throws Exception
     */
    @Override
    public int[] sort(int[] sourceArray) {
        int[] newArray = Arrays.copyOf(sourceArray, sourceArray.length);


        for (int i = 1; i < newArray.length; i++) {
            // 取出未排序队列里的值 逐个找到应该插入的位置
            int temp = newArray[i];
            int j = i;

            while (j > 0 && newArray[j - 1] > temp) {
                newArray[j] = newArray[j - 1];
                j--;
            }
            // 插入元素
            if (i != j) {
                newArray[j] = temp;
            }
        }
        return newArray;
    }

    public static void main(String[] args) {
        InsertSort insertSort = new InsertSort();
        int[] sourceArray = {6, 4, 7, 3, 8, 14, 67, 26, 54};
        // int[] sourceArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] sort = insertSort.sort(sourceArray);
        for (int i : sort) {
            System.out.println(i);
        }
    }
}
