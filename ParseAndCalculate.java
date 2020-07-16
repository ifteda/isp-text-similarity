import java.util.*;

public class ParseAndCalculate {
  
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println("Please enter your postfix expression.");
    String str = scan.next();
    //LinkedTree tree = parseToTree(str);
    //infix(tree);
    //prefix(tree);
    System.out.println(calculate(str));
  }
  
  
//  public static LinkedTree parseToTree(String str) {
//    Stack<Character> s = new LLStack<Character>();
//    LinkedTree node = new LinkedTree();
//    
//    //traversing String:
//    for (int i = 0; i < str.length(); i++) {
//      
//      if(str.charAt(i) == ' ')
//        i++;
//      
//      char c = str.charAt(i);
//      
//      if (isOperand(c)) { 
//        node.insert(Integer.parseInt(c + ""), c);
//        s.push(c);
//      }
//      
//      else if (isOperator(c)) {
//        Character tree1 = s.pop(); //?
//        Character tree2 = s.pop(); //?
//        if (tree1 == null || tree2 == null)
//          throw new NullPointerException("Empty node.");
//        //LinkedTree node = new LinkedTree(); //operator is root; tree1 & tree2 are its children
//        s.push();
//      }
//      
//      else
//       throw new IllegalArgumentException("Invalud input.");
//    }
//  }
  
  
  public static boolean isOperator(char c) {
    if (c == '+' || c == '-' || c == '*' || c == '/' || c == '%')
      return true;
    else
      return false;
  }
  
  
  
  public static boolean isOperand(char c) {
    if(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' ||
       c == '5' || c == '6' || c == '7' || c == '8' || c == '9')
      return true;
    else
      return false;
  }
  
  
//  //make public accessor methods for node in LinkedTree
//  public static void infix(LinkedTree tree) {
//     if (tree.height(tree.getNode()) > 0) //tree not empty
//        if (isOperator(tree.getNode())) //token? -> node or value that node holds?
//           System.out.print("(");
//        infix(tree.left); //explicit param = left child
//        System.out.print(tree.getNode()); //token?
//        infix(tree.right); // explicit param = right child
//        if (isOperator(tree.getNode()))
//           System.out.print(")");
//    }
//  
//  public static void prefix(LinkedTree tree) { //?
//    if (tree.height(root) > 0) { //tree not empty
//      System.out.print(tree.getNode());
//      prefix(tree.left); //explicit param = left child
//      prefic(tree.right); //explicit param = right child
//    }
//  }
  
  
  /* Calculates value of expression.
   */
  public static int calculate(String str) {
    Stack<Character> s = new LLStack<Character>();
    int result = 0;
    
    //traversing String:
    for (int i = 0; i < str.length(); i++) {
      
      if(str.charAt(i) == ' ')
        i++;
      
      char c = str.charAt(i);
      
      if (isOperand(c)) { 
        s.push(c);
        System.out.println(c);
      }
      
      else if (isOperator(c)) {
        Character val1 = s.pop();
        Character val2 = s.pop();
        System.out.println(val1 + " " + val2);
        if (val1 == null || val2 == null)
          throw new NullPointerException("Insufficient values.");

        if (val2 == null) //??
          return Integer.parseInt(val1 + "");
        if (c == '+')
          result = Integer.parseInt(val1 + "") + Integer.parseInt(val2 + "");
        if (c == '-')
          result = Integer.parseInt(val1 + "") - Integer.parseInt(val2 + "");
        if (c == '*')
          result = Integer.parseInt(val1 + "") * Integer.parseInt(val2 + "");
        if (c == '/')
          result = Integer.parseInt(val1 + "") / Integer.parseInt(val2 + "");
        if (c == '%')
          result = Integer.parseInt(val1 + "") % Integer.parseInt(val2 + "");
        s.push( (char) result);
      }
    }
      
      int answer = s.pop();
      if (s.peek() == null)
        return answer;
      else
        throw new IllegalArgumentException("Too many values.");    
  }
}