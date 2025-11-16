/**
 * 字符串匹配算法实现
 * 
 * 包含：BF算法、KMP算法
 * 
 * @author 数据结构练习
 * @version 1.0
 */
public class StringMatch {
    
    /**
     * BF算法（Brute Force，暴力匹配）
     * 时间复杂度：O(n × m)
     * 空间复杂度：O(1)
     * 
     * @param text 主串
     * @param pattern 模式串
     * @return 第一次匹配的位置，未找到返回-1
     */
    public static int bruteForce(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        
        if (m == 0) return 0;
        if (n < m) return -1;
        
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m && text.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i;  // 找到匹配
            }
        }
        
        return -1;  // 未找到
    }
    
    /**
     * KMP算法
     * 时间复杂度：O(n + m)
     * 空间复杂度：O(m)
     * 
     * @param text 主串
     * @param pattern 模式串
     * @return 第一次匹配的位置，未找到返回-1
     */
    public static int kmp(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        
        if (m == 0) return 0;
        if (n < m) return -1;
        
        // 构建next数组
        int[] next = buildNext(pattern);
        
        int i = 0;  // 主串指针
        int j = 0;  // 模式串指针
        
        while (i < n && j < m) {
            if (j == -1 || text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];  // 利用next数组移动模式串
            }
        }
        
        if (j == m) {
            return i - m;  // 返回匹配的起始位置
        }
        
        return -1;
    }
    
    /**
     * 构建next数组
     * next[i]表示模式串前i个字符的最长公共前后缀长度
     * 
     * @param pattern 模式串
     * @return next数组
     */
    private static int[] buildNext(String pattern) {
        int m = pattern.length();
        int[] next = new int[m];
        
        next[0] = -1;  // 特殊标记
        int j = 0;     // 已匹配的前缀长度
        int i = 1;     // 当前处理的字符位置
        
        while (i < m) {
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (i < m) {
                    next[i] = j;
                }
            } else {
                j = next[j];  // 回溯
            }
        }
        
        return next;
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        String text = "ababcababa";
        String pattern = "ababa";
        
        System.out.println("主串：" + text);
        System.out.println("模式串：" + pattern);
        System.out.println();
        
        // BF算法
        int pos1 = bruteForce(text, pattern);
        System.out.println("BF算法结果：" + (pos1 >= 0 ? "在位置" + pos1 + "找到匹配" : "未找到"));
        
        // KMP算法
        int pos2 = kmp(text, pattern);
        System.out.println("KMP算法结果：" + (pos2 >= 0 ? "在位置" + pos2 + "找到匹配" : "未找到"));
    }
}



