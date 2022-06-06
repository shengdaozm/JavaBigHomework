package Algorithm;

/**
 *算法执行的总类，用于存放图的数据结构与调用两个生成算法
 */
public class algorithm {
    public Graph g;//存在建图的信息，包含边集数组与矩阵两种格式
    public int n,m;//算法调用的节点数和边数
    public int cnt;//计数器
    public Kruskal k;//Kruskal执行
    public Prim p;//prim算法执行

    /**
     * algorithm类的无参数初始化
     */
    public algorithm() {
        k=new Kruskal();
        p=new Prim();
    }

    /**
     * algorithm类的有参数初始化
     * @param g 图的数据结构
     */
    public algorithm(Graph g) {
        k=new Kruskal();
        p=new Prim();
        this.g=g;
    }

    /**
     * algorithm类的对象初始化，申请各类空间，准备输入边与点的数据
     * @param _n 输入建图的节点数
     * @param _m 输入建图的边数
     */
    public void algo_init(int _n,int _m) {
        cnt=1;
        n=_n;
        m=_m;
        g=new Graph(n,m);
        k=new Kruskal();
        p=new Prim();
    }

    /**
     * algorithm类的加边的方法
     * @param u 输入边的源点
     * @param v 输入边的终点
     * @param w 输入边的权值
     */
    public void getegde(int u,int v,int w) {
        g.init(u,v,w);
    }
}
