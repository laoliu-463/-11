/**
 * AVL树（平衡二叉树）Java实现
 * 
 * AVL树：一种自平衡的二叉排序树，任意节点的左右子树高度差不超过1
 * 特点：通过旋转操作保持平衡，查找、插入、删除的时间复杂度都是O(log n)
 * 
 * 本实现包含：
 * - AVL树的链式存储
 * - 旋转操作（左旋、右旋、左右旋、右左旋）
 * - 插入操作（自动平衡）
 * - 删除操作（自动平衡）
 * - 查找操作
 * 
 * @author 数据结构练习
 * @version 1.0
 */

// AVL树节点类
class AVLNode {
    int data;           // 数据域
    AVLNode left;       // 左指针
    AVLNode right;      // 右指针
    int height;         // 节点高度
    
    /**
     * 构造函数：创建节点
     * 
     * @param data 节点存储的数据
     */
    AVLNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 1;  // 新节点高度为1
    }
}

// AVL树类
public class AVLTree {
    private AVLNode root;  // 根节点
    
    /**
     * 构造函数：创建空的AVL树
     */
    public AVLTree() {
        this.root = null;
    }
    
    /**
     * 获取根节点
     */
    public AVLNode getRoot() {
        return root;
    }
    
    // ========== 辅助方法 ==========
    
    /**
     * 获取节点的高度
     */
    private int height(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }
    
    /**
     * 获取节点的平衡因子
     * 平衡因子 = 左子树高度 - 右子树高度
     * 
     * @param node 节点
     * @return 平衡因子
     */
    private int getBalanceFactor(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }
    
    /**
     * 更新节点的高度
     */
    private void updateHeight(AVLNode node) {
        if (node != null) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
        }
    }
    
    // ========== 旋转操作 ==========
    
    /**
     * 右旋转（RR旋转）
     * 用于左子树高度大于右子树高度的情况
     * 
     *        y                x
     *       / \              / \
     *      x   T3    -->    T1  y
     *     / \                  / \
     *    T1  T2              T2  T3
     * 
     * @param y 不平衡的节点
     * @return 旋转后的新根节点
     */
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        
        // 执行旋转
        x.right = y;
        y.left = T2;
        
        // 更新高度
        updateHeight(y);
        updateHeight(x);
        
        return x;  // 返回新的根节点
    }
    
    /**
     * 左旋转（LL旋转）
     * 用于右子树高度大于左子树高度的情况
     * 
     *      x                    y
     *     / \                  / \
     *    T1  y        -->      x  T3
     *       / \              / \
     *      T2  T3           T1  T2
     * 
     * @param x 不平衡的节点
     * @return 旋转后的新根节点
     */
    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        
        // 执行旋转
        y.left = x;
        x.right = T2;
        
        // 更新高度
        updateHeight(x);
        updateHeight(y);
        
        return y;  // 返回新的根节点
    }
    
    /**
     * 左右旋转（LR旋转）
     * 先对左子节点左旋，再对当前节点右旋
     * 
     * @param node 不平衡的节点
     * @return 旋转后的新根节点
     */
    private AVLNode leftRightRotate(AVLNode node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }
    
    /**
     * 右左旋转（RL旋转）
     * 先对右子节点右旋，再对当前节点左旋
     * 
     * @param node 不平衡的节点
     * @return 旋转后的新根节点
     */
    private AVLNode rightLeftRotate(AVLNode node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }
    
    // ========== 插入操作 ==========
    
    /**
     * 插入节点
     * 
     * 时间复杂度：O(log n)
     * 空间复杂度：O(log n)
     * 
     * @param data 要插入的值
     */
    public void insert(int data) {
        root = insertRecursive(root, data);
    }
    
    private AVLNode insertRecursive(AVLNode node, int data) {
        // 1. 执行BST插入
        if (node == null) {
            return new AVLNode(data);
        }
        
        if (data < node.data) {
            node.left = insertRecursive(node.left, data);
        } else if (data > node.data) {
            node.right = insertRecursive(node.right, data);
        } else {
            return node;  // 值已存在，不插入
        }
        
        // 2. 更新节点高度
        updateHeight(node);
        
        // 3. 获取平衡因子
        int balance = getBalanceFactor(node);
        
        // 4. 如果节点不平衡，进行旋转
        
        // 左左情况（LL）：右旋转
        if (balance > 1 && data < node.left.data) {
            return rightRotate(node);
        }
        
        // 右右情况（RR）：左旋转
        if (balance < -1 && data > node.right.data) {
            return leftRotate(node);
        }
        
        // 左右情况（LR）：左右旋转
        if (balance > 1 && data > node.left.data) {
            return leftRightRotate(node);
        }
        
        // 右左情况（RL）：右左旋转
        if (balance < -1 && data < node.right.data) {
            return rightLeftRotate(node);
        }
        
        return node;  // 节点已经平衡
    }
    
    // ========== 删除操作 ==========
    
    /**
     * 删除节点
     * 
     * 时间复杂度：O(log n)
     * 空间复杂度：O(log n)
     * 
     * @param data 要删除的值
     */
    public void delete(int data) {
        root = deleteRecursive(root, data);
    }
    
    private AVLNode deleteRecursive(AVLNode node, int data) {
        // 1. 执行BST删除
        if (node == null) {
            return null;
        }
        
        if (data < node.data) {
            node.left = deleteRecursive(node.left, data);
        } else if (data > node.data) {
            node.right = deleteRecursive(node.right, data);
        } else {
            // 找到要删除的节点
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                // 有两个子节点：找到右子树的最小值
                AVLNode minNode = findMin(node.right);
                node.data = minNode.data;
                node.right = deleteRecursive(node.right, minNode.data);
            }
        }
        
        // 2. 更新节点高度
        updateHeight(node);
        
        // 3. 获取平衡因子
        int balance = getBalanceFactor(node);
        
        // 4. 如果节点不平衡，进行旋转
        
        // 左左情况（LL）：右旋转
        if (balance > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }
        
        // 右右情况（RR）：左旋转
        if (balance < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }
        
        // 左右情况（LR）：左右旋转
        if (balance > 1 && getBalanceFactor(node.left) < 0) {
            return leftRightRotate(node);
        }
        
        // 右左情况（RL）：右左旋转
        if (balance < -1 && getBalanceFactor(node.right) > 0) {
            return rightLeftRotate(node);
        }
        
        return node;
    }
    
    /**
     * 找到子树中的最小节点
     */
    private AVLNode findMin(AVLNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    // ========== 查找操作 ==========
    
    /**
     * 查找节点
     * 
     * 时间复杂度：O(log n)
     * 空间复杂度：O(log n)
     * 
     * @param data 要查找的值
     * @return 找到的节点，未找到返回null
     */
    public AVLNode search(int data) {
        return searchRecursive(root, data);
    }
    
    private AVLNode searchRecursive(AVLNode node, int data) {
        if (node == null || node.data == data) {
            return node;
        }
        
        if (data < node.data) {
            return searchRecursive(node.left, data);
        } else {
            return searchRecursive(node.right, data);
        }
    }
    
    // ========== 遍历操作 ==========
    
    /**
     * 中序遍历（得到有序序列）
     */
    public void inOrder() {
        System.out.print("中序遍历（有序序列）：");
        inOrderRecursive(root);
        System.out.println();
    }
    
    private void inOrderRecursive(AVLNode node) {
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
    
    private void preOrderRecursive(AVLNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        preOrderRecursive(node.left);
        preOrderRecursive(node.right);
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
    
    private int sizeRecursive(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + sizeRecursive(node.left) + sizeRecursive(node.right);
    }
    
    /**
     * 求树的高度
     */
    public int height() {
        return height(root);
    }
    
    /**
     * 判断树是否平衡
     */
    public boolean isBalanced() {
        return isBalancedRecursive(root);
    }
    
    private boolean isBalancedRecursive(AVLNode node) {
        if (node == null) {
            return true;
        }
        
        int balance = getBalanceFactor(node);
        if (Math.abs(balance) > 1) {
            return false;
        }
        
        return isBalancedRecursive(node.left) && isBalancedRecursive(node.right);
    }
    
    /**
     * 打印树的信息
     */
    public void printInfo() {
        if (root == null) {
            System.out.println("AVL树为空！");
            return;
        }
        
        System.out.println("========== AVL树信息 ==========");
        System.out.println("节点总数：" + size());
        System.out.println("树的高度：" + height());
        System.out.println("是否平衡：" + isBalanced());
        System.out.println("==============================");
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("========== AVL树测试 ==========\n");
        
        AVLTree avl = new AVLTree();
        
        // 测试插入（会触发旋转）
        System.out.println("【测试1：插入节点（测试旋转）】");
        int[] values = {10, 20, 30, 40, 50, 25};
        System.out.print("插入序列：");
        for (int val : values) {
            System.out.print(val + " ");
            avl.insert(val);
        }
        System.out.println("\n");
        
        // 测试中序遍历
        System.out.println("【测试2：中序遍历（有序序列）】");
        avl.inOrder();
        System.out.println();
        
        // 测试前序遍历
        System.out.println("【测试3：前序遍历】");
        avl.preOrder();
        System.out.println();
        
        // 打印树的信息
        System.out.println("【测试4：树的信息】");
        avl.printInfo();
        System.out.println();
        
        // 测试查找
        System.out.println("【测试5：查找节点】");
        AVLNode found = avl.search(30);
        if (found != null) {
            System.out.println("找到节点：" + found.data);
        } else {
            System.out.println("未找到节点 30");
        }
        System.out.println();
        
        // 测试删除
        System.out.println("【测试6：删除节点】");
        System.out.println("删除节点 20");
        avl.delete(20);
        avl.inOrder();
        avl.printInfo();
        System.out.println();
        
        // 测试大量插入
        System.out.println("【测试7：大量插入测试平衡性】");
        AVLTree avl2 = new AVLTree();
        for (int i = 1; i <= 20; i++) {
            avl2.insert(i);
        }
        System.out.println("插入1-20后：");
        avl2.printInfo();
        System.out.println("中序遍历：");
        avl2.inOrder();
        
        System.out.println("\n========== AVL树测试完成 ==========");
    }
}















