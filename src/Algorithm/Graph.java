package Algorithm;

public class Graph {
    public final int inf=0x3f3f3f3f;
    public Edge e[];
    public int c[][];
    public int u,v,w;
    public int n,m,pos;

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
    }

    void init(int u,int v,int w) {
        c[u][v]=Math.min(c[u][v],w);
        e[pos]=new Edge(u,v,w);
        ++pos;
    }
}
