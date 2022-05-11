package Algorithm;

public class Prim {
    /**
     * prim算法计算过程
     * @param g 输入的图的信息
     * @return 生成的最小边
     */
    public final int inf=0x3f3f3f3f;
    int [][] prim(Graph g) {
        int ans[][]=new int[g.n-1][2];
        Boolean s[] = new Boolean[g.n]; //标记数组
        int lowcost[] = new int[g.n]; //每个节点的最小花费
        int closeset[] = new int[g.n]; //再最小生成树中与每个节点相连的那个节点
        int cnt = 0;

        //算法部分
        int pre = 0;
        s[0] = true;
        lowcost[0] = 0;
        for (int i = 1; i < g.n; ++i) {
            lowcost[i] = g.c[0][i];
            closeset[i] = 0;
            s[i] = false;
        }
        for (int i = 1; i < g.n; ++i) {
            int temp = inf;
            int t = 0;
            for (int j = 0; j < g.n; ++j) {
                if (!s[j] && lowcost[j] < temp) {
                    t = j;
                    temp = lowcost[j];
                }
            }
            if (t == 0)
                return ans;
            s[t] = true;
            ans[cnt][0] = pre;
            ans[cnt][1] = t;
            ++cnt;
            pre = t;
            for (int j = 0; j < g.n; ++j) {
                if (!s[j] && g.c[t][j] < lowcost[j]) {
                    lowcost[j] = g.c[t][j];
                    closeset[j] = t;
                }
            }
        }
        return ans;
    }
}
