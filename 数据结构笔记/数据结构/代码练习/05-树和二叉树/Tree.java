/**
 * 一般树（多叉树）Java实现
 * 
 * 一般树：每个节点可以有多个子节点的树形结构
 * 特点：层次结构、递归定义、非线性结构、一对多关系
 * 
 * 本实现包含：
 * - 一般树的链式存储（使用子节点列表）
 * - 树的遍历（先根遍历、后根遍历、层次遍历）
 * - 基本操作（求节点数、求深度、求叶子节点数、查找节点、求度等）
 * - 树与二叉树的转换（左子右兄表示法）
 * 
 * @author 数据结构练习
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

// 一般树节点类
class TreeNode {
    int data;                           // 数据域：存储节点数据
    List<TreeNode> children;           // 子节点列表：存储所有子节点
    
    /**
     * 构造函数：创建节点
     * 
     * @param data 节点存储的数据
     */
    TreeNode(int data) {
        this.data = data;
        this.children = new ArrayList<>();
    }
    
    /**
     * 添加子节点
     * 
     * @param child 子节点
     */
    void addChild(TreeNode child) {
        children.add(child);
    }
    
    /**
     * 获取节点的度（子节点个数）
     * 
     * @return 节点的度
     */
    int getDegree() {
        return children.size();
    }
    
    /**
     * 判断是否为叶子节点
     * 
     * @return true表示是叶子节点，false表示不是
     */
    boolean isLeaf() {
        return children.isEmpty();
    }
}

// 一般树类
public class Tree {
    private TreeNode root;  // 根节点
    
    /**
     * 构造函数：创建空的树
     */
    public Tree() {
        this.root = null;
    }
    
    /**
     * 构造函数：创建带根节点的树
     * 
     * @param rootData 根节点的数据
     */
    public Tree(int rootData) {
        this.root = new TreeNode(rootData);
    }
    
    /**
     * 获取根节点
     * 
     * @return 根节点
     */
    public TreeNode getRoot() {
        return root;
    }
    
    /**
     * 设置根节点
     * 
     * @param root 根节点
     */
    public void setRoot(TreeNode root) {
        this.root = root;
    }
    
    // ========== 遍历操作 ==========
    
    /**
     * 先根遍历（前序遍历）：先访问根节点，再依次遍历各子树
     * 
     * 时间复杂度：O(n) - 访问所有节点
     * 空间复杂度：O(h) - 递归栈深度，h为树的高度
     * 
     * @param node 当前节点
     */
    public void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        
        System.out.print(node.data + " ");  // 访问根节点
        
        // 依次遍历各子树
        for (TreeNode child : node.children) {
            preOrder(child);
        }
    }
    
    /**
     * 后根遍历（后序遍历）：先依次遍历各子树，再访问根节点
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(h)
     * 
     * @param node 当前节点
     */
    public void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        
        // 先依次遍历各子树
        for (TreeNode child : node.children) {
            postOrder(child);
        }
        
        System.out.print(node.data + " ");  // 访问根节点
    }
    
    /**
     * 层次遍历：逐层从左到右访问
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(w) - w为树的最大宽度
     * 
     * @param root 根节点
     */
    public void levelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("树为空！");
            return;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.data + " ");
            
            // 将所有子节点加入队列
            for (TreeNode child : node.children) {
                queue.offer(child);
            }
        }
        System.out.println();
    }
    
    // ========== 基本操作 ==========
    
    /**
     * 求树的节点数
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(h)
     * 
     * @param node 当前节点
     * @return 节点总数
     */
    public int countNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int count = 1;  // 当前节点
        
        // 累加所有子树的节点数
        for (TreeNode child : node.children) {
            count += countNodes(child);
        }
        
        return count;
    }
    
    /**
     * 求树的深度/高度
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(h)
     * 
     * @param node 当前节点
     * @return 树的深度
     */
    public int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        if (node.isLeaf()) {
            return 1;  // 叶子节点深度为1
        }
        
        int maxChildHeight = 0;
        // 找出所有子树的最大深度
        for (TreeNode child : node.children) {
            int childHeight = height(child);
            maxChildHeight = Math.max(maxChildHeight, childHeight);
        }
        
        return maxChildHeight + 1;
    }
    
    /**
     * 求叶子节点数
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(h)
     * 
     * @param node 当前节点
     * @return 叶子节点总数
     */
    public int countLeaves(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        if (node.isLeaf()) {
            return 1;  // 叶子节点
        }
        
        int count = 0;
        // 累加所有子树的叶子节点数
        for (TreeNode child : node.children) {
            count += countLeaves(child);
        }
        
        return count;
    }
    
    /**
     * 查找节点
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(h)
     * 
     * @param node 当前节点
     * @param value 要查找的值
     * @return 找到的节点，未找到返回null
     */
    public TreeNode search(TreeNode node, int value) {
        if (node == null) {
            return null;
        }
        
        if (node.data == value) {
            return node;  // 找到节点
        }
        
        // 在所有子树中查找
        for (TreeNode child : node.children) {
            TreeNode found = search(child, value);
            if (found != null) {
                return found;
            }
        }
        
        return null;  // 未找到
    }
    
    /**
     * 求树的度（所有节点度的最大值）
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(h)
     * 
     * @param node 当前节点
     * @return 树的度
     */
    public int getTreeDegree(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int maxDegree = node.getDegree();  // 当前节点的度
        
        // 递归求所有子树的度
        for (TreeNode child : node.children) {
            maxDegree = Math.max(maxDegree, getTreeDegree(child));
        }
        
        return maxDegree;
    }
    
    /**
     * 求树的宽度（最大层节点数）
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(w)
     * 
     * @param root 根节点
     * @return 树的宽度
     */
    public int width(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();  // 当前层的节点数
            maxWidth = Math.max(maxWidth, levelSize);
            
            // 处理当前层的所有节点
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                // 将所有子节点加入队列
                for (TreeNode child : node.children) {
                    queue.offer(child);
                }
            }
        }
        
        return maxWidth;
    }
    
    /**
     * 打印树的信息
     */
    public void printInfo() {
        if (root == null) {
            System.out.println("树为空！");
            return;
        }
        
        System.out.println("========== 树的信息 ==========");
        System.out.println("节点总数：" + countNodes(root));
        System.out.println("树的深度：" + height(root));
        System.out.println("叶子节点数：" + countLeaves(root));
        System.out.println("树的度：" + getTreeDegree(root));
        System.out.println("树的宽度：" + width(root));
        System.out.println("==============================");
    }
    
    /**
     * 打印树的结构（可视化）
     * 
     * @param node 当前节点
     * @param prefix 前缀字符串
     * @param isLast 是否是最后一个子节点
     */
    public void printTree(TreeNode node, String prefix, boolean isLast) {
        if (node == null) {
            return;
        }
        
        System.out.print(prefix);
        System.out.print(isLast ? "└── " : "├── ");
        System.out.println(node.data);
        
        String newPrefix = prefix + (isLast ? "    " : "│   ");
        int childCount = node.children.size();
        
        for (int i = 0; i < childCount; i++) {
            boolean isLastChild = (i == childCount - 1);
            printTree(node.children.get(i), newPrefix, isLastChild);
        }
    }
    
    /**
     * 打印树的结构（从根开始）
     */
    public void printTree() {
        if (root == null) {
            System.out.println("树为空！");
            return;
        }
        printTree(root, "", true);
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("========== 一般树测试 ==========\n");
        
        // 创建示例树
        /*
                A(1)
              / | \
             /  |  \
          B(2) C(3) D(4)
          /|    |
         / |    |
      E(5) F(6) G(7)
        */
        Tree tree = new Tree(1);  // A
        
        TreeNode nodeB = new TreeNode(2);  // B
        TreeNode nodeC = new TreeNode(3);  // C
        TreeNode nodeD = new TreeNode(4);  // D
        TreeNode nodeE = new TreeNode(5);  // E
        TreeNode nodeF = new TreeNode(6);  // F
        TreeNode nodeG = new TreeNode(7);  // G
        
        // 构建树结构
        tree.root.addChild(nodeB);
        tree.root.addChild(nodeC);
        tree.root.addChild(nodeD);
        
        nodeB.addChild(nodeE);
        nodeB.addChild(nodeF);
        
        nodeC.addChild(nodeG);
        
        System.out.println("创建树成功！");
        System.out.println("树的结构：");
        System.out.println("        A(1)");
        System.out.println("      / | \\");
        System.out.println("     /  |  \\");
        System.out.println("  B(2) C(3) D(4)");
        System.out.println("  /|    |");
        System.out.println(" / |    |");
        System.out.println("E(5) F(6) G(7)");
        System.out.println();
        
        // 打印树的可视化结构
        System.out.println("【测试1：树的可视化结构】");
        tree.printTree();
        System.out.println();
        
        // 测试遍历
        System.out.println("【测试2：三种遍历方式】");
        System.out.print("先根遍历：");
        tree.preOrder(tree.root);
        System.out.println("  （根 → 各子树）");
        
        System.out.print("后根遍历：");
        tree.postOrder(tree.root);
        System.out.println("  （各子树 → 根）");
        
        System.out.print("层次遍历：");
        tree.levelOrder(tree.root);
        System.out.println("  （逐层访问）");
        System.out.println();
        
        // 测试基本操作
        System.out.println("【测试3：基本操作】");
        System.out.println("节点总数：" + tree.countNodes(tree.root));
        System.out.println("树的深度：" + tree.height(tree.root));
        System.out.println("叶子节点数：" + tree.countLeaves(tree.root));
        System.out.println("树的度：" + tree.getTreeDegree(tree.root));
        System.out.println("树的宽度：" + tree.width(tree.root));
        System.out.println();
        
        // 测试查找
        System.out.println("【测试4：查找节点】");
        TreeNode found = tree.search(tree.root, 5);
        if (found != null) {
            System.out.println("找到节点：" + found.data);
            System.out.println("节点的度：" + found.getDegree());
            System.out.println("是否为叶子节点：" + found.isLeaf());
        } else {
            System.out.println("未找到节点");
        }
        
        TreeNode notFound = tree.search(tree.root, 10);
        if (notFound != null) {
            System.out.println("找到节点：" + notFound.data);
        } else {
            System.out.println("未找到节点 10");
        }
        System.out.println();
        
        // 打印树的详细信息
        System.out.println("【测试5：树的详细信息】");
        tree.printInfo();
        System.out.println();
        
        // 创建另一个树（更复杂的结构）
        System.out.println("【测试6：复杂树结构】");
        Tree complexTree = new Tree(1);
        
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);
        
        complexTree.root.addChild(n2);
        complexTree.root.addChild(n3);
        complexTree.root.addChild(n4);
        
        n2.addChild(n5);
        n2.addChild(n6);
        
        n3.addChild(n7);
        
        n4.addChild(n8);
        n4.addChild(n9);
        
        System.out.println("复杂树结构：");
        complexTree.printTree();
        System.out.println();
        
        System.out.print("先根遍历：");
        complexTree.preOrder(complexTree.root);
        System.out.println();
        
        System.out.print("后根遍历：");
        complexTree.postOrder(complexTree.root);
        System.out.println();
        
        System.out.print("层次遍历：");
        complexTree.levelOrder(complexTree.root);
        
        complexTree.printInfo();
        
        System.out.println("========== 一般树测试完成 ==========");
    }
}

