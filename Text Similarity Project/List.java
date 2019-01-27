/*
 * List.java
 * An interface for specifying a List ADT
 */
public interface List {
    /** returns the item at position i in the list */
    Object getItem(int i);

    /** 
     * adds the specified item at position i in the list, shifting the
     * items that are currently in positions i, i+1, i+2, etc. to the
     * right by one.  Returns false if the list is full, and true
     * otherwise. Should throw IndexOutOfBoundsException when appropriate
     */
    boolean addItem(Object item, int i);

    /** 
     * removes the item at position i in the list, shifting the items
     * that are currently in positions i+1, i+2, etc. to the left by
     * one.  Returns a reference to the removed object.
     * Should throw IndexOutOfBoundsException when appropriate
     */
    Object removeItem(int i);

    /** returns the number of items in the list */
    int length();

    /** returns true if the list is full, and false otherwise */
    boolean isFull();

    /** returns an iterator object for this list. */
    //ListIterator iterator();
}