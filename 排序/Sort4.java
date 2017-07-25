/**
 * 归并排序
 * 核心：分而治之（Divide and Conquer）
 */
public class Sort4 {

    public int[] Merge_Sort(int[] arr) {
        int[] temp = new int[arr.length];//辅助数组
        mergeSort(arr, 0, arr.length - 1, temp);
        return arr;
    }
    /**递归调用mergeSort,递归的处理左右数组。当first == right,即小数组的长度为1时，可认为此时小数组有序。
    然后再合并相邻的两个小数组*/
    private static void mergeSort(int[] arr, int first, int last, int[] temp) {
        if (first < last) {
            int middle = (first + last) / 2;
            mergeSort(arr, first, middle, temp);//左边有序
            mergeSort(arr, middle + 1, last, temp);//右边有序
            mergeArray(arr, first, middle, middle + 1, last, temp);//将二个有序数列合并
        }
    }

    private static void mergeArray(int[] arr, int start1, int end1, int start2, int end2, int[] temp) {
        int count = 0;
        int L = start1, R = start2;//L,R分别为左右指针
        while (L <= end1 && R <= end2) {
            if (arr[L] <= arr[R])
                temp[count++] = arr[L++];
            else temp[count++] = arr[R++];
        }
        while (L <= end1)
            temp[count++] = arr[L++];
        while (R <= end2)
            temp[count++] = arr[R++];

        for (int i = 0; i < end2 - start1 + 1; i++)
            arr[start1 + i] = temp[i];
    }


}
