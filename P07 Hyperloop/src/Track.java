public class Track implements ListADT<Pod> {
  protected LinkedNode head;
  protected LinkedNode tail;
  private int size;

  // Constructor initializes an empty track
  public Track() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  // Adds a Pod to the correct position based on its class type
  public void add(Pod newElement) {
    if (!newElement.isFunctional())
      return;

    LinkedNode newNode = new LinkedNode(newElement);
    if (newElement.getClass() != null) {
      if (head == null) {
        head = tail = newNode;
      } else {
        newNode.setNext(head);
        head.setPrev(newNode);
        head = newNode;
      }
        } else {
      if (tail == null) {
        head = tail = newNode;
      } else {
        tail.setNext(newNode);
        newNode.setPrev(tail);
        tail = newNode;
      }
        }
    size++;
    }

  // Adds a passenger to the first available seat in a Pod matching their class
  public boolean addPassenger(String name, boolean isFirstClass) {
    LinkedNode current = head;

    while (current != null) {
      try {
        if (current.getPod().getPodClass() == (isFirstClass ? Pod.FIRST : Pod.ECONOMY)
            && !current.getPod().isFull()) {
          current.getPod().addPassenger(name);
          return true;
        }
      } catch (MalfunctioningPodException e) {
        // Skip malfunctioning Pods
        }
        current = current.getNext();
    }
    return false; // No available seats in specified class Pods
  }
  // Removes all Pods from this track
  public void clear() {
    head = tail = null;
    size = 0;
    }

  // Checks if a Pod is contained in the track
  public boolean contains(Pod toFind) {
    LinkedNode current = head;
    while (current != null) {
      if (current.getPod().equals(toFind)) {
        return true;
      }
      current = current.getNext();
        }
    return false;
    }

  // Finds the index of the first non-functional Pod
  public int findFirstNonFunctional() {
    LinkedNode current = head;
    int index = 0;
    while (current != null) {
      if (!current.getPod().isFunctional()) {
        return index;
      }
      current = current.getNext();
      index++;
    }
    return -1;
    }

  // Searches for a passenger by name and returns the Pod index
  public int findPassenger(String name) {
    LinkedNode current = head;
    int index = 0;
    while (current != null) {
      try {
        if (current.getPod().containsPassenger(name)) {
          return index;
        }
      } catch (MalfunctioningPodException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      current = current.getNext();
      index++;
    }
    return -1;
    }

  // Accesses the Pod at a specified index
  public Pod get(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException();
    LinkedNode current = head;
    for (int i = 0; i < index; i++) {
      current = current.getNext();
    }
    return current.getPod();
    }

  // Checks if the track is empty
  public boolean isEmpty() {
    return size == 0;
    }

  // Removes a Pod at a specified index
  public Pod remove(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException();
    LinkedNode current = head;
    for (int i = 0; i < index; i++) {
      current = current.getNext();
    }
    Pod pod = current.getPod();
    if (current == head) {
      head = head.getNext();
      if (head != null)
        head.setPrev(null);
    } else if (current == tail) {
      tail = tail.getPrev();
      if (tail != null)
        tail.setNext(null);
    } else {
      current.getPrev().setNext(current.getNext());
      current.getNext().setPrev(current.getPrev());
    }
    size--;
    return pod;
    }

  // Reports the current number of Pods on this track
  public int size() {
    return size;
    }

  // Returns a string representation of the track
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    LinkedNode current = head;
    while (current != null) {
      sb.append(current.getPod().toString()).append("\n");
      current = current.getNext();
    }
    return sb.toString();
    }
}
