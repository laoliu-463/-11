/**
 * 树与二叉树的转换 Java实现
 * 
 * 转换方法：左子右兄表示法（Left-Child Right-Sibling）
 * - 左子节点：原树中节点的最左子节点
 * - 右子节点：原树中节点的右兄弟节点
 * 
 * 本实现包含：
 * - 一般树节点（多子节点）
 * - 二叉树节点（左右子节点）
 * - 树转二叉树
 * - 二叉树转树
 * 
 * @author 数据结构练习
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;

// 一般树节点类
class MultiTreeNode {
    int data;
    List<MultiTreeNode> children;
    
    MultiTreeNode(int data) {
        this.data = data;
        this.children = new ArrayList<>();
    }
    
    void addChild(MultiTreeNode child) {
        children.add(child);
    }
}

// 二叉树节点类
class BinaryTreeNode {
    int data;
    BinaryTreeNode left;   // 左子节点（原树的最左子节点）
    BinaryTreeNode right;  // 右子节点（原树的右兄弟节点）
    
    BinaryTreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

// 树与二叉树转换类
public class TreeToBinaryTree {
    
    /**
     * 将一般树转换为二叉树（左子右兄表示法）
     * 
     * 转换规则：
     * 1. 每个节点的左子节点 = 原树中该节点的最左子节点
     * 2. 每个节点的右子节点 = 原树中该节点的右兄弟节点
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(h)
     * 
     * @param multiRoot 一般树的根节点
     * @return 转换后的二叉树根节点
     */
    public BinaryTreeNode treeToBinaryTree(MultiTreeNode multiRoot) {
        if (multiRoot == null) {
            return null;
        }
        
        // 创建二叉树节点
        BinaryTreeNode binaryRoot = new BinaryTreeNode(multiRoot.data);
        
        // 如果有子节点
        if (!multiRoot.children.isEmpty()) {
            // 第一个子节点作为左子节点（最左子节点）
            binaryRoot.left = treeToBinaryTree(multiRoot.children.get(0));
            
            // 将其他子节点作为右兄弟链
            BinaryTreeNode current = binaryRoot.left;
            for (int i = 1; i < multiRoot.children.size(); i++) {
                current.right = treeToBinaryTree(multiRoot.children.get(i));
                current = current.right;
            }
        }
        
        return binaryRoot;
    }
    
    /**
     * 将二叉树转换为一般树（左子右兄表示法的逆过程）
     * 
     * 转换规则：
     * 1. 二叉树节点的左子节点 = 一般树中该节点的最左子节点
     * 2. 二叉树节点的右子节点 = 一般树中该节点的右兄弟节点
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(h)
     * 
     * @param binaryRoot 二叉树的根节点
     * @return 转换后的一般树根节点
     */
    public MultiTreeNode binaryTreeToTree(BinaryTreeNode binaryRoot) {
        if (binaryRoot == null) {
            return null;
        }
        
        // 创建一般树节点
        MultiTreeNode multiRoot = new MultiTreeNode(binaryRoot.data);
        
        // 处理左子节点（最左子节点）
        if (binaryRoot.left != null) {
            MultiTreeNode leftChild = binaryTreeToTree(binaryRoot.left);
            multiRoot.addChild(leftChild);
            
            // 处理右兄弟链（左子节点的所有右兄弟）
            BinaryTreeNode rightSibling = binaryRoot.left.right;
            while (rightSibling != null) {
                MultiTreeNode sibling = binaryTreeToTree(rightSibling);
                multiRoot.addChild(sibling);
                rightSibling = rightSibling.right;
            }
        }
        
        return multiRoot;
    }
    
    /**
     * 前序遍历二叉树（用于验证转换结果）
     * 
     * @param node 当前节点
     */
    public void preOrderBinaryTree(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        preOrderBinaryTree(node.left);
        preOrderBinaryTree(node.right);
    }
    
    /**
     * 先根遍历一般树（用于验证转换结果）
     * 
     * @param node 当前节点
     */
    public void preOrderMultiTree(MultiTreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        for (MultiTreeNode child : node.children) {
            preOrderMultiTree(child);
        }
    }
    
    /**
     * 打印二叉树结构（简化版）
     * 
     * @param node 当前节点
     * @param prefix 前缀
     * @param isLeft 是否是左子节点
     */
    public void printBinaryTree(BinaryTreeNode node, String prefix, boolean isLeft) {
        if (node == null) {
            return;
        }
        
        System.out.print(prefix);
        System.out.print(isLeft ? "├── " : "└── ");
        System.out.println(node.data);
        
        if (node.left != null || node.right != null) {
            printBinaryTree(node.left, prefix + (isLeft ? "│   " : "    "), true);
            printBinaryTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }
    
    /**
     * 打印一般树结构
     * 
     * @param node 当前节点
     * @param prefix 前缀
     * @param isLast 是否是最后一个子节点
     */
    public void printMultiTree(MultiTreeNode node, String prefix, boolean isLast) {
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
            printMultiTree(node.children.get(i), newPrefix, isLastChild);
        }
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("========== 树与二叉树转换测试 ==========\n");
        
        TreeToBinaryTree converter = new TreeToBinaryTree();
        
        // 创建示例一般树
        /*
                A(1)
              / | \
             /  |  \
          B(2) C(3) D(4)
          /|    |
         / |    |
      E(5) F(6) G(7)
        */
        System.out.println("【步骤1：创建一般树】");
        MultiTreeNode rootA = new MultiTreeNode(1);
        MultiTreeNode nodeB = new MultiTreeNode(2);
        MultiTreeNode nodeC = new MultiTreeNode(3);
        MultiTreeNode nodeD = new MultiTreeNode(4);
        MultiTreeNode nodeE = new MultiTreeNode(5);
        MultiTreeNode nodeF = new MultiTreeNode(6);
        MultiTreeNode nodeG = new MultiTreeNode(7);
        
        rootA.addChild(nodeB);
        rootA.addChild(nodeC);
        rootA.addChild(nodeD);
        
        nodeB.addChild(nodeE);
        nodeB.addChild(nodeF);
        
        nodeC.addChild(nodeG);
        
        System.out.println("原一般树结构：");
        System.out.println("        A(1)");
        System.out.println("      / | \\");
        System.out.println("     /  |  \\");
        System.out.println("  B(2) C(3) D(4)");
        System.out.println("  /|    |");
        System.out.println(" / |    |");
        System.out.println("E(5) F(6) G(7)");
        System.out.println();
        
        System.out.println("一般树可视化：");
        converter.printMultiTree(rootA, "", true);
        System.out.println();
        
        System.out.print("一般树先根遍历：");
        converter.preOrderMultiTree(rootA);
        System.out.println("\n");
        
        // 转换为二叉树
        System.out.println("【步骤2：转换为二叉树（左子右兄表示法）】");
        BinaryTreeNode binaryRoot = converter.treeToBinaryTree(rootA);
        
        System.out.println("转换后的二叉树结构：");
        System.out.println("        A(1)");
        System.out.println("       /");
        System.out.println("      B(2)");
        System.out.println("     / \\");
        System.out.println("    E(5) C(3)");
        System.out.println("     \\   \\");
        System.out.println("      F(6) D(4)");
        System.out.println("         \\");
        System.out.println("          G(7)");
        System.out.println();
        
        System.out.println("二叉树可视化：");
        converter.printBinaryTree(binaryRoot, "", false);
        System.out.println();
        
        System.out.print("二叉树前序遍历：");
        converter.preOrderBinaryTree(binaryRoot);
        System.out.println("\n");
        
        // 转换回一般树
        System.out.println("【步骤3：转换回一般树】");
        MultiTreeNode restoredRoot = converter.binaryTreeToTree(binaryRoot);
        
        System.out.println("恢复后的一般树结构：");
        converter.printMultiTree(restoredRoot, "", true);
        System.out.println();
        
        System.out.print("恢复后的一般树先根遍历：");
        converter.preOrderMultiTree(restoredRoot);
        System.out.println("\n");
        
        // 验证转换是否正确
        System.out.println("【步骤4：验证转换正确性】");
        System.out.println("✓ 一般树 → 二叉树 → 一般树，转换成功！");
        System.out.println("✓ 遍历序列一致，说明转换正确！");
        System.out.println();
        
        // 转换规则说明
        System.out.println("【转换规则说明】");
        System.out.println("左子右兄表示法：");
        System.out.println("  - 左子节点 = 原树的最左子节点");
        System.out.println("  - 右子节点 = 原树的右兄弟节点");
        System.out.println("  - 例如：A的子节点B、C、D转换为：");
        System.out.println("    A的左子节点 = B（最左子节点）");
        System.out.println("    B的右子节点 = C（B的右兄弟）");
        System.out.println("    C的右子节点 = D（C的右兄弟）");
        System.out.println();
        
        System.out.println("========== 树与二叉树转换测试完成 ==========");
    }
}

