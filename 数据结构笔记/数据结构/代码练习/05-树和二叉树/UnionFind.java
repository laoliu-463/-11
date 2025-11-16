/**
 * 并查集（Union-Find）Java实现
 * 
 * 并查集：一种树形数据结构，用于处理不交集的合并及查询问题
 * 核心操作：Find（查找）、Union（合并）
 * 
 * 本实现包含：
 * - 基础并查集操作
 * - 路径压缩优化
 * - 按秩合并优化
 * - 连通性判断
 * 
 * @author 数据结构练习
 * @version 1.0
 */

public class UnionFind {
    private int[] parent;  // parent[i]表示i的父节点
    private int[] rank;    // rank[i]表示以i为根的树的高度（用于按秩合并）
    private int count;     // 连通分量的数量
    
    /**
     * 构造函数：初始化并查集
     * 
     * @param n 元素个数
     */
    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        count = n;
        
        // 初始化：每个元素的父节点是自己
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }
    
    /**
     * 查找元素x所属集合的根节点（带路径压缩）
     * 
     * 时间复杂度：O(α(n))，α为阿克曼函数的反函数，接近O(1)
     * 
     * @param x 元素
     * @return 根节点
     */
    public int find(int x) {
        if (parent[x] != x) {
            // 路径压缩：将路径上的所有节点直接连接到根节点
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    /**
     * 合并元素x和y所在的集合（按秩合并）
     * 
     * 时间复杂度：O(α(n))
     * 
     * @param x 元素x
     * @param y 元素y
     */
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX != rootY) {
            // 按秩合并：将高度小的树合并到高度大的树下
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                // 高度相同，任意选择一个作为根，高度+1
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            count--;  // 连通分量减1
        }
    }
    
    /**
     * 判断元素x和y是否在同一个集合中
     * 
     * @param x 元素x
     * @param y 元素y
     * @return true表示连通，false表示不连通
     */
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
    
    /**
     * 获取连通分量的数量
     * 
     * @return 连通分量数量
     */
    public int getCount() {
        return count;
    }
    
    /**
     * 打印并查集的状态（用于调试）
     */
    public void print() {
        System.out.print("Parent: ");
        for (int i = 0; i < parent.length; i++) {
            System.out.print(parent[i] + " ");
        }
        System.out.println();
        
        System.out.print("Rank:   ");
        for (int i = 0; i < rank.length; i++) {
            System.out.print(rank[i] + " ");
        }
        System.out.println();
        
        System.out.println("连通分量数量: " + count);
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("========== 并查集测试 ==========\n");
        
        // 创建并查集（5个元素）
        UnionFind uf = new UnionFind(5);
        System.out.println("【测试1：初始化】");
        uf.print();
        System.out.println();
        
        // 测试合并
        System.out.println("【测试2：合并操作】");
        System.out.println("合并 0 和 1");
        uf.union(0, 1);
        uf.print();
        System.out.println("0和1是否连通: " + uf.connected(0, 1));
        System.out.println();
        
        System.out.println("合并 2 和 3");
        uf.union(2, 3);
        uf.print();
        System.out.println("2和3是否连通: " + uf.connected(2, 3));
        System.out.println();
        
        System.out.println("合并 1 和 2");
        uf.union(1, 2);
        uf.print();
        System.out.println("0和3是否连通: " + uf.connected(0, 3));
        System.out.println();
        
        // 测试连通性
        System.out.println("【测试3：连通性判断】");
        System.out.println("0和4是否连通: " + uf.connected(0, 4));
        System.out.println("1和3是否连通: " + uf.connected(1, 3));
        System.out.println();
        
        // 测试连通分量数量
        System.out.println("【测试4：连通分量数量】");
        System.out.println("当前连通分量数量: " + uf.getCount());
        System.out.println();
        
        // 朋友圈问题示例
        System.out.println("【测试5：朋友圈问题】");
        UnionFind friends = new UnionFind(6);
        // 朋友关系：(0,1), (2,3), (4,5), (1,4)
        friends.union(0, 1);
        friends.union(2, 3);
        friends.union(4, 5);
        friends.union(1, 4);
        
        System.out.println("朋友关系：(0,1), (2,3), (4,5), (1,4)");
        System.out.println("朋友圈数量: " + friends.getCount());
        System.out.println("0和5是否在同一朋友圈: " + friends.connected(0, 5));
        System.out.println("2和4是否在同一朋友圈: " + friends.connected(2, 4));
        
        System.out.println("\n========== 并查集测试完成 ==========");
    }
}

