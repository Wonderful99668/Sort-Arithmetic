public class Sort1 {

    private int a[]={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
    public static void main(String[] args) {
        Sort1 test1 = new Sort1();
        //Sort3 test = new Sort3();
        Sort4 test = new Sort4();
        long start = System.currentTimeMillis();
        int[]c = test.Merge_Sort(test1.a);
        long end = System.currentTimeMillis();
        for (int i=0;i<test1.a.length;i++)
            System.out.print(c[i]+" ");
        System.out.println();
        System.out.print("快速排序耗时:"+(end - start)+"毫秒");
    }

    /**
     * 假设前面(n-1) [n>=2] 个数已经是排好顺序的，现在要把第n个数插到前面的有序数中，使得这n个数
     * 也是排好顺序的。如此反复循环，直到全部排好顺序。
     **/
    public int[] insertSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int j = i - 1;
            int target = arr[i];
            for (; j >= 0 && target < arr[j]; j--)
                arr[j + 1] = arr[j];//将大于target的值整体后移一个单位
            arr[j + 1] = target;
        }
        return arr;
    }

    /**
     * 基本思想：算法先将要排序的一组数按某个增量d（n/2,n为要排序数的个数）分成若干组(d组)，
     * 每组中记录的下标相差d.对每组中全部元素进行直接插入排序，然后再用一个较小的增量（d/2）对它进行分组，
     * 在每组中再进行直接插入排序。当增量减到1时，进行直接插入排序后，排序完成。
     **/
    public int[] shellSort(int[] arr) {
        double middle = arr.length;

        while (true) {
            middle = Math.ceil(middle/2);//返回大于参数x的最小整数
            int d = (int) middle;

            for (int count = 0; count < d; count++) {
                for (int i = count + d; i < arr.length; i += d) {
                    int target = arr[i];
                    int j = i - d;
                    for (; j >= 0 && target < arr[j]; j -= d)
                        arr[j+d]=arr[j];
                    arr[j+d]=target;
                }
            }
            if (d==1) break;
        }
        return arr;
    }

}
