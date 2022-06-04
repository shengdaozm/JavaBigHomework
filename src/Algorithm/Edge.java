package Algorithm;

/**
 * 储存图的边集数组
 */
public class Edge {
    //存在每一个边的情况
    public int u,v,w,val;
    public String start,end;

    /**
     * 将边的信息以字符串的形式输入
     * @param start 输入边的源点
     * @param end 输入边的终点
     * @param val 输入边的权值
     */
    public Edge(String start,String end,int val) {
        this.start=start;
        this.end=end;
        this.val=val;
    }

    /**
     * 缓存边的信息，充当边集数组的作用
     * @param _u 输入边的源点
     * @param _v 输入边的终点
     * @param _w 输入便的权值
     */
    public Edge(int _u,int _v,int _w) {
        u=_u;
        v=_v;
        w=_w;
    }

}
