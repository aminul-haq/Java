import java.util.*;
import java.io.*;

public class DIJKSTRA_01{ 
  public static int [] par, vis;
  public static void main(String[] args)throws Exception {
    InputReader in = new InputReader(System.in);
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    int n = in.nextInt(), m = in.nextInt(), s = in.nextInt(), t = in.nextInt();
    //int[][] mat = new int[n+1][n+1];
    
    ArrayList<ArrayList<Node>> list = new  ArrayList<ArrayList<Node>>();
    for(int i = 0; i <= n+1; i++){
      list.add(new ArrayList<Node>());
    }
    
    
    for(int i = 0; i < m; i++){
      int u = in.nextInt(), v = in.nextInt(), w = in.nextInt();
      list.get(u).add(new Node(v, w));
      list.get(v).add(new Node(u, w));
    }
    
    pw.println(dijkstra(n,s,t,list));

    
    pw.close();
  }
  
  static int dijkstra(int n, int s, int t, ArrayList<ArrayList<Node>> list){
    par = new int[n+1];
    vis = new int[n+1];
    Arrays.fill(vis, 9999999);
    
    PriorityQueue<Node> pq = new  PriorityQueue<Node>();
    pq.add(new Node(s, 0));
    vis[s] = 0;
    
    while(!pq.isEmpty()){
      Node poll = pq.poll();
      int u = poll.v;
      int ucost = vis[u];
      
      for(int i = 0; i < list.get(u).size(); i++){
        int v = list.get(u).get(i).v, w = list.get(u).get(i).w;
        if(vis[v] > ucost + w){
          vis[v] = ucost+w;
          pq.add(new Node(v, ucost+w));
        }
      }
    }
    
    return vis[t];
  }
  
  static class Node implements Comparable<Node>{
    public int v, w;
    public Node(int vv, int ww){
      v = vv; w = ww;
    }
    
    public int compareTo(Node nd){
      return w - nd.w;
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