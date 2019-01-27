public class LLStack<T> implements Stack<T>
{
  private class Node
  {
        private T item;
        private Node next;
        
        public Node() {}
        
        public Node(T obj, Node n)
        {
            item = obj;
            next = n;
        }
    }
  
  private Node top;
  
  public LLStack()
  {
    top = null;
  }
  
  /* isEmpty()
   * Returns true if stack is empty.
   * Returns false otherwise.
   */
  public boolean isEmpty()
  {
    return (top == null);
  }
  
  /* isFull()
   * Returns false.
   * Linked Lists are never full.
   */
  public boolean isFull()
  {
    return false;
  }
  
  /* push()
   * Adds the specified item to the top of the stack.
   * Returns true upon addition.
   */
  public boolean push(T o)
  {
    Node temp = top;
    top = new Node();
    top.item = o;
    top.next = temp;
    return true;
  }
  
  /* pop()
   * Removes the item at the top of the stack.
   * Returns a reference to the removed object.
   * Returns null is the stack is empty.
   */
  public T pop()
  {
    if(isEmpty())
      return null;
    T removed = top.item;
    top = top.next;
    return removed;
  }
  
  /* peek()
   * Returns a reference to the item at the top of the stack without removing it.
   * Returns null is the stack is empty.
   */
  public T peek()
  {
    if(isEmpty())
      return null;
    return top.item;
  }
  
  /* toString()
   * Returns string of contents of stack.
   */
  public String toString()
  {
    String str = "{";
    Node temp = top;
    while(temp.next != null)
    {
      str += temp.item + ", ";
      temp = temp.next;
    }
    str += temp.item + "}";
    return str;
  }
}