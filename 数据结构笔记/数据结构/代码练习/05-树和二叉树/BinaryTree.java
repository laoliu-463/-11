/**
 * 二叉树 Java实现
 * 
 * 二叉树：每个节点最多有两个子节点（左子节点和右子节点）的树形结构
 * 特点：层次结构、递归定义、非线性结构
 * 
 * 本实现包含：
 * - 二叉树的链式存储
 * - 四种遍历方式（前序、中序、后序、层次遍历）
 * - 基本操作（求节点数、求深度、求叶子节点数、查找节点等）
 * 
 * @author 数据结构练习
 * @version 1.0
 */

import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

// 二叉树节点类
class TreeNode {
    int data;           // 数据域：存储节点数据
    TreeNode left;      // 左指针：指向左子节点
    TreeNode right;     // 右指针：指向右子节点
    
    /**
     * 构造函数：创建节点
     * 
     * @param data 节点存储的数据
     */
    TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

// 二叉树类
public class BinaryTree {
    private TreeNode root;  // 根节点
    
    /**
     * 构造函数：创建空的二叉树
     */
    public BinaryTree() {
        this.root = null;
    }
    
    /**
     * 构造函数：创建带根节点的二叉树
     * 
     * @param rootData 根节点的数据
     */
    public BinaryTree(int rootData) {
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
    
    // ========== 遍历操作（递归） ==========
    
    /**
     * 前序遍历（递归）：根 → 左 → 右
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
        preOrder(node.left);                 // 前序遍历左子树
        preOrder(node.right);                // 前序遍历右子树
    }
    
    /**
     * 中序遍历（递归）：左 → 根 → 右
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(h)
     * 
     * @param node 当前节点
     */
    public void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);                  // 中序遍历左子树
        System.out.print(node.data + " ");   // 访问根节点
        inOrder(node.right);                 // 中序遍历右子树
    }
    
    /**
     * 后序遍历（递归）：左 → 右 → 根
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
        postOrder(node.left);                // 后序遍历左子树
        postOrder(node.right);               // 后序遍历右子树
        System.out.print(node.data + " ");   // 访问根节点
    }
    
    /**
     * 层次遍历（使用队列）：逐层从左到右访问
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
            
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        System.out.println();
    }
    
    // ========== 遍历操作（非递归） ==========
    
    /**
     * 前序遍历（非递归）：使用栈
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(h)
     */
    public void preOrderNonRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.data + " ");
            
            // 先压右子节点，再压左子节点（栈是后进先出）
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        System.out.println();
    }
    
    /**
     * 中序遍历（非递归）：使用栈
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(h)
     */
    public void inOrderNonRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        
        while (current != null || !stack.isEmpty()) {
            // 一路向左，压入所有左子节点
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            // 弹出节点并访问
            if (!stack.isEmpty()) {
                current = stack.pop();
                System.out.print(current.data + " ");
                current = current.right;  // 转向右子树
            }
        }
        System.out.println();
    }
    
    // ========== 基本操作 ==========
    
    /**
     * 求二叉树的节点数
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
        return 1 + countNodes(node.left) + countNodes(node.right);
    }
    
    /**
     * 求二叉树的深度/高度
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
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
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
        if (node.left == null && node.right == null) {
            return 1;  // 叶子节点
        }
        return countLeaves(node.left) + countLeaves(node.right);
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
            return node;
        }
        
        // 在左子树中查找
        TreeNode left = search(node.left, value);
        if (left != null) {
            return left;
        }
        
        // 在右子树中查找
        return search(node.right, value);
    }
    
    /**
     * 求二叉树的宽度（最大层节点数）
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(w)
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
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        
        return maxWidth;
    }
    
    /**
     * 判断是否是完全二叉树
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(w)
     */
    public boolean isComplete(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = false;  // 标记是否遇到了空节点
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            
            if (node == null) {
                flag = true;  // 遇到空节点
            } else {
                if (flag) {
                    return false;  // 空节点后不应该有非空节点
                }
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        
        return true;
    }
    
    /**
     * 打印树的信息
     */
    public void printInfo() {
        if (root == null) {
            System.out.println("二叉树为空！");
            return;
        }
        
        System.out.println("========== 二叉树信息 ==========");
        System.out.println("节点总数：" + countNodes(root));
        System.out.println("树的深度：" + height(root));
        System.out.println("叶子节点数：" + countLeaves(root));
        System.out.println("树的宽度：" + width(root));
        System.out.println("是否完全二叉树：" + isComplete(root));
        System.out.println("================================");
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("========== 二叉树测试 ==========\n");
        
        // 手动创建二叉树
        /*
                1
               / \
              2   3
             / \
            4   5
        */
        BinaryTree tree = new BinaryTree(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);
        
        System.out.println("创建二叉树成功！");
        System.out.println("树的结构：");
        System.out.println("        1");
        System.out.println("       / \\");
        System.out.println("      2   3");
        System.out.println("     / \\");
        System.out.println("    4   5");
        System.out.println();
        
        // 测试遍历（递归）
        System.out.println("【测试1：四种遍历方式（递归）】");
        System.out.print("前序遍历：");
        tree.preOrder(tree.root);
        System.out.println("  （根 → 左 → 右）");
        
        System.out.print("中序遍历：");
        tree.inOrder(tree.root);
        System.out.println("  （左 → 根 → 右）");
        
        System.out.print("后序遍历：");
        tree.postOrder(tree.root);
        System.out.println("  （左 → 右 → 根）");
        
        System.out.print("层次遍历：");
        tree.levelOrder(tree.root);
        System.out.println("  （逐层访问）");
        System.out.println();
        
        // 测试遍历（非递归）
        System.out.println("【测试2：遍历方式（非递归）】");
        System.out.print("前序遍历（非递归）：");
        tree.preOrderNonRecursive(tree.root);
        
        System.out.print("中序遍历（非递归）：");
        tree.inOrderNonRecursive(tree.root);
        System.out.println();
        
        // 测试基本操作
        System.out.println("【测试3：基本操作】");
        System.out.println("节点总数：" + tree.countNodes(tree.root));
        System.out.println("树的深度：" + tree.height(tree.root));
        System.out.println("叶子节点数：" + tree.countLeaves(tree.root));
        System.out.println("树的宽度：" + tree.width(tree.root));
        System.out.println();
        
        // 测试查找
        System.out.println("【测试4：查找节点】");
        TreeNode found = tree.search(tree.root, 4);
        if (found != null) {
            System.out.println("找到节点：" + found.data);
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
        
        // 测试是否是完全二叉树
        System.out.println("【测试5：是否是完全二叉树】");
        System.out.println("是否完全二叉树：" + tree.isComplete(tree.root));
        System.out.println();
        
        // 打印树的详细信息
        System.out.println("【测试6：树的详细信息】");
        tree.printInfo();
        System.out.println();
        
        // 创建另一个二叉树（完全二叉树）
        /*
                1
               / \
              2   3
             / \ /
            4  5 6
        */
        System.out.println("【测试7：完全二叉树测试】");
        BinaryTree completeTree = new BinaryTree(1);
        completeTree.root.left = new TreeNode(2);
        completeTree.root.right = new TreeNode(3);
        completeTree.root.left.left = new TreeNode(4);
        completeTree.root.left.right = new TreeNode(5);
        completeTree.root.right.left = new TreeNode(6);
        
        System.out.println("树的结构：");
        System.out.println("        1");
        System.out.println("       / \\");
        System.out.println("      2   3");
        System.out.println("     / \\ /");
        System.out.println("    4  5 6");
        System.out.print("层次遍历：");
        completeTree.levelOrder(completeTree.root);
        System.out.println("是否完全二叉树：" + completeTree.isComplete(completeTree.root));
        System.out.println();
        
        System.out.println("========== 二叉树测试完成 ==========");
    }
}

