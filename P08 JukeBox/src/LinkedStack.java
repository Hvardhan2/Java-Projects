import java.util.ArrayList;

public class LinkedStack<T> implements StackADT<T> {
  private LinkedNode<T> top; // Reference to the top of the stack

  // Constructor to initialize an empty stack
  public LinkedStack() {
    this.top = null;
  }

  @Override
  public void push(T value) {
    if (value == null) {
      throw new IllegalArgumentException("Value cannot be null");
    }

    // Create a new node with the given value
    LinkedNode<T> newNode = new LinkedNode<>(value);
    newNode.setNext(top); // Link the current top to the new node
    top = newNode; // Update the top reference to the new node
  }

  @Override
  public T pop() {
    if (isEmpty()) {
      return null; // Return null if the stack is empty
    }

    T value = top.getData(); // Get the data from the top node
    top = top.getNext(); // Update the top reference to the next node
    return value; // Return the removed value
  }

  @Override
  public T peek() {
    return (isEmpty()) ? null : top.getData(); // Return null if empty, otherwise the top value
  }

  @Override
  public boolean isEmpty() {
    return top == null; // The stack is empty if the top reference is null
  }

  @Override
  public boolean contains(T value) {
    if (value == null) {
      return false; // Null values cannot be in the stack
    }

    LinkedNode<T> current = top; // Start from the top of the stack
    while (current != null) {
      if (current.getData().equals(value)) {
        return true; // Return true if the value is found
      }
      current = current.getNext(); // Move to the next node
    }

    return false; // Return false if the value is not found
  }

  // Optional utility method to return the stack contents as an ArrayList
  public ArrayList<T> getList() {
    ArrayList<T> list = new ArrayList<>();
    LinkedNode<T> current = top;

    while (current != null) {
      list.add(current.getData()); // Add each node's data to the list
      current = current.getNext(); // Move to the next node
    }

    return list; // Return the ArrayList representation
  }
}
