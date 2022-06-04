package Algorithm;

/**
 * 并查集
 */
public class UnionFind {
    private int parent[];
    private int size[];
    private int n;
    private int setCount;

    /**
     * 并查集的初始化
     * @param _n 并查集的初始节点数
     */
    public UnionFind(int _n) {
        parent = new int[_n];
        size = new int[_n];
        n = _n;
        setCount = _n;
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * 递归查找x节点的父节点
     * @param x 待查找节点
     * @return x的父节点
     */
    int findset(int x) {
        if (parent[x] == x) return x;
        else {
            parent[x] = findset(parent[x]);
            return parent[x];
        }
    }

    /**
     * 将x与y连接起来
     * @param x 节点x
     * @param y 节点y
     * @return 节点x与节点y是否已经连接起来
     */
    public Boolean unite(int x, int y) {
        x = findset(x);
        y = findset(y);
        if (x == y) return false;
        if (size[x] < size[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        parent[y] = x;
        size[x] += size[y];
        --setCount;
        return true;
    }

    /**
     * 获取连通分支数
     * @return 连通分支数
     */
    public int getSetCount() {return setCount;}

    /**
     * 判断x与y是否连通
     * @param x 节点x
     * @param y 节点y
     * @return x与y是否连通
     */
    Boolean connected(int x, int y) {
        return findset(x) == findset(y);
    }
}
