import java.util.Random;

/**
 * Created by thd on 2016/9/23.
 */
public class character1_1_11 {
    public static void main(String[] args){
        Boolean[][] boo = new Boolean[10][10];
        boo = random(boo);
        for(int i=0;i<boo.length;i++){
            for(int j=0;j<boo[0].length;j++){
                if(boo[i][j]) System.out.println((j+i*10)+":"+"*" );
                else System.out.println((j+i*10)+":"+" ");
            }
        }
    }
    public  static Boolean[][] random(Boolean[][] boo){
        Random ran = new Random();
        for(int i=0;i<boo.length;i++){
            for(int j=0;j<boo[0].length;j++){
                boo[i][j] = ran.nextBoolean();
            }
        }
        return boo;
    }
}
