/**
 * 二叉排序树（Binary Search Tree, BST）Java实现
 * 
 * 二叉排序树：对于任意节点，左子树的所有节点值 < 根节点值 < 右子树的所有节点值
 * 特点：中序遍历可以得到有序序列、查找效率高（平均O(log n)）
 * 
 * 本实现包含：
 * - 二叉排序树的链式存储
 * - 基本操作（插入、删除、查找）
 * - 遍历操作（中序遍历得到有序序列）
 * - 性能分析
 * 
 * @author 数据结构练习
 * @version 1.0
 */

// 二叉排序树节点类
class BSTNode {
    int data;           // 数据域
    BSTNode left;       // 左指针：指向左子节点（所有值 < data）
    BSTNode right;      // 右指针：指向右子节点（所有值 > data）
    
    /**
     * 构造函数：创建节点
     * 
     * @param data 节点存储的数据
     */
    BSTNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

// 二叉排序树类
public class BinarySearchTree {
    private BSTNode root;  // 根节点
    
    /**
     * 构造函数：创建空的二叉排序树
     */
    public BinarySearchTree() {
        this.root = null;
    }
    
    /**
     * 获取根节点
     * 
     * @return 根节点
     */
    public BSTNode getRoot() {
        return root;
    }
    
    // ========== 查找操作 ==========
    
    /**
     * 查找节点（递归）
     * 
     * 时间复杂度：O(h)，h为树的高度
     *   - 最好情况（平衡树）：O(log n)
     *   - 最坏情况（单支树）：O(n)
     * 空间复杂度：O(h)
     * 
     * @param data 要查找的值
     * @return 找到的节点，未找到返回null
     */
    public BSTNode search(int data) {
        return searchRecursive(root, data);
    }
    
    private BSTNode searchRecursive(BSTNode node, int data) {
        if (node == null || node.data == data) {
            return node;
        }
        
        if (data < node.data) {
            return searchRecursive(node.left, data);  // 在左子树中查找
        } else {
            return searchRecursive(node.right, data); // 在右子树中查找
        }
    }
    
    /**
     * 查找节点（非递归）
     * 
     * 时间复杂度：O(h)
     * 空间复杂度：O(1)
     */
    public BSTNode searchIterative(int data) {
        BSTNode current = root;
        
        while (current != null) {
            if (data == current.data) {
                return current;  // 找到节点
            } else if (data < current.data) {
                current = current.left;   // 在左子树中查找
            } else {
                current = current.right;  // 在右子树中查找
            }
        }
        
        return null;  // 未找到
    }
    
    // ========== 插入操作 ==========
    
    /**
     * 插入节点（递归）
     * 
     * 时间复杂度：O(h)
     * 空间复杂度：O(h)
     * 
     * @param data 要插入的值
     * @return true表示插入成功，false表示值已存在
     */
    public boolean insert(int data) {
        if (root == null) {
            root = new BSTNode(data);
            return true;
        }
        return insertRecursive(root, data);
    }
    
    private boolean insertRecursive(BSTNode node, int data) {
        if (data < node.data) {
            if (node.left == null) {
                node.left = new BSTNode(data);
                return true;
            } else {
                return insertRecursive(node.left, data);
            }
        } else if (data > node.data) {
            if (node.right == null) {
                node.right = new BSTNode(data);
                return true;
            } else {
                return insertRecursive(node.right, data);
            }
        } else {
            return false;  // 值已存在，不插入
        }
    }
    
    /**
     * 插入节点（非递归）
     * 
     * 时间复杂度：O(h)
     * 空间复杂度：O(1)
     */
    public boolean insertIterative(int data) {
        if (root == null) {
            root = new BSTNode(data);
            return true;
        }
        
        BSTNode current = root;
        BSTNode parent = null;
        
        while (current != null) {
            parent = current;
            
            if (data < current.data) {
                current = current.left;
            } else if (data > current.data) {
                current = current.right;
            } else {
                return false;  // 值已存在
            }
        }
        
        // 插入新节点
        if (data < parent.data) {
            parent.left = new BSTNode(data);
        } else {
            parent.right = new BSTNode(data);
        }
        
        return true;
    }
    
    // ========== 删除操作 ==========
    
    /**
     * 删除节点
     * 
     * 时间复杂度：O(h)
     * 空间复杂度：O(h)
     * 
     * @param data 要删除的值
     * @return true表示删除成功，false表示值不存在
     */
    public boolean delete(int data) {
        root = deleteRecursive(root, data);
        return root != null || search(data) == null;
    }
    
    private BSTNode deleteRecursive(BSTNode node, int data) {
        if (node == null) {
            return null;  // 节点不存在
        }
        
        if (data < node.data) {
            // 在左子树中删除
            node.left = deleteRecursive(node.left, data);
        } else if (data > node.data) {
            // 在右子树中删除
            node.right = deleteRecursive(node.right, data);
        } else {
            // 找到要删除的节点
            if (node.left == null) {
                // 情况1：只有右子树或没有子树
                return node.right;
            } else if (node.right == null) {
                // 情况2：只有左子树
                return node.left;
            } else {
                // 情况3：有两个子树
                // 找到右子树中的最小节点（中序后继）
                BSTNode minNode = findMin(node.right);
                // 用最小节点的值替换当前节点
                node.data = minNode.data;
                // 删除右子树中的最小节点
                node.right = deleteRecursive(node.right, minNode.data);
            }
        }
        
        return node;
    }
    
    /**
     * 找到子树中的最小节点（最左节点）
     */
    private BSTNode findMin(BSTNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    /**
     * 找到子树中的最大节点（最右节点）
     */
    private BSTNode findMax(BSTNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }
    
    // ========== 遍历操作 ==========
    
    /**
     * 中序遍历（得到有序序列）
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(h)
     */
    public void inOrder() {
        System.out.print("中序遍历（有序序列）：");
        inOrderRecursive(root);
        System.out.println();
    }
    
    private void inOrderRecursive(BSTNode node) {
        if (node == null) {
            return;
        }
        inOrderRecursive(node.left);
        System.out.print(node.data + " ");
        inOrderRecursive(node.right);
    }
    
    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.print("前序遍历：");
        preOrderRecursive(root);
        System.out.println();
    }
    
    private void preOrderRecursive(BSTNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        preOrderRecursive(node.left);
        preOrderRecursive(node.right);
    }
    
    /**
     * 后序遍历
     */
    public void postOrder() {
        System.out.print("后序遍历：");
        postOrderRecursive(root);
        System.out.println();
    }
    
    private void postOrderRecursive(BSTNode node) {
        if (node == null) {
            return;
        }
        postOrderRecursive(node.left);
        postOrderRecursive(node.right);
        System.out.print(node.data + " ");
    }
    
    // ========== 基本操作 ==========
    
    /**
     * 判断树是否为空
     */
    public boolean isEmpty() {
        return root == null;
    }
    
    /**
     * 求树的节点数
     */
    public int size() {
        return sizeRecursive(root);
    }
    
    private int sizeRecursive(BSTNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + sizeRecursive(node.left) + sizeRecursive(node.right);
    }
    
    /**
     * 求树的高度
     */
    public int height() {
        return heightRecursive(root);
    }
    
    private int heightRecursive(BSTNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = heightRecursive(node.left);
        int rightHeight = heightRecursive(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    /**
     * 查找最小值
     */
    public int findMin() {
        if (root == null) {
            throw new RuntimeException("树为空！");
        }
        return findMin(root).data;
    }
    
    /**
     * 查找最大值
     */
    public int findMax() {
        if (root == null) {
            throw new RuntimeException("树为空！");
        }
        return findMax(root).data;
    }
    
    /**
     * 打印树的信息
     */
    public void printInfo() {
        if (root == null) {
            System.out.println("二叉排序树为空！");
            return;
        }
        
        System.out.println("========== 二叉排序树信息 ==========");
        System.out.println("节点总数：" + size());
        System.out.println("树的高度：" + height());
        System.out.println("最小值：" + findMin());
        System.out.println("最大值：" + findMax());
        System.out.println("================================");
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("========== 二叉排序树测试 ==========\n");
        
        BinarySearchTree bst = new BinarySearchTree();
        
        // 测试插入
        System.out.println("【测试1：插入节点】");
        int[] values = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45};
        System.out.print("插入序列：");
        for (int val : values) {
            System.out.print(val + " ");
            bst.insert(val);
        }
        System.out.println("\n");
        
        // 测试中序遍历（应该是有序序列）
        System.out.println("【测试2：中序遍历（有序序列）】");
        bst.inOrder();
        System.out.println();
        
        // 测试前序和后序遍历
        System.out.println("【测试3：前序和后序遍历】");
        bst.preOrder();
        bst.postOrder();
        System.out.println();
        
        // 测试查找
        System.out.println("【测试4：查找节点】");
        BSTNode found = bst.search(40);
        if (found != null) {
            System.out.println("找到节点：" + found.data);
        } else {
            System.out.println("未找到节点 40");
        }
        
        BSTNode notFound = bst.search(100);
        if (notFound != null) {
            System.out.println("找到节点：" + notFound.data);
        } else {
            System.out.println("未找到节点 100");
        }
        System.out.println();
        
        // 打印树的信息
        System.out.println("【测试5：树的信息】");
        bst.printInfo();
        System.out.println();
        
        // 测试删除
        System.out.println("【测试6：删除节点】");
        System.out.println("删除节点 20（叶子节点）");
        bst.delete(20);
        bst.inOrder();
        
        System.out.println("删除节点 30（有一个子节点）");
        bst.delete(30);
        bst.inOrder();
        
        System.out.println("删除节点 50（有两个子节点，根节点）");
        bst.delete(50);
        bst.inOrder();
        System.out.println();
        
        // 最终树的信息
        System.out.println("【测试7：删除后的树信息】");
        bst.printInfo();
        
        System.out.println("========== 二叉排序树测试完成 ==========");
    }
}















