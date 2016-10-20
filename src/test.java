import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


/**
 * Created by thd on 2016/9/23.
 */
public class test<Key extends Comparable<Key>, Value> {

    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node
    {
        Key key;            //键
        Value val;          //相关联的值
        Node left, right;   //左右子树
        int N;              //这棵子树中的结点总数
        boolean color;      //由其父结点指向它的链接的颜色
        Node(Key key, Value val, int N, boolean color)
        {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }
    private boolean isRed(Node x)
    {
        if (x == null) return false;
        return x.color == RED;
    }
    private Node rotateLeft(Node h)
    {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }
    private Node rotateRight(Node h)
    {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }
    private void flipColors(Node h)//颜色转换
    {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
    public int size()
    { return size(root); }
    private int size(Node x)
    {
        if (x == null) return 0;
        else  return x.N;
    }
    public void put(Key key, Value val)
    { //查找key，找到则更新其值，否则为它新建一个结点
        root = put(root, key, val);
        root.color = BLACK;
    }
    public int CompareTo(Key key)
    {
        if(this.key > key) return +1;
        if(this.key < key) return -1;
        return 0;
    }
    private Node put(Node h, Key key, Value val)
    {
        if(h == null)  return new Node(key, val, 1, RED); //标准的插入操作，和父结点用红链接相连
        int cmp = key.CompareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;

        if(isRed(h.right) && !isRed(h.left))  h = rotateLeft(h);//将任意含有红色右链接的3-结点（或临时的4-结点）向左旋转
        if(isRed(h.left) && isRed(h.left.left))  h = rotateRight(h);//将临时的4-结点中两条连续红链接中的上层链接向右旋转
        if(isRed(h.left) && isRed(h.right))  flipColors(h);//进行颜色转换并将红链接在树中向上传递
    }
}
