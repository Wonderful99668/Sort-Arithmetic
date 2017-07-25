/**
 * 选择排序与堆排序
 */
public class Sort2 {
    /**
     * 基本思想：在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
     * 然后在剩下的数当中再找最小的与第二个位置的数交换，如此循环到倒数第二个数和最后一个数比较为止。
     */
    /**选择排序*/
    public int[] selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int target = arr[i];
            int position = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < target) {
                    position = j;
                    target = arr[position];
                }
            }
            arr[position] = arr[i];
            arr[i] = target;
        }
        return arr;
    }

    /**
     * HeapSort
     * 其基本思想为(大顶堆)：
     1)将初始待排序关键字序列(R1,R2....Rn)构建成大顶堆，此堆为初始的无序区；
     2)将堆顶元素R[1]与最后一个元素R[n]交换，此时得到新的无序区(R1,R2,......Rn-1)和新的有序区(Rn),且满足R[1,2...n-1]<=R[n];
     3)由于交换后新的堆顶R[1]可能违反堆的性质，因此需要对当前无序区(R1,R2,......Rn-1)调整为新堆，然后再次将R[1]与无序区最后一个元素交换，
     得到新的无序区(R1,R2....Rn-2)和新的有序区(Rn-1,Rn)。不断重复此过程直到有序区的元素个数为n-1，则整个排序过程完成。
     **/

    public int[] heapSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; ++i) {
            int lastIndex = arr.length - 1 - i;
            buildMaxHeap(arr, lastIndex);
            swap(arr, 0, lastIndex);//交换堆顶和最后一个元素
        }
        return arr;
    }
    //对arr数组从0到lastIndex建大顶堆
    private void buildMaxHeap(int[] arr, int lastIndex) {
        /*从最后一个节点(lastIndex)的父节点开始
        叶节点的父节点为(index-1)/2
        leftChild = parent*2+1;
        rightChild = parent*2+2
         */
        for (int i = (lastIndex - 1) / 2; i >= 0; i--)
            heapAdjust(arr, i, lastIndex);
    }

    /**核心算法*/
    private void heapAdjust(int[] arr, int target, int lastIndex) {
        //如果当前k节点的子节点存在
        while (target * 2 + 1 <= lastIndex) {
            int lChild = target * 2 + 1;
            int biggerIndex = lChild;//biggerIndex始终保存叶节点中较大节点的索引
            if (lChild < lastIndex) {
                int rChild = lChild + 1;
                if (arr[rChild] > arr[lChild])
                    biggerIndex = rChild;
            }

            if (arr[target] < arr[biggerIndex]) {
                swap(arr, target, biggerIndex);//保证堆顶为最大元素
                target = biggerIndex;//重新进入循环，调整以biggerIndex为堆顶的堆为大顶堆
            } else break;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
