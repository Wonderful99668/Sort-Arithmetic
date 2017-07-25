/**
 * 冒泡排序与快速排序
 */
public class Sort3 {
    /**冒泡排序*/
    //原理：比较两个相邻的元素，将值大的元素交换至右端。
    public int[] bubbleSort(int arr[]) {
        for (int i = 0; i < arr.length - 1; ++i) {
            for (int j = 0; j < arr.length - 1 - i; ++j) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * QuickSort
     * 基本思想：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
     * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
     * 核心：分而治之 + “挖坑填数”
     * 关键：取主元pivot的方法
     **/

    public int[] Quick_Sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    /**
     * 快速排序核心算法
     */

    private void quickSort(int[] arr, int left, int right) {

        int low = left, high = right;
        int pivot;//主元
        if (right - left > 2)
            pivot = median3(arr, left, right);
        else pivot = arr[low];

        while (low < high) {
            while (low < high && arr[high] >= pivot)
                high--;
            if (low < high)
                arr[low++] = arr[high];
            while (low < high && arr[low] <= pivot)
                low++;
            if (low < high)
                arr[high--] = arr[low];
        }
        arr[low] = pivot;

        if (low > left)
            quickSort(arr, left, low - 1);
        if (low + 1 < right)
            quickSort(arr, low + 1, right);
    }

    /*确定pivot值的算法：取左，中，右三个数的中间值*/
    private int median3(int[] arr, int left, int right) {
        int center = (left + right) / 2;
        /*三步操作保证arr[left]<=arr[center]<=arr[right]*/
        if (arr[left] > arr[center])
            swap(arr, left, center);
        if (arr[left] > arr[right])
            swap(arr, left, right);
        if (arr[center] > arr[right])
            swap(arr, center, right);

        swap(arr, center, left);

        return arr[left];/*返回pivot*/
    }

    private int[] insertSort(int[] arr, int left, int right) {
        for (int i = left; i < right; i++) {
            int j = i - 1;
            int target = arr[i];
            for (; j >= 0 && target < arr[j]; j--)
                arr[j + 1] = arr[j];//将大于target的值整体后移一个单位
            arr[j + 1] = target;
        }
        return arr;
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
