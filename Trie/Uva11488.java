import java.util.*;
import java.io.*;

public class Uva11488{ 
  static int maxx = 0;
  public static void main(String[] args)throws IOException, Exception {
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    InputReader in = new InputReader(System.in);
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    int test = in.nextInt();
    for(int t = 1; t <= test; t++){
      int n = in.nextInt();
     // String [] a = new String[n];
      Trie tri = new Trie();
      int max = 0;
      int count = 1;
      for(int i = 0; i< n; i++){
        String s = in.nextLine();
        tri.insert(s);
      }
      
      pw.println(maxx);
      maxx = 0;
    }
    pw.close();
  }
  
  
  
  static class Trie{
    private Node root;
    public Trie(){
      root = new Node(new Node[2], 0, 0);
    }
    public void insert(String word){
      Node current = root;
      for(int i = 0; i < word.length(); i++){
        if(current.getChildren(word.charAt(i) - '0') != null){
          current = current.getChildren(word.charAt(i) - '0');
          current.count++;
        }
        else{
          Node newNode = new Node(new Node[2],1, ++Node.len);
          current.setChildren(word.charAt(i) - '0', newNode);
          current = newNode;
        }
        maxx = Math.max(maxx, (i+1)*current.count);
      }
    }

  }
  static class Node{
    public Node[] children;
    public int count;
    static int len = 0;
    public Node(Node[] child, int cnt, int l){
      children = child;
      count = cnt;
      len = l;
    }
    
    public Node getChildren(int i){
      return children[i];
    }
    public void setChildren(int i, Node n){
      children[i] = n;
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
        throw new RuntimeException(e);
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
  }
}