package Algorithm;

public class algorithm {
    Graph g;//存在建图的信息，包含边集数组与矩阵两种格式
    public int n,m;//算法调用的节点数和边数
    public int cnt;//计数器
    Kruskal k;
    Prim p;

    public void algo_init(int _n,int _m) {
        cnt=1;
        n=_n;
        m=_m;
        g=new Graph(n,m);
        k=new Kruskal();
        p=new Prim();
    }

    public void getegde(int u,int v,int w) {
        g.init(u,v,w);
    }
}
