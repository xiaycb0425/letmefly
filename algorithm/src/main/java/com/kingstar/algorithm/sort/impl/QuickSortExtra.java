package com.kingstar.algorithm.sort.impl;

import com.kingstar.algorithm.sort.IArraySort;

/**
 * @author xiayc
 * @date 2020/10/13 15:41
 * @desc 快速排序
 */
public class QuickSortExtra implements IArraySort {
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

        return quickSort(sourceArray, 0, sourceArray.length);
    }

    private int[] quickSort(int[] s, int l, int r) {
        if (l < r)
        {
            //Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1
            int i = l, j = r, x = s[l];
            while (i < j)
            {
                while(i < j && s[j] >= x) // 从右向左找第一个小于x的数
                    j--;
                if(i < j)
                    s[i++] = s[j];

                while(i < j && s[i] < x) // 从左向右找第一个大于等于x的数
                    i++;
                if(i < j)
                    s[j--] = s[i];
            }
            s[i] = x;
            quickSort(s, l, i - 1); // 递归调用
            quickSort(s, i + 1, r);
        }
        return s;
    }

    public static void main(String[] args) throws Exception {
        QuickSortExtra quickSort = new QuickSortExtra();
        int[] sourceArray = {6, 4, 7, 3, 0, 8, 14, 3, 67, 26, 14, 54};
        // int[] sourceArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] sort = quickSort.sort(sourceArray);
        for (int i : sort) {
            System.out.println(i);
        }
    }
}
