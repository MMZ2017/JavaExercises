import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private Item[] randomQueue;
  private int size;
  
  /**
   * construct an empty randomized queue.
   */
  public RandomizedQueue() {
    randomQueue = (Item[]) new Object[1];
    size = 0;
  }

  /**
   * is the randomized queue empty.
   */
  public boolean isEmpty() {
    return size == 0;   
  }
  
  /**
   * return the number of items on the randomized queue.
   */
  public int size() {
    return size;  
  }

  private void resize(int capacity) {
    Item[] copy = (Item[]) new Object[capacity];
    for (int i = 0; i < size; i++) {
      copy[i] = randomQueue[i];
    }
    randomQueue = copy;
  }
  
  /**
   * add an item.
   */
  public void enqueue(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Cannot add null item.");
    }
    if (size == randomQueue.length) {
      resize(2 * randomQueue.length);
    }
    if (size == randomQueue.length / 4 && randomQueue.length >= 2) {
      resize(randomQueue.length / 2);
    }
    randomQueue[size] = item;
    size++;
  }
  
  /**
   * remove and return a random item.
   */
  public Item dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("The randomQueue is empty.");
    }
    int r = StdRandom.uniform(size);
    final Item temp = randomQueue[r];
    if (r != size - 1) {
      randomQueue[r] = randomQueue[size - 1];
    }
    randomQueue[size - 1] = null;
    size--;
    return temp;    
  }
  
  /**
   * return a random item (but do not remove it).
   */
  public Item sample() {
    if (isEmpty()) {
      throw new NoSuchElementException("The randomQueue is empty.");
    }
    int r = StdRandom.uniform(size);
    return randomQueue[r];   
  }

  /**
   * return an independent iterator over items in random order.
   */
  public Iterator<Item> iterator() {
    return new RandomQueueIterator();
  }
    
  private class RandomQueueIterator implements Iterator<Item> {
    private int index = 0;

    /**
     * Create a constructor that Knuth shuffle the randomQueue.
     */
    public RandomQueueIterator() {
      for (int i = 1; i < size; i++) {        
        int r = StdRandom.uniform(i);
        Item temp = randomQueue[r];
        randomQueue[r] = randomQueue[i];
        randomQueue[i] = temp;
      }
    }

    @Override
    public boolean hasNext() {
      return randomQueue[index] != null;
    }

    @Override
    public Item next() {
      if (isEmpty()) { 
        throw new NoSuchElementException("The randomQueue is empty."); 
      }
      Item temp = randomQueue[index];
      index++;
      return temp;
    }
    
    @Override
    public void remove() {
      throw new java.lang.UnsupportedOperationException();
    }
  }

  /**
   * unit testing (optional).
   */
  public static void main(String[] args) {
    RandomizedQueue<Integer> rq1 = new RandomizedQueue<Integer>();
    rq1.enqueue(3);
    rq1.enqueue(6);
    rq1.iterator();
    rq1.enqueue(9);
    rq1.enqueue(12);
    rq1.enqueue(15);
    rq1.enqueue(18);
    System.out.println(rq1.dequeue());
    System.out.println(rq1.sample());
    
    RandomizedQueue<Integer> rq2 = new RandomizedQueue<Integer>();
    rq2.sample();
  } 
}
