package Algorithm;

public class Graph {
    public Edge e[];
    public int c[][];
    public int u,v,w;
    public int n,m;

    Graph(int n,int m) {
        this.n=n;
        this.m=m;
        e=new Edge[m];
        c=new int[n][n];
    }
    void init(int u,int v,int w) {

    }
}
