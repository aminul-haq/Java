import java.util.*;
import java.io.*;

public class Uva644{ 
  public static void main(String[] args)throws IOException, Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    String s = br.readLine();
    int t = 0;
    
  //  while( (s = br.readLine()) != null){
      ArrayList <String> list = new ArrayList<String>();
      Trie tri = new Trie();
      while( !s.equals("9")){
        //  tri.insert(s);
        list.add(s);
       // System.out.println(s);
        s = br.readLine();
      }
      boolean bol = false;
      
      Collections.sort(list, new StringLengthComparator());
      tri.insert(list.get(0));
      for(int i = 1; i< list.size(); i++){
        String temp = list.get(i);
        String out = tri.prefixSearch(temp);
        System.out.println(temp+" "+out);
        if(temp.length() == out.length()){
          bol = true;
         // break;
        }
        // tri.prefixSearch(temp);
        tri.insert(temp);
      }
      if(bol) pw.println("Set "+(++t)+" is immediately decodable");
      else pw.println("Set "+(++t)+" is not immediately decodable");
      
   // }
    pw.close();
  }
  
  
  static class Trie{
    private Node root;
    public Trie(){
      root = new Node(new Node[2], 0,' ' );
    }
    public void insert(String word){
      Node current = root;
      for(int i = 0; i < word.length(); i++){
      //  System.err.print(word.charAt(i) - '0');
        if(current.getChildren(word.charAt(i) - '0') != null){
          current = current.getChildren(word.charAt(i) - '0');
          current.count++;
        }
        else{
          Node newNode = new Node(new Node[2],1, (word.charAt(i)));
          current.setChildren(word.charAt(i) - '0', newNode);
          current = newNode;
        }
      }
    //  System.out.println();
    }
    
    public String prefixSearch(String key){
      String out = "";
      Node current = root;
      for(int i = 0; i <key.length(); i++){
        
        if(i > 0 && (current ==  null || current.c != key.charAt(i) ))
          break;
        out+=""+key.charAt(i);
        System.err.println(current.c+" "+(key.charAt(i) - '0'));
        current = current.getChildren(key.charAt(i) - '0');
        
      }
      return out;
    }
  }
  
  
  
  static class Node{
    public Node[] children;
    public int count;
    public char c ;
    public Node(Node[] child, int cnt, char chr){
      children = child;
      count = cnt;
      c = chr;
    }
    
    public Node getChildren(int i){
      return children[i];
    }
    public void setChildren(int i, Node n){
      children[i] = n;
    }
  }
  
  
  
  static  class StringLengthComparator implements Comparator<String> {
    public int compare(String o1, String o2) {
      if (o1.length() < o2.length()) {
        return 1;
      } else if (o1.length() > o2.length()) {
        return -1;
      } else {
        return 0;
      }
    }
  }
}
