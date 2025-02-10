import java.util.ArrayList;

public class LinkedQueue<T> implements QueueADT<T> {
  private LinkedNode<T> front; // Points to the front of the queue
  private LinkedNode<T> back; // Points to the back of the queue
  private int size; // Number of elements in the queue

  // Constructor to initialize an empty queue
  public LinkedQueue() {
    this.front = null;
    this.back = null;
    this.size = 0;
  }

  @Override
  public void enqueue(T value) {
    if (value == null) {
      throw new IllegalArgumentException("Value cannot be null");
    }

    LinkedNode<T> newNode = new LinkedNode<>(value);
    if (back == null) { // If the queue is empty
      front = back = newNode;
    } else {
      back.setNext(newNode); // Link the new node to the back
      back = newNode; // Update the back reference
    }
    size++; // Increment the size
  }

  @Override
  public T dequeue() {
    if (isEmpty()) {
      return null; // Return null if the queue is empty
    }

    T value = front.getData(); // Get the data from the front node
    front = front.getNext(); // Move the front pointer to the next node

    if (front == null) { // If the queue is now empty
      back = null; // Update the back pointer
    }

    size--; // Decrement the size
    return value; // Return the removed value
  }

  @Override
  public T peek() {
    return (isEmpty()) ? null : front.getData(); // Return null if empty, otherwise the front value
  }

  @Override
  public boolean isEmpty() {
    return size == 0; // The queue is empty if size is 0
  }

  @Override
  public int size() {
    return size; // Return the current size of the queue
  }

  @Override
  public void clear() {
    front = back = null; // Remove all references
    size = 0; // Reset the size to 0
  }

  @Override
  public boolean contains(T value) {
    if (value == null) {
      return false; // Null values cannot be in the queue
    }

    LinkedNode<T> current = front; // Start from the front
    while (current != null) {
      if (current.getData().equals(value)) {
        return true; // Return true if the value is found
      }
      current = current.getNext(); // Move to the next node
    }

    return false; // Return false if the value is not found
  }

  // Optional utility method to return the queue contents as an ArrayList
  public ArrayList<T> getList() {
    ArrayList<T> list = new ArrayList<>();
    LinkedNode<T> current = front;

    while (current != null) {
      list.add(current.getData()); // Add each node's data to the list
      current = current.getNext(); // Move to the next node
    }

    return list; // Return the ArrayList representation
  }
}
