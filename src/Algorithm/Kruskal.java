package Algorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 执行Kruskal算法
 */
public class Kruskal {
    UnionFind uf;

    /**
     * Kruskal算法计算过程
     * @param g 输入的图的信息
     * @return 生成的最小边
     */
    public int[][] mit_kruskal(Graph g) {
        int ans[][] = new int[g.n - 1][2];
        uf = new UnionFind(g.n);
        Arrays.sort(g.e, new Comparator<Edge>() {
            public int compare(Edge e1, Edge e2) {
                return e1.w - e2.w;
            }
        });
        int cnt = 0;
        for (int i = 0; i < g.m; ++i) {
            if (uf.unite(g.e[i].u, g.e[i].v)) {
                ans[cnt][0] = g.e[i].u;
                ans[cnt][1] = g.e[i].v;
                ++cnt;
                if (cnt == g.n - 1) return ans;
            }
        }
        return ans;
    }

    /**
     * 将最小生成树的边改成展示的字符串类型
     * @param g 输入图的基本信息
     * @return 生成树的最小边的字符串表示
     */
    public Edge[] changed_mit_kruskal(Graph g) {
        Edge[] edges=new Edge[g.getN()-1];
        int ans[][]=mit_kruskal(g);
        for(int i=0;i<g.getN()-1;++i) {
            edges[i]=new Edge("V"+ans[i][0],"V"+ans[i][1],g.c[ans[i][0]][ans[i][1]]);
        }
        return edges;
    }
}