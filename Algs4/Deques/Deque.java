import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  
  private Node first; 
  private Node last;
  private int size;
  
  private class Node {
    Item item;
    Node next;
    Node prev;
  }
  
  /** 
   * construct an empty deque.
   */
  public Deque() {
    first = null;
    last = null;
    size = 0;
  }
  
  // is the deque empty?
  public boolean isEmpty() {
    return size == 0;
  }
  
  // return the number of items on the deque
  public int size() {
    return size;
  }
  
  /**
   *  add the item to the front.
   */
  public void addFirst(Item item) {
    if (item == null) { 
      throw new IllegalArgumentException("Cannot add null item");    
    }
    Node oldfirst = first;
    first = new Node();
    first.item = item;
    first.next = oldfirst;
    first.prev = null;
    if (oldfirst != null) {
      oldfirst.prev = first;
    }
    if (isEmpty()) {
      last = first;
    }
    size++;
  }
  
  /**
   * add the item to the end.
   */
  public void addLast(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Cannot add null item");
    }
    Node oldlast = last;
    last = new Node();
    last.item = item;
    last.prev = oldlast;
    last.next = null;
    if (oldlast != null) {
      oldlast.next = last;
    }
    if (isEmpty()) {
      first = last;
    }
    size++;
  }
  
  /**
   *  remove an return the item from the front.
   */
  public Item removeFirst() {
    if (size == 0) {
      throw new NoSuchElementException("The deque is empty.");
    }
    final Item item = first.item;
    first = first.next;
    if (first != null) {
      first.prev = null;
    }
    size--;
    if (isEmpty()) {
      last = null;
    }
    return item;
  }
  
  /**
   *  remove and return the item from the end.
   */
  public Item removeLast() {
    if (size == 0) {
      throw new NoSuchElementException("The deque is empty.");
    }
    final Item item = last.item;
    last = last.prev;
    if (last != null) {
      last.next = null;
    }
    size--;
    if (isEmpty()) {
      first = null;
    }
    return item;
  }
  
  /**
   *  return an iterator over items in order from front to end.
   */
  public Iterator<Item> iterator() {
    return new DequeIterator();
  }
  
  private class DequeIterator implements Iterator<Item> {
    private Node current = first;
    
    @Override
    public boolean hasNext() {
      return current != null;     
    }

    @Override
    public Item next() {
      if (current == null) { 
        throw new NoSuchElementException("The deque is empty."); 
      }
      Item item = current.item;
      current = current.next;
      return item;
    }
    
    @Override
    public void remove() {
      throw new java.lang.UnsupportedOperationException();
    }
  }
  
  /**
   *  unit testing (optional).
   */
  public static void main(String[] args) {
    Deque<Integer> deque1 = new Deque<Integer>();
    deque1.addLast(3);
    deque1.addFirst(1);
    deque1.addFirst(2);
    deque1.addFirst(9);
    deque1.addLast(11);
    System.out.println(deque1.removeLast());
    System.out.println(deque1.removeLast());
    
    Deque<Integer> deque2 = new Deque<Integer>();
    deque2.addFirst(5);
    deque2.addLast(10);
    deque2.addLast(60);
    deque2.addLast(80);
    deque2.addLast(30);
    deque2.removeFirst();
    deque2.removeFirst();
    Iterator<Integer> it = deque2.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());    
    }
    
    Deque<Integer> deque3 = new Deque<Integer>();
    deque3.removeLast();
  }
}
