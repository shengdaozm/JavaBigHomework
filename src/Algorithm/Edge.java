package Algorithm;

public class Edge {
    //存在每一个边的情况
    public int u,v,w,val;
    public String start,end;

    //存放最小边
//    public Edge(String start,String end) {
//        this.start=start;
//        this.end=end;
//        //this.val=val;
//    }

    //字符串类型的边集数组
    public Edge(String start,String end,int val) {
        this.start=start;
        this.end=end;
        this.val=val;
    }

    //储存边集数组
    public Edge(int _u,int _v,int _w) {
        u=_u;
        v=_v;
        w=_w;
    }

}
