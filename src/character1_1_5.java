import java.util.Scanner;

/**
 * Created by thd on 2016/9/23.
 */
public class character1_1_5 {
    public static void main(String[] args){
        Scanner scan  = new Scanner(System.in);
        double x,y;
        x = y = 0.0;
        x = scan.nextDouble();
        y = scan.nextDouble();
        if(x>0 && x<1 && y>0 && y<1){
            System.out.println("true");
        }
        else System.out.println("false");
    }
}
