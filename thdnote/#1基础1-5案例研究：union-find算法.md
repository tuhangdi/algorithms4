# 1.5 案例研究：union-find算法
- 优秀的算法因为能够解决实际问题而变得更为重要；
- 高效算法的代码也可以很简单；
- 理解某个实现的性能特点是一项有趣而令人满足的挑战；
- 在解决同一个问题的多种算法之间进行选择时，科学方法是一种重要的工具；
- 迭代式改进能够让算法的效率越来越高。
## 1.5.1 动态连接性问题
- 问题的输入是一列整数对，其中每个整数都表示一个某种类型的对象，一对整数pq可以被理解为“p和q是相连的”，我们假设相连是一种对等的关系。对等关系能够将对象分为多个等价类，在这里，当且仅当两个对象相连时它们才属于同一个等价类。我们的目标是编写一个程序来过滤掉序列中所有无意义的整数对（两个整数均来自于同一个等价类中）。换句话说，当程序从输入中读取了证书对p q时，如果已知的所有整数对都不能说明p和q相连的，那么则将这一对整数写入到输出中。如果已知的数据可以说明p 和q是相连的，那么程序应该忽略p q继续处理输入中的下一对整数。
  ![图1.5.1](/assets/图1.5.1.png)
- 该问题可应用于：
  - 网络
  - 变量名等价性
  - 数据集合
- 设计一份API封装所需的基本操作：初始化、连接两个触点、判断包含某个触点的分量、判断两个触点是否存在于同一个分量之中以及返回所有分量的数量。
  ![表1.5.1](/assets/表1.5.1.png)
  ```java
  public class UF
  {
    private int[] id;//分量id（以触点作为索引）
    private int count; //分量数量
    public UF(int N)
    {//初始化分量id数组
      count = N;
      id = new int[N];
      for(int i=0;i < N;i++)
        id[i] = i;
    }
    public int count()
    { return count;}
    public boolean connected(int p, int q)
    { renturn find(p) == find(q); }
    public int find(int p)//见quick-find
    public void union(int p, int q)//见quick-union,加权quick-union
    public static void main(String[] args)
    {//解决由StdIn得到的动态连通性问题
      int N = StdIn.readInt() //读取触点数量
      UF N = new UF(N); //初始化N个分量
      while (!StdIn.isEmpty())
      {
        int p = StdIn.readInt();
        int q = StdIn.readInt();//读取整数对
        if (uf.connected(p, q)) continue;//如果已经连通则忽略
        uf.union(p, q);//归并分量
        StdOut.println(p + " " + q);//打印连接
      }
      StdOut.println(uf.count() + "components");
    }
  }
  ```
## 1.5.2 实现（均根据以触点为索引的id[]数组来确定两个触点是否存在于相同的连通分量中）
- quick-find算法：保证当且仅当id[p]等于id[q]时p和q是连通的。换句话说，在同一个连通分量重的所有触点在id[]中的值必须全部相同。
  ```java
  public int find(int p)
  { return id[p]; }
  public void union(int p, int q)
  {//将p和q归并到相同的分量中
    int pID = find(p);
    int qID = find(q);

    //如果p和q已经在相同的分量之中则不需要采取任何行动
    if (pID == qID) return;

    //将p的分量重命名为q的名称
    for (int i = 0;i < id.length; i++)
        if (id[i] == pID) id[i] = qID;
    count--;
  }
  ```
  ![表1.5.2](/assets/表1.5.2.png)

  find()操作的速度显然是很快的，因为它只需要访问id[]数组一次。但quick-find算法一般无法处理大型问题，因为对于每一对输入union()都需要扫描整个id[]数组。
- quick-union算法：
  - 每个触点所对应的id[]元素都是同一个分量中的另一个触点的名称（也可能是它自己）——我们将这种联系称为链接
  - 在实现find()方法时，我们从给定的触点开始，由它的链接得到另一个触点，再由这个触点的链接到达第三个触点，如此继续指导到达一个根触点，即链接指向自己的触点。
  - 当且仅当分别由两个触点开始的这个过程到达同一个根触点时它们存在于同一个连通分量中。
  ```java
  private int find(int p)
  {//找出分量的名称
    while(p != id[p]) p = id[p];
    return p;
  }
  public void union(int p, int q)
  {//将p和q的根节点统一
    int pRoot = find(p);
    int qRoot = find(q);
    if (pRoot == qRoot) return;
    id[pRoot] = qRoot;
    count--;
  }
  ```
  ![图1.5.4](/assets/图1.5.4.png)

- 加权 quick-union算法：记录每一棵树的大小并总是将较小的树连接到较大的树上。
```java
public class UF
{
  private int[] id;//父链接数组（由触点索引）
  private int[] sz;//（有触点索引的）各个根节点所对应的分量的大小
  private int count; //连通分量的数量
  public WeightedQuickUnionUF(int N)
  {
    count = N;
    id = new int[N];
    for(int i=0;i < N;i++)
      id[i] = i;
    sz = new int[N];
    for(int i = 0; i < N; i++) sz[i] = 1;
  }
  public int count()
  { return count;}
  public boolean connected(int p, int q)
  { renturn find(p) == find(q); }
  public int find(int p)
  {//跟随链接找到根节点
    while(p != id[p]) p = id[p];
    return p;
  }
  public void union(int p, int q)
  {
    int i = find(p);
    int j = find(q);
    if(i == j) return;
    //将小树的根节点连接到大树的根节点
    if (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i];}
    else{id[j] = i;sz[i] += sz[j];}
    count--;
  }
}
```
- 最优算法
  ![表1.5.3](/assets/表1.5.3.png)

##练习
