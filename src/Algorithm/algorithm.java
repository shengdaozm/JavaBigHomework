package Algorithm;

public class algorithm {
    public Graph g;//存在建图的信息，包含边集数组与矩阵两种格式
    public int n,m;//算法调用的节点数和边数
    public int cnt;//计数器
    public Kruskal k;
    public Prim p;

    public algorithm() {
        k=new Kruskal();
        p=new Prim();
    }
    public algorithm(Graph g) {
        k=new Kruskal();
        p=new Prim();
        this.g=g;
    }
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
