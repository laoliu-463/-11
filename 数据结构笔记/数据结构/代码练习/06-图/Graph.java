/**
 * 图的实现（邻接表表示）
 * 
 * 包含：图的创建、DFS、BFS、最短路径等
 * 
 * @author 数据结构练习
 * @version 1.0
 */
import java.util.*;

public class Graph {
    private int V;  // 顶点数
    private LinkedList<Integer>[] adj;  // 邻接表
    
    /**
     * 构造函数：创建有V个顶点的图
     */
    @SuppressWarnings("unchecked")
    public Graph(int v) {
        V = v;
        adj = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }
    
    /**
     * 添加边（无向图）
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }
    
    /**
     * 添加有向边
     */
    public void addDirectedEdge(int v, int w) {
        adj[v].add(w);
    }
    
    /**
     * DFS（深度优先搜索）
     * 时间复杂度：O(V + E)
     */
    public void dfs(int start) {
        boolean[] visited = new boolean[V];
        System.out.print("DFS遍历：");
        dfsUtil(start, visited);
        System.out.println();
    }
    
    private void dfsUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");
        
        for (int w : adj[v]) {
            if (!visited[w]) {
                dfsUtil(w, visited);
            }
        }
    }
    
    /**
     * BFS（广度优先搜索）
     * 时间复杂度：O(V + E)
     */
    public void bfs(int start) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        
        visited[start] = true;
        queue.offer(start);
        
        System.out.print("BFS遍历：");
        while (!queue.isEmpty()) {
            int v = queue.poll();
            System.out.print(v + " ");
            
            for (int w : adj[v]) {
                if (!visited[w]) {
                    visited[w] = true;
                    queue.offer(w);
                }
            }
        }
        System.out.println();
    }
    
    /**
     * 最短路径（BFS实现，适用于无权图）
     * @return 从start到各顶点的最短距离
     */
    public int[] shortestPath(int start) {
        int[] dist = new int[V];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();
        
        dist[start] = 0;
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            int v = queue.poll();
            
            for (int w : adj[v]) {
                if (dist[w] == -1) {
                    dist[w] = dist[v] + 1;
                    queue.offer(w);
                }
            }
        }
        
        return dist;
    }
    
    /**
     * 打印图
     */
    public void printGraph() {
        System.out.println("图的邻接表表示：");
        for (int i = 0; i < V; i++) {
            System.out.print("顶点" + i + ": ");
            for (int w : adj[i]) {
                System.out.print(w + " ");
            }
            System.out.println();
        }
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        Graph g = new Graph(5);
        
        // 添加边
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 4);
        
        g.printGraph();
        System.out.println();
        
        // DFS遍历
        g.dfs(0);
        
        // BFS遍历
        g.bfs(0);
        
        // 最短路径
        int[] dist = g.shortestPath(0);
        System.out.print("从顶点0到各顶点的最短距离：");
        for (int i = 0; i < dist.length; i++) {
            System.out.print("到" + i + ":" + dist[i] + " ");
        }
        System.out.println();
    }
}



