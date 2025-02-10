
public class Linkedlist {
  Node head;
  Node tail;
  Node temp;

  public void add(int val) {

    Node newNode = new Node(val);
    if (tail != null) {
      tail.nextNode = newNode;
      tail = newNode;
    } else {
      head = newNode;
      tail = newNode;
      temp = head;
    }

  }



  public void display() {

    if (temp == null) {
      temp = head;
      return;
    }
    System.out.print(temp.data + " ");
    temp = temp.nextNode;


    display();
  }
}

