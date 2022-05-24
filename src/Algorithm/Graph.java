package Algorithm;

import java.util.*;

public class Graph {
    private final int inf=Integer.MIN_VALUE;
    public Edge e[];
    public int c[][];
    public int u,v,w;
    public int n,m,pos;
    public List<String> vex;

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

    void init(int u,int v,int w) {
        c[u][v]=Math.min(c[u][v],w);
        e[pos]=new Edge(u,v,w);
        ++pos;
    }

    public void produce_vex() {
        for(int i=0;i<n;++i) {
        vex.add("V"+i);
        }
    }
    //获得节点数
    public int getN() {
        return n;
    }

    //获得边的数目
    public int getM() {
        return m;
    }

    //获得邻接矩阵
    public int[][] getC() {
        return c;
    }

    //获得边集数组
    public Edge[] getE() {
        return e;
    }

    //获取节点数组
    public List<String> getVex() { return vex;}
}
