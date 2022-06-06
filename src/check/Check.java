package check;


import Algorithm.Edge;
import Algorithm.Graph;
import Algorithm.UnionFind;
import Algorithm.Edge;

/**
 * 检查图是否为连通图，为最小生成树的生成左准备
 */
public class Check {
    private Graph g;
    private UnionFind uf;

    /**
     * check的初始化
     * @param g 存放图的数据
     */
    public Check(Graph g) {
        this.g=g;
        uf=new UnionFind(g.n);
    }

    /**
     * 并查集判断图是否连通即可
     * @return 图是否连通
     */
    public Boolean ischeck() {
        if(g.getN()>6) return false;
        Edge[] edgedate=g.getE();
        for (int i = 0; i < g.m; ++i) uf.unite(g.e[i].u, g.e[i].v);
        return uf.getSetCount()==1;
    }
}
