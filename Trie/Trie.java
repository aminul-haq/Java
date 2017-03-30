

public class Trie {

 private Node root;

 public Trie()  // Constructor 
 {
  root = new Node(); 
  root.content = ' ';  // Root node contains blank - represents empty string.
 }

 public void insert(String s)
 {
  Node current = root; 
  
  if(s.length()==0)   // For empty String
   current.marker=true;
  
  for(int i=0;i<s.length();i++)
  {

   /* Revisit and first visit are being differentiated
    * to avoid overwriting child[] values during revisit
    */ 
   
   if(current.child[(int)(s.charAt(i)-'a')] != null)  // Revisit
   {
    current = current.child[(int)(s.charAt(i)-'a')];
    System.out.println("Inserted character: "+ current.content);
   }
   
   else    // First visit
   {
    current.child[(int)(s.charAt(i)-'a')] = new Node((int)(s.charAt(i)-'a'));
    current = current.child[(int)(s.charAt(i)-'a')];
    System.out.println("Inserted character: "+ current.content);
   }
   // Place a marker to indicate end of the word
   if(i==s.length()-1)
    current.marker = true;
  } 
  System.out.println("Finished inserting the word: "+s+"\n");
 }

 

 public boolean search(String s)
 {
  Node current = root;
  System.out.println("\nSearching for string: "+s);
  
  while(current != null)
  {
   for(int i=0;i<s.length();i++)
   {    
    if(current.child[(int)(s.charAt(i)-'a')] == null)
    {
     System.out.println("Cannot find string: "+s);
     return false;
    }
    else
    {
     current = current.child[(int)(s.charAt(i)-'a')];
     System.out.println("Found character: "+ current.content);
    }
   }
   // If we are here, the string exists.
   // But to ensure unwanted substrings are not found:
   
   if (current.marker == true)
   {
    System.out.println("Found string: "+s);
    return true;
   }
   else
   {
    System.out.println("Cannot find string: "+s +"(only present as a substring)");
    return false;
   }
  }
  
  return false; 
 }

}
