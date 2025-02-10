// FREEZE CODE BEGIN
public class MaxHeap {
  private int[] heap;
  private int size;

  public MaxHeap(int capacity) {
    heap = new int[capacity];
  }
  // FREEZE CODE END

  // WRITE YOUR CODE HERE
  public int delete() {
    int max = heap[0];
    heap[0] = heap[size - 1];
    int pointer = 0;
    int temp;
    size--;
    while (pointer * 2 + 1 <= size) {
      if (pointer * 2 + 1 < size) {
        if (heap[pointer * 2 + 1] > heap[pointer * 2 + 2]) {
          if (heap[pointer * 2 + 1] > heap[pointer]) {
            temp = heap[pointer * 2 + 1];
            heap[pointer * 2 + 1] = heap[pointer];
            heap[pointer] = temp;
          }
          pointer = pointer * 2 + 1;
        } else if (heap[pointer * 2 + 2] > heap[pointer]) {
          temp = heap[pointer * 2 + 2];
          heap[pointer * 2 + 2] = heap[pointer];
          heap[pointer] = temp;
        }
        pointer = pointer * 2 + 2;
      } else if (heap[pointer * 2 + 1] > heap[pointer]) {
        temp = heap[pointer * 2 + 1];
        heap[pointer * 2 + 1] = heap[pointer];
        heap[pointer] = temp;
      }
      pointer = pointer * 2 + 1;
    }
    return max;

  }



  // FREEZE CODE BEGIN
  public void printHeap() {
    for (int i = 0; i < size; i++) {
      System.out.print(heap[i] + " ");
    }
    System.out.println();
  }

  public void setHeap(int[] values) {
    if (values.length <= heap.length) {
      heap = values;
      size = heap.length;
    }
  }

  public static void main(String[] args) {
    MaxHeap heap = new MaxHeap(12);
    int[] values = {7, 8, 3, 9, 2, 5, 4};

    heap.setHeap(values);

    System.out.println("Old root: " + heap.delete());
    heap.printHeap();
  }
}
// FREEZE CODE END
