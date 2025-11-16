/**
 * 查找算法实现
 * 
 * 包含：顺序查找、折半查找、哈希查找
 * 
 * @author 数据结构练习
 * @version 1.0
 */
import java.util.*;

public class Search {
    
    /**
     * 顺序查找
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 
     * @param arr 数组
     * @param target 目标值
     * @return 目标值的索引，未找到返回-1
     */
    public static int sequentialSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * 折半查找（二分查找）- 递归实现
     * 时间复杂度：O(log n)
     * 空间复杂度：O(log n)
     * 
     * @param arr 有序数组
     * @param target 目标值
     * @return 目标值的索引，未找到返回-1
     */
    public static int binarySearchRecursive(int[] arr, int target) {
        return binarySearchRecursive(arr, target, 0, arr.length - 1);
    }
    
    private static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] > target) {
            return binarySearchRecursive(arr, target, left, mid - 1);
        } else {
            return binarySearchRecursive(arr, target, mid + 1, right);
        }
    }
    
    /**
     * 折半查找（二分查找）- 迭代实现
     * 时间复杂度：O(log n)
     * 空间复杂度：O(1)
     * 
     * @param arr 有序数组
     * @param target 目标值
     * @return 目标值的索引，未找到返回-1
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return -1;
    }
    
    /**
     * 哈希查找（使用HashMap）
     * 时间复杂度：平均O(1)，最坏O(n)
     * 空间复杂度：O(n)
     * 
     * @param arr 数组
     * @param target 目标值
     * @return 目标值的索引，未找到返回-1
     */
    public static int hashSearch(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        // 构建哈希表
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        
        // 查找
        Integer index = map.get(target);
        return index != null ? index : -1;
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        // 无序数组
        int[] arr1 = {3, 1, 4, 1, 5, 9, 2, 6};
        System.out.println("无序数组：" + Arrays.toString(arr1));
        System.out.println("顺序查找5：" + sequentialSearch(arr1, 5));
        System.out.println();
        
        // 有序数组
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("有序数组：" + Arrays.toString(arr2));
        System.out.println("折半查找（递归）5：" + binarySearchRecursive(arr2, 5));
        System.out.println("折半查找（迭代）5：" + binarySearch(arr2, 5));
        System.out.println();
        
        // 哈希查找
        System.out.println("哈希查找5：" + hashSearch(arr1, 5));
    }
}



