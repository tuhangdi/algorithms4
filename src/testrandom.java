import java.util.Random;
/**
 * Created by thd on 2016/10/14.
 */

/***
 * 生成一百个-100到100的随机数，并在生成的同时取最大值和最小值
 */
public class testrandom {
    public static void main(String[] args)
    {
        int max = -100;
        int min = 100;
        int[] maxmin = new int[2];
        maxmin[0] = -100;
        maxmin[1] = 100;
        int i = 0;
        max = getmaxmin(maxmin,i)[0];
        min = getmaxmin(maxmin,i)[1];
        System.out.println("\n100随机数最大数为："+max+"\t最小数为："+min);
    }
    public static int[] getmaxmin(int[] maxmin,int i){
        if(i == 100) return maxmin;
        Random r = new Random();
        int random = 0;
        if (r.nextBoolean()) {
            random = r.nextInt(101) ;
        } else {
            random = -r.nextInt(101) ;
        }
        i++;
        if (maxmin[0] >= random && maxmin[1] <= random){
            return getmaxmin(maxmin,i);
        }
        else if(maxmin[0] < random && maxmin[1] <= random){
            maxmin[0] = random;
            return getmaxmin(maxmin,i);
        }
        else {
            maxmin[1] = random;
            return getmaxmin(maxmin,i);
        }
    }
}
