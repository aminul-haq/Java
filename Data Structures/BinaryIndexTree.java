import java.util.*;

public class BinaryIndexTree{
  public static int [] bit;
  public static void createBIT(int [] arr){
    bit = new int[arr.length+1];
    //updateBIT(0, 5);
    for(int i = 1; i <= arr.length; i++){
      updateBIT(i, arr[i-1]);
    }
  }
  
  public static void updateBIT(int index, int value){
   // index++;
    while(index < bit.length){
      bit[index] += value;
      index = index + ( index & -index);
    }
  }
  
  public static int getSum(int index){
    //index++;
    int sum = 0;
    while(index > 0){
      sum += bit[index];
      index = index - (index & -index);
    }
    return sum;
  }
  
  
  static int get(int x) //works great! :D
  {
    int MPOW = (int) (Math.log(bit.length)/Math.log(2));
    int sum=0;
    int ret=0; // Number of element in array, for which the sum is equal to sum
    for(int i = 1<<MPOW; i > 0 && ret+(i)<bit.length; i>>=1) // Loop through the powers of two, starting with the highest possible
    {
      if(sum+bit[ret+(i)] < x){ // Trying to expand the current prefix
        sum += bit[ret+(i)];
        ret += i;
        System.err.println(sum+" "+ret);
      }
    }
    return ret;
  }
  
  static int test(int l, int h) //works great! :D
  {
    int MPOW = (int) (Math.log(h)/Math.log(2));
    int sum=0;
    int ret=0; // Number of element in array, for which the sum is equal to sum
    for(int i = 8; i > 0 && ret+(i)<bit.length; i>>=1) // Loop through the powers of two, starting with the highest possible
    {
      System.out.println(i+" "+ret+" "+bit[(i)]);
      
      if(sum+bit[ret+(i)] < 6){ // Trying to expand the current prefix
        sum += bit[ret+(i)];
        ret += i;
        System.err.println(sum+" "+ret);
      }
    }
    return ret;
  }
  
  static int findG(int cumFre){ //it works, but i don't know how to make it work :P
    int MPOW = (int) (Math.log(bit.length)/Math.log(2));
    int idx = 0, bitMask = 1<<MPOW;
    
    while ((bitMask != 0) && (idx < bit.length)){
      int tIdx = idx + bitMask;
      System.out.println(tIdx);
      System.err.println(cumFre+" "+tIdx+" "+bit[tIdx]);
      if (cumFre >= bit[tIdx]){ 
       // System.out.println(cumFre+" "+tIdx+" "+bit[tIdx]);
        // if current cumulative frequency is equal to cumFre, 
        // we are still looking for higher index (if exists)
        idx = tIdx;
        cumFre -= bit[tIdx];
      }
      bitMask >>= 1;
    }
    if (cumFre != 0)
      return -1;
    else
      return idx;
  }
  
  public static void main(String[] args){
    int [] arr = {1, 2, 2, 1 , 1, 1, 2,1, 2}; //0, 0, 0, 1};
    createBIT(arr);
    System.err.println(getSum(4));
    
    System.out.println(test(1, 6)); 
    
  }
}
  