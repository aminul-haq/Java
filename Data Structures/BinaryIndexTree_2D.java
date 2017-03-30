import java.util.*;

public class BinaryIndexTree_2D{
  public static int [][] bit;
  public static void updateBIT(int x, int y, int val){
    int y1;
    while (x < bit.length){
      y1 = y;
      while (y1 < bit[x].length){
        bit[x][y1] += val;
        y1 += (y1 & -y1); 
      }
      x += (x & -x); 
    }
  }
  
  
  public static int getSum(int x, int y){ //return sum form 1,1
    int sum= 0;         
    while( x > 0){
      int y1 = y;
      while(y1 > 0){
        sum += bit[x][y1];
        y1 -= y1 & -y1;
      }
      x -= x & -x;
    }
    return sum;
  }
  
  public static void main(String[] args){
    //int [] arr = {1,2,3,4,5,6};
    // createBIT(arr);
    bit = new int[10][10];
    updateBIT(1,1,1);
    System.out.println(getSum(4,4));
    updateBIT(3,3,12);
    System.out.println(getSum(4,4));
    updateBIT(2,3,5);
    System.out.println(getSum(4,4));
    updateBIT(1,3,7);
    System.out.println(getSum(0,0));
    updateBIT(1,1,1);
    
    
  }
}
