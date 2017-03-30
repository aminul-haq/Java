import java.util.*;
import java.io.*;

public class MO_sqrt_decom_sample{ 
  public static int N;
  public static void main(String[] args)throws Exception {
    InputReader in = new InputReader(System.in);
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    int n = in.nextInt(), q = in.nextInt(); N = (int) Math.sqrt(n);
    int n5 = n + 5;
    int[] a = new int[n5];
    
    for(int i = 1; i <= n; i++){
      a[i] = in.nextInt();
      if(a[i] > n) a[i] = n+3;
    }
    
    Node [] pair = new Node[q];
    
    for(int i = 0; i < q; i++){
      pair[i] = new Node(in.nextInt(), in.nextInt(), i);
    }
    Arrays.sort(pair);
    
    int [] ans = new int[q];
    
    int count[] = new int[n5];
    //int occur[] = new int[n5];
    
    int l = 1, r = 0, res = 0, x = 0;
  //  updateBIT(a[1], 1);
    
    for(int i = 0; i < q; i++){
      while( r < pair[i].v){
        r++;x = a[r];
        count[x]++;
        if(count[x] == x) res++;
        else if(count[x] == 1+x) res--;
      }
      
      while( r > pair[i].v){
        x = a[r];
        count[x]--;
        if(count[x] == x) res++;
        else if(count[x] == x-1) res--;
        r--;
      }
     
     
      while( l > pair[i].u){
        l--;x = a[l];
        count[x]++;
        if(count[x] == x) res++;
        else if(count[x] == x+1) res--;
      }
      
      while( l < pair[i].u){
        x = a[l];
        count[x]--;
        if(count[x] == x) res++;
        else if(count[x] == x-1) res--;
        l++;
      }
      
      ans[pair[i].idx] = res;
      
    //  System.err.println(pair[i].u+" "+pair[i].v+" "+res);
      
    }
    
    for(int i: ans) pw.println(i);
    

    
    pw.close();
  }
  
  static class Node implements Comparable<Node>{
    public int u, v, idx;
    public Node(int uu, int vv, int i){
      u = uu; v = vv; idx = i;
    }
    public int compareTo(Node n){
      if(u/N != n.u/N) return u - n.u;
      else return v - n.v;
    }
  }
  
  
  static class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;
    
    public InputReader(InputStream stream) {
      reader = new BufferedReader(new InputStreamReader(stream));
      tokenizer = null;
    }
    
    public String next() {
  
      try {
        while (tokenizer == null || !tokenizer.hasMoreTokens())
          tokenizer = new StringTokenizer(reader.readLine());
      } catch (IOException e) {
        return null;
      }
      
      return tokenizer.nextToken();
    }
    
    public String nextLine() {
      String line = null;
      try {
        tokenizer = null;
        line =  reader.readLine();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      return line;
    }
    
    public int nextInt() {
      return Integer.parseInt(next());
    }
    
    public double nextDouble() {
      return Double.parseDouble(next());
    }
    
    public long nextLong() {
      return Long.parseLong(next());
    }
    public boolean hasNext(){
      try {
        while (tokenizer == null || !tokenizer.hasMoreTokens())
          tokenizer = new StringTokenizer(reader.readLine());
      } 
      catch (Exception e) {
        return false;
      }
     
    return true; 
    
    }
  }
}