
public class isMaxHeap {
  public static boolean isMaxHeap(Integer [] heapData, int size){


    for (int index = 0; index < size; index++) {
      int leftchild = index * 2 + 1;
      int rightchild = index * 2 + 2;
      if (leftchild <= size - 1 && heapData[leftchild] != null) {



        if (heapData[index] < heapData[leftchild])
        return false;
    }
    if (rightchild <= size - 1 && heapData[rightchild] != null) {


    if(heapData[index]<heapData[rightchild]) return false;}

}
    return true;
  }

  public static void main(String[] args) {
    System.out.println(isMaxHeap(new Integer[] {17, 5, 15, 2, 10, 8}, 6)); // should return false

    System.out.println(isMaxHeap(new Integer[] {17, 5, 15, 2, 3, 8, null, null}, 6));
    // should return true
    System.out.println(isMaxHeap(new Integer[] {17, null, null, null}, 1));
     // should return true
    System.out.println(isMaxHeap(new Integer[] {17, 5, 15, 3, null, 8}, 6));
    // should return false
  }
}
