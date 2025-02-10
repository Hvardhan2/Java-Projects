// FREEZE CODE BEGIN
public class MinHeap {
  private int[] heap;
  private int size;

  public MinHeap(int capacity) {
    heap = new int[capacity];
  }
  // FREEZE CODE END

  // WRITE YOUR CODE HERE
  public void insert(int value) {
    int temp;
    heap[size] = value;

    int pointer = size;
    size++;



    while (pointer != 0) {
      if (heap[pointer] < heap[(pointer - 1) / 2]) {
        temp = heap[(pointer - 1) / 2];
        heap[(pointer - 1) / 2] = heap[pointer];
        heap[pointer] = temp;
        pointer = (pointer - 1) / 2;

      } else
        return;
    }
  }


  // FREEZE CODE BEGIN
  public void printHeap() {
    for (int i = 0; i < size; i++) {
      System.out.print(heap[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    MinHeap heap = new MinHeap(10);
    // int[] values = Helper.parseArgs(args, 1);
    // int toInsert = Helper.parseArgs(args, 2)[0];
    //
    // for (int i = 0; i < values.length; i++) {
    // heap.insert(values[i]);
    // }

    heap.insert(2);
    heap.insert(5);
    heap.insert(11);
    heap.insert(1);


    heap.printHeap();
  }
}
// FREEZE CODE END
