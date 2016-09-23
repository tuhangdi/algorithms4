import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by thd on 2016/9/23.
 */
public class test {
    public static void main (String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int a,b,c;
        a=b=c=0;
        a = scan.nextInt();
        b = scan.nextInt();
        c = scan.nextInt();
        //System.out.println(a);
        //System.out.println(b);
        //System.out.println(c);
        if(a==b && b==c){
            System.out.println("equal");
        }
        else System.out.println("not equals");
    }
}
