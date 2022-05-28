package check;


import Algorithm.Edge;
import Algorithm.Graph;
import Algorithm.UnionFind;
import Algorithm.Edge;

public class Check {
    private Graph g;
    private UnionFind uf;
    public Check(Graph g) {
        this.g=g;
        uf=new UnionFind(g.n);
    }

    //并查集判断图是否连通即可
    public Boolean check() {
        Edge[] edgedate=g.getE();
        for (int i = 0; i < g.m; ++i) uf.unite(g.e[i].u, g.e[i].v);
        return uf.getSetCount()==1;
    }
}
