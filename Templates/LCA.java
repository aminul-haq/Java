import java.util.*;
import java.io.*;

public class LCA{ 
  public static int n, m, L[], P[][], T[];
  public static ArrayList<ArrayList<Integer>> list;
  public static void main(String[] args)throws Exception {
    InputReader in = new InputReader(System.in);
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    n = in.nextInt();
    m = n-1;
    
    L = new int[n+5];   //level
    P = new int[n+5][20];   //sparse-table
    T = new int[n+5];  // parent
    
    list = new ArrayList<ArrayList<Integer>>();
    for(int i = 0; i <= n; i++){
      list.add(new ArrayList<Integer>());
    }
    
    for(int i = 0; i < m; i++){
      int u = in.nextInt(), v = in.nextInt();
      list.get(v).add(u);
      list.get(u).add(v);
    }
    
    int q = in.nextInt();
    
    lca_init();
    for(int i = 0; i < q; i++){
      System.out.println(lca(in.nextInt(), in.nextInt()));
    }
    
    pw.close();
  }
  
  static int lca(int p, int q){
    if(L[p] < L[q]){
      int temp = p; p = q; q = temp;
    }
    
    int log = 1;
    
    while((1<<(log+1)) <= L[p]){
      log++;
    }
      
    for(int i = log; i >= 0; i--){
      if(L[p] - (1 << i) >= L[q]){
              p = P[p][i];
      }
    }
    
    if(p == q) return p;
    
    for(int i = log; i >= 0; i--){
      if(P[p][i] != -1 && P[p][i] != P[q][i]){
         p = P[p][i]; q = P[q][i];
      }
    }
    
    return T[p];
    
  }
  
  static void lca_init(){
    for(int i = 0; i <= n; i++){
      Arrays.fill(P[i], -1);
    }
    
    dfs(1, 1, 0);
    
    for(int i = 0; i <= n; i++){
      P[i][0] = T[i];
    }
    
    for(int j = 1; (1<<j) <= n; j++){
      for(int i = 0; i <= n; i++){
        if(P[i][j - 1] != -1) P[i][j] = P[P[i][j-1]][j-1];
      }
    }
  }
  
  static void dfs(int from, int u, int l){
    T[u] = from;
    L[u] = l;
    for(int v : list.get(u)){
      if(v == from) continue;
      dfs(u, v, l+1);
    }
  }
  
  static class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;
    
    public InputReader(InputStream Pream) {
      reader = new BufferedReader(new InputStreamReader(Pream));
      tokenizer = null;
    }
    
    public String next()throws Exception {
      while (tokenizer == null || !tokenizer.hasMoreTokens())
        tokenizer = new StringTokenizer(reader.readLine());
      return tokenizer.nextToken();
    }
    
    public String nextLine()throws Exception {
      String line = null;
      tokenizer = null;
      line =  reader.readLine();
      return line;
    }
    
    public int nextInt()throws Exception {
      return Integer.parseInt(next());
    }
    
    public double nextDouble() throws Exception{
      return Double.parseDouble(next());
    }
    
    public long nextLong()throws Exception {
      return Long.parseLong(next());
    }
    
  }
}