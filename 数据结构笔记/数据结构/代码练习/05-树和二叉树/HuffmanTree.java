/**
 * 哈夫曼树和哈夫曼编码 Java实现
 * 
 * 哈夫曼树：带权路径长度最短的二叉树（最优二叉树）
 * 哈夫曼编码：一种可变长度编码，用于数据压缩
 * 
 * 本实现包含：
 * - 哈夫曼树的构造
 * - 哈夫曼编码的生成
 * - 编码和解码操作
 * 
 * @author 数据结构练习
 * @version 1.0
 */

import java.util.*;

// 哈夫曼树节点类
class HuffmanNode {
    char data;              // 字符（叶子节点）
    int frequency;          // 频率（权值）
    HuffmanNode left;      // 左子节点
    HuffmanNode right;     // 右子节点
    
    /**
     * 构造函数：创建节点
     */
    HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }
    
    /**
     * 构造函数：创建内部节点
     */
    HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.data = '\0';  // 内部节点没有字符
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
    
    /**
     * 判断是否为叶子节点
     */
    boolean isLeaf() {
        return left == null && right == null;
    }
}

// 频率比较器（用于优先队列）
class FrequencyComparator implements Comparator<HuffmanNode> {
    @Override
    public int compare(HuffmanNode a, HuffmanNode b) {
        return a.frequency - b.frequency;
    }
}

// 哈夫曼树类
public class HuffmanTree {
    private HuffmanNode root;  // 根节点
    private Map<Character, String> codes;  // 字符到编码的映射
    
    /**
     * 构造函数：根据字符频率构建哈夫曼树
     * 
     * @param frequencies 字符频率映射
     */
    public HuffmanTree(Map<Character, Integer> frequencies) {
        codes = new HashMap<>();
        root = buildTree(frequencies);
        generateCodes(root, "", codes);
    }
    
    /**
     * 构建哈夫曼树
     * 
     * 算法步骤：
     * 1. 将每个字符创建为节点，加入优先队列
     * 2. 每次取出频率最小的两个节点
     * 3. 合并成一个新节点，新节点频率 = 两节点频率之和
     * 4. 将新节点加入队列
     * 5. 重复步骤2-4，直到队列只剩一个节点（根节点）
     * 
     * 时间复杂度：O(n log n)
     * 空间复杂度：O(n)
     */
    private HuffmanNode buildTree(Map<Character, Integer> frequencies) {
        // 使用优先队列（最小堆）
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(new FrequencyComparator());
        
        // 为每个字符创建节点并加入队列
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            pq.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        
        // 构建哈夫曼树
        while (pq.size() > 1) {
            // 取出频率最小的两个节点
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            
            // 创建新节点，频率为两节点之和
            int newFreq = left.frequency + right.frequency;
            HuffmanNode newNode = new HuffmanNode(newFreq, left, right);
            
            // 将新节点加入队列
            pq.offer(newNode);
        }
        
        return pq.poll();  // 返回根节点
    }
    
    /**
     * 生成哈夫曼编码
     * 
     * 编码规则：
     * - 左分支编码为0
     * - 右分支编码为1
     * - 从根到叶子节点的路径就是该字符的编码
     * 
     * @param node 当前节点
     * @param code 当前编码
     * @param codes 编码映射表
     */
    private void generateCodes(HuffmanNode node, String code, Map<Character, String> codes) {
        if (node == null) {
            return;
        }
        
        // 叶子节点，记录编码
        if (node.isLeaf()) {
            codes.put(node.data, code.isEmpty() ? "0" : code);  // 只有一个字符时编码为"0"
            return;
        }
        
        // 左分支编码0，右分支编码1
        generateCodes(node.left, code + "0", codes);
        generateCodes(node.right, code + "1", codes);
    }
    
    /**
     * 编码：将字符串转换为哈夫曼编码
     * 
     * @param text 原始文本
     * @return 编码后的字符串
     */
    public String encode(String text) {
        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            encoded.append(codes.get(c));
        }
        return encoded.toString();
    }
    
    /**
     * 解码：将哈夫曼编码转换为原始字符串
     * 
     * @param encoded 编码后的字符串
     * @return 原始文本
     */
    public String decode(String encoded) {
        StringBuilder decoded = new StringBuilder();
        HuffmanNode current = root;
        
        for (char bit : encoded.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }
            
            // 到达叶子节点，解码一个字符
            if (current.isLeaf()) {
                decoded.append(current.data);
                current = root;  // 重置到根节点
            }
        }
        
        return decoded.toString();
    }
    
    /**
     * 获取编码表
     * 
     * @return 字符到编码的映射
     */
    public Map<Character, String> getCodes() {
        return new HashMap<>(codes);
    }
    
    /**
     * 计算带权路径长度（WPL）
     * 
     * @return WPL值
     */
    public int calculateWPL() {
        return calculateWPLRecursive(root, 0);
    }
    
    private int calculateWPLRecursive(HuffmanNode node, int depth) {
        if (node == null) {
            return 0;
        }
        
        if (node.isLeaf()) {
            return node.frequency * depth;
        }
        
        return calculateWPLRecursive(node.left, depth + 1) + 
               calculateWPLRecursive(node.right, depth + 1);
    }
    
    /**
     * 打印编码表
     */
    public void printCodes() {
        System.out.println("========== 哈夫曼编码表 ==========");
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.println("字符: " + entry.getKey() + " -> 编码: " + entry.getValue());
        }
        System.out.println("==================================");
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("========== 哈夫曼树和编码测试 ==========\n");
        
        // 示例1：简单示例
        System.out.println("【测试1：简单示例】");
        Map<Character, Integer> freq1 = new HashMap<>();
        freq1.put('A', 5);
        freq1.put('B', 3);
        freq1.put('C', 2);
        freq1.put('D', 1);
        
        System.out.println("字符频率：");
        for (Map.Entry<Character, Integer> entry : freq1.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        
        HuffmanTree tree1 = new HuffmanTree(freq1);
        tree1.printCodes();
        System.out.println("带权路径长度（WPL）: " + tree1.calculateWPL());
        System.out.println();
        
        // 测试编码和解码
        System.out.println("【测试2：编码和解码】");
        String text1 = "AABBCDDD";
        System.out.println("原始文本: " + text1);
        
        String encoded1 = tree1.encode(text1);
        System.out.println("编码后: " + encoded1);
        System.out.println("原始长度: " + (text1.length() * 2) + "位（假设每个字符2位）");
        System.out.println("编码长度: " + encoded1.length() + "位");
        System.out.println("压缩率: " + String.format("%.2f%%", 
            (1.0 - (double)encoded1.length() / (text1.length() * 2)) * 100));
        
        String decoded1 = tree1.decode(encoded1);
        System.out.println("解码后: " + decoded1);
        System.out.println("解码正确: " + text1.equals(decoded1));
        System.out.println();
        
        // 示例2：文本压缩示例
        System.out.println("【测试3：文本压缩示例】");
        String text2 = "HELLO WORLD";
        System.out.println("原始文本: " + text2);
        
        // 统计字符频率
        Map<Character, Integer> freq2 = new HashMap<>();
        for (char c : text2.toCharArray()) {
            if (c != ' ') {  // 忽略空格
                freq2.put(c, freq2.getOrDefault(c, 0) + 1);
            }
        }
        
        System.out.println("字符频率：");
        for (Map.Entry<Character, Integer> entry : freq2.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        
        HuffmanTree tree2 = new HuffmanTree(freq2);
        tree2.printCodes();
        
        String encoded2 = tree2.encode(text2.replace(" ", ""));
        System.out.println("编码后: " + encoded2);
        System.out.println("编码长度: " + encoded2.length() + "位");
        
        String decoded2 = tree2.decode(encoded2);
        System.out.println("解码后: " + decoded2);
        System.out.println();
        
        // 示例3：单个字符
        System.out.println("【测试4：单个字符（边界情况）】");
        Map<Character, Integer> freq3 = new HashMap<>();
        freq3.put('X', 10);
        
        HuffmanTree tree3 = new HuffmanTree(freq3);
        tree3.printCodes();
        
        String text3 = "X";
        String encoded3 = tree3.encode(text3);
        System.out.println("原始文本: " + text3);
        System.out.println("编码后: " + encoded3);
        
        String decoded3 = tree3.decode(encoded3);
        System.out.println("解码后: " + decoded3);
        
        System.out.println("\n========== 哈夫曼树和编码测试完成 ==========");
    }
}















