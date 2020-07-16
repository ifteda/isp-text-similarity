public class LLList implements List
{
    private class Node
    {
        private Object o;
        private Node next;
        
        public Node(Object obj, Node n)
        {
            o = obj;
            next = n;
        }
    }
    
    private int numItems;
    private Node front;
    
    public LLList()
    {
        numItems = 0;
        front = null;
    }
    
    public Object getItem(int i)
    {
        if(i < 0 || i >= numItems)
            throw new IndexOutOfBoundsException("no item at given position");
        Node temp = front;
        while(i > 0)
        {
            temp = temp.next;
        }
        return temp.o;
     }
    
    public boolean addItem(Object item, int i)
    {
      if(i < 0 || i >= numItems)
            throw new IndexOutOfBoundsException("no item at given position");
      Node n = new Node(item, null);
      if(i == 0)
        n.next = front;
      
      Node temp = front;
      while(i > 1 && temp != null)
      {
        temp = temp.next;
        i--;
      }
      Node trail = temp;
      n.next = temp.next;
      trail.next = n;
      
      numItems++;
      return true;
    }
    
    public Object removeItem(int i)
    {
      if(i < 0 || i >= numItems)
            throw new IndexOutOfBoundsException("no item at given position");
      if(i == 0)
        return front.next;
      Node temp = front;
      while(i > 1 && temp != null)
      {
        temp = temp.next;
        i--;
      }
      Object toRemove = temp.next.o;
      Node trail = temp;
      Node trav = temp.next.next;
      trail.next = trav;
      numItems--;
      return toRemove;
    }
    
    public int length()
    {
      return numItems;
    }
    
    public boolean isFull()
    { return false; }
    
//    public ListIterator iterator()
//    {
//        return new LLListIterator();
//    }
//    
//    private class LLListIterator implements ListIterator
//    {
//        private Node n;
//        
//        public LLListIterator()
//        {
//            n = front;
//        }
//        
//        public boolean hasNext()
//        {
//            if(n != null)
//            {
//                return true;
//            }
//            return false;
//        }
//        
//        public Object next()
//        {
//            if( n == null )
//                throw new NoSuchElementException("Iterator has no items.");
//              
//            Object o = n.o;
//            n = n.next;
//            return o;
//        }
//    }
     
}