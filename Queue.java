import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The Class MyALDAQueue.
 *
 * @param <E> the element type
 */
public class MyALDAQueue < E > implements ALDAQueue < E > {

  /** The first. */
  private Node < E > first;

  /** The capacity. */
  private int capacity;

  /**
   * The Class Node.
   *
   * @param <E> the element type
   */
  private static class Node < E > {

    /** The data. */
    private E data;

    /** The next. */
    private Node < E > next;

    /**
     * Instantiates a new node.
     *
     * @param element the element
     * @param next the next
     */
    Node(E element, Node < E > next) {
      this.data = element;
      this.next = next;
    }
    
    /**
     * Sets the next.
     *
     * @param node the new next
     */
    public void setNext(Node < E > node) {
      this.next = node;
    }

  }

  /**
   * The Class QueueIterator.
   *
   * @param <E> the element type
   */
  public class QueueIterator <E> implements Iterator {

    /** The node. */
    private Node < E > node;

    /** The tmp. */
    private Node < E > tmp;

    /**
     * Instantiates a new queue iterator.
     *
     * @param node the node
     */
    public QueueIterator(Node < E > node) {
      this.node = node;
    }

    /**
     * Checks for next.
     *
     * @return true, if successful
     */
    @Override
    public boolean hasNext() {
      return node != null;
    }

    /**
     * Next.
     *
     * @return the object
     */
    @Override
    public Object next() {
      if (hasNext()) {
        tmp = node;
        node = node.next;
        return tmp.data;
      } else {
        throw new NoSuchElementException();
      }
    }

  }

  /**
   * Instantiates a new my ALDA queue.
   *
   * @param capacity the capacity
   */
  public MyALDAQueue(int capacity) {
    if (capacity == 0 || capacity < 0) {
      throw new IllegalArgumentException();
    }
    this.capacity = capacity;
  }

  /**
   * Iterator.
   *
   * @return the iterator
   */
  @Override
  public Iterator < E > iterator() {
    return new QueueIterator(first);
  }

  /**
   * Adds the.
   *
   * @param element the element
   */
  @Override
  public void add(E element) {
    Node < E > tmp;

    if (isFull()) {
      throw new IllegalStateException();
    } else if (element == null) {
      throw new NullPointerException();
    } else if (String.class.isInstance(element)) {
      element = (E)((String) element).intern();
    }

    if (first == null) {
      first = new Node < E > (element, null);
    } else {
      tmp = first;
      while (tmp.next != null) {
        tmp = tmp.next;
      }
      tmp.setNext(new Node < E > (element, null));
    }
  }

  /**
   * Adds the all.
   *
   * @param c the c
   */
  @Override
  public void addAll(Collection < ?extends E > c) {
    for (E e: c) {
      add(e);
    }
  }

  /**
   * Removes the.
   *
   * @return the e
   */
  @Override
  public E remove() {
    if (first == null) {
      throw new NoSuchElementException();
    }
    E data = first.data;
    first = first.next;
    return data;
  }

  /**
   * Peek.
   *
   * @return the e
   */
  @Override
  public E peek() {
    if (first != null) {
      return first.data;
    } else {
      return null;
    }
  }

  /**
   * Clear.
   */
  @Override
  public void clear() {
    while (first != null) {
      first = first.next;
    }
  }

  /**
   * Size.
   *
   * @return the int
   */
  @Override
  public int size() {
    int count = 0;
    Node < E > tmp = first;
    while (tmp != null) {
      tmp = tmp.next;
      count++;
    }
    return count;
  }

  /**
   * Checks if is empty.
   *
   * @return true, if is empty
   */
  @Override
  public boolean isEmpty() {
    return first == null;
  }

  /**
   * Checks if is full.
   *
   * @return true, if is full
   */
  @Override
  public boolean isFull() {
    return size() == capacity;
  }

  /**
   * Total capacity.
   *
   * @return the int
   */
  @Override
  public int totalCapacity() {
    return capacity;
  }

  /**
   * Current capacity.
   *
   * @return the int
   */
  @Override
  public int currentCapacity() {
    return capacity - size();
  }

  /**
   * Discriminate.
   *
   * @param e the e
   * @return the int
   */
  @Override
  public int discriminate(E e) {
    Node < E > prev = first;
    Node < E > temp = first; 
    int count = 0;
    if (e == null) throw new NullPointerException();

    while (temp != null) {
      if (first.data == e) {
        first = first.next;
        count++;
      } else if (temp.data == e) {
        prev.next = temp.next;
        temp = prev;
        count++;
      }
      prev = temp;
      temp = temp.next;
    }

    for (int i = 0; i < count; i++, add(e)) {}

    return count;

  }

  /**
   * To string.
   *
   * @return the string
   */
  public String toString() {
    Node < E > tmp = first;
    E data;
    String output = "[";
    int i = 0;
    while (i < size()) {
      data = tmp.data;
      tmp = tmp.next;
      if (i == 0) {
        output += data + "";
      } else if (i == size() - 1) {
        output += ", " + data;
      } else {
        output += ", " + data + "";
      }
      i++;
    }
    output += "]";
    return output.toString();
  }

}
