package com.kingstar.algorithm.sort.impl;

import com.kingstar.algorithm.sort.IArraySort;

import java.util.Arrays;

/**
 * @author xiayc
 * @date 2020/10/10 9:35
 * @desc 选择排序
 */
public class SelectSort implements IArraySort {


    /**
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
     * <p>
     * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     * <p>
     * 重复第二步，直到所有元素均排序完毕。
     *
     * @param sourceArray
     * @return
     * @throws Exception
     */

    @Override
    public int[] sort(int[] sourceArray)  {
        int[] newArray = Arrays.copyOf(sourceArray, sourceArray.length);


        for (int j = 0; j < newArray.length - 1; j++) {

            int minIndex = j;
            for (int i = j + 1; i < newArray.length; i++) {
                if (newArray[i] < newArray[minIndex]) {
                    minIndex = i;
                }
            }
            // 做一次交换
            if (j != minIndex) {
                int temp = newArray[j];
                newArray[j] = newArray[minIndex];
                newArray[minIndex] = temp;
            }
        }
        return newArray;
    }

    public static void main(String[] args){
        SelectSort selectSort = new SelectSort();
        int[] sourceArray = {6, 4, 7, 3, 8, 14, 67, 26, 54};
        // int[] sourceArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] sort = selectSort.sort(sourceArray);
        for (int i : sort) {
            System.out.println(i);
        }
    }
}
