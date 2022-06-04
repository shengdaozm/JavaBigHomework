package Algorithm;

import java.util.*;

/**
 * 存放图的类，内部设置有边集数组，邻接表
 */
public class Graph {
    private final int inf=Integer.MAX_VALUE;
    public Edge e[];
    public int c[][];
    public int u,v,w;
    public int n,m,pos;
    public List<String> vex;

    /**
     *  graph的初始化，为建图做准备
     * @param n 建图的点的个数
     * @param m 建图的边的数目
     */
    Graph(int n,int m) {
        this.n=n;
        this.m=m;
        pos=0;
        e=new Edge[m];
        c=new int[n][n];
        for(int i=0;i<n;++i)
            for(int j=0;j<n;++j) {
                c[i][j]=inf;
            }
        vex = new ArrayList<>();
    }

    /**
     * 将输入的边存入
     * @param u 输入边的源点
     * @param v 输入边的终点
     * @param w 输入边的权值
     */
    void init(int u,int v,int w) {
        c[u][v]=Math.min(c[u][v],w);
        c[v][u]=c[u][v];
        e[pos]=new Edge(u,v,w);
        ++pos;
    }

    /**
     * 将点的名称转化成字符串的形式
     */
    public void produce_vex() {
        for(int i=0;i<n;++i) {
        vex.add("V"+i);
        }
    }

    /**
     * 获得节点数
     * @return 图的节点数
     */
    public int getN() { return n; }

    /**
     * 获得图的边数
     * @return 图的边数
     */
    public int getM() {
        return m;
    }

    /**
     * 获得邻接矩阵
     * @return 邻接数组
     */
    public int[][] getC() {
        return c;
    }

    /**
     * 获得边集数组
     * @return 边集数组
     */
    public Edge[] getE() {
        return e;
    }

    /**
     * 获取节点名称数组
     * @return 节点名称数组
     */
    public List<String> getVex() { return vex;}
}
