/**
 * 排序算法实现
 * 
 * 包含：冒泡排序、快速排序、归并排序、堆排序
 * 
 * @author 数据结构练习
 * @version 1.0
 */
import java.util.*;

public class Sort {
    
    /**
     * 冒泡排序
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     * 稳定性：稳定
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;  // 优化：提前终止
        }
    }
    
    /**
     * 快速排序
     * 时间复杂度：平均O(n log n)，最坏O(n²)
     * 空间复杂度：O(log n)
     * 稳定性：不稳定
     */
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }
    
    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;
        
        int pivot = partition(arr, left, right);
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }
    
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }
    
    /**
     * 归并排序
     * 时间复杂度：O(n log n)
     * 空间复杂度：O(n)
     * 稳定性：稳定
     */
    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }
    
    private static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;
        
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        
        for (i = 0; i < temp.length; i++) {
            arr[left + i] = temp[i];
        }
    }
    
    /**
     * 堆排序
     * 时间复杂度：O(n log n)
     * 空间复杂度：O(1)
     * 稳定性：不稳定
     */
    public static void heapSort(int[] arr) {
        int n = arr.length;
        
        // 构建最大堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        
        // 依次取出堆顶元素
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }
    
    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }
    
    /**
     * 交换数组元素
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        int[] arr1 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("原数组：" + Arrays.toString(arr1));
        
        // 冒泡排序
        int[] arr2 = arr1.clone();
        bubbleSort(arr2);
        System.out.println("冒泡排序：" + Arrays.toString(arr2));
        
        // 快速排序
        int[] arr3 = arr1.clone();
        quickSort(arr3);
        System.out.println("快速排序：" + Arrays.toString(arr3));
        
        // 归并排序
        int[] arr4 = arr1.clone();
        mergeSort(arr4);
        System.out.println("归并排序：" + Arrays.toString(arr4));
        
        // 堆排序
        int[] arr5 = arr1.clone();
        heapSort(arr5);
        System.out.println("堆排序：" + Arrays.toString(arr5));
    }
}



