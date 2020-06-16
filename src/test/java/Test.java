/**
 * @author wb
 * @date 2020/1/4 23:31
 */
public class Test {
    public static void main(String [] args){
//        System.out.println(sum(3));
        System.out.println(nummuti(4));
    }

    public static int sum(int i){
        if(i==1){
            return i;
        }else {
            return i+sum(i-1);
        }
    }

    public static int nummuti(int i){
        if(i==1){
            return i;
        }else {
            return i*nummuti(i-1);
        }
    }

    public static int fmuti(int i){
        if(i==0){
            return 1;
        }else if(i==1){
            return 4;
        }else {
            return fmuti(i-1)+fmuti(i-2);
        }
    }

}
