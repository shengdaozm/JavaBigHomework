package Algorithm;

public class Edge {
    //存在每一个边的情况
    public int u,v,w,val;
    public String start,end;

    public Edge(String start,String end,int val) {
        this.start=start;
        this.end=end;
        this.val=val;
    }
    public Edge(int _u,int _v,int _w) {
        u=_u;
        v=_v;
        w=_w;
    }

}
