package Algorithm;

import java.util.Arrays;
import java.util.Comparator;

public class Kruskal {
    /**
     * Kruskal算法计算过程
     *
     * @param g 输入的图的信息
     * @return 生成的最小边
     */
    UnionFind uf;

    int[][] kruskal(Graph g) {
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
}