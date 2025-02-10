//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Bank Transaction Manager
// Course: CS 300 Fall 2024
//
// Author: Harshvardhan Singh Rathore
// Email: hvardhan2@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
// No pair programming for this project
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Some online help to understand how compareTo works for enum from the
// sources
//
///////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;

/**
 * Class for implementing a heap/priority queue on Transactions
 */
public class TransactionHeap {
	private Transaction[] transactions;
	private int size;

	/**
	 * Initializes transactions array with size capacity
	 * 
	 * @param capacity the length of the transactions heap array
	 */
	public TransactionHeap(int capacity) {
		transactions = new Transaction[capacity];
		size = 0;
	}

	/**
	 * This method adds a transaction to the heap if space allows.
	 * 
	 * @param transaction the transaction to add to the heap
	 * @throws IllegalStateException if the TransactionHeap is full.
	 */
	public void addTransaction(Transaction transaction) {
      if (size >= transactions.length)
        throw new IllegalStateException();
      else
        transactions[size] = transaction;

      heapifyUp(size);
      size++;

	}

	/**
	 * Reinforces the heap rules after adding a Transaction to the end
	 * 
	 * @param index the index of the new Transaction
	 */
	public void heapifyUp(int index) {
      while ((index - 1) / 2 >= 0
          & transactions[(index - 1) / 2].compareTo(transactions[index]) < 0) {
        Transaction temp = transactions[(index - 1) / 2];
        transactions[(index - 1) / 2] = transactions[index];
        transactions[index] = temp;
        index = (index - 1) / 2;
      }
	}

	/**
	 * Removes the next transaction from the priority queue
	 * 
	 * @return the next transaction in the priority queue
	 * @throws NoSuchElementException if there are no transactions in the heap
	 */
	public Transaction getNextTransaction() {
      if (size <= 0 & transactions[0] == null)
        throw new NoSuchElementException();
      Transaction root = transactions[0];
      transactions[0] = transactions[size - 1];
      transactions[size - 1] = null;
      size--;
      heapifyDown(0);
      return root;
	}

	/**
	 * Enforces the heap conditions after removing a Transaction from the heap
	 * 
	 * @param index the index whose subtree needs to be heapified
	 */
    public void heapifyDown(int index) {
      // When there is no child node
      if (index * 2 + 1 > size - 1) {
        return;
      }
      // when there is exactly one child node
      else if (index * 2 + 1 == size - 1) {
        if (transactions[index].compareTo(transactions[index * 2 + 1]) < 0) {
          Transaction temp = transactions[index];
          transactions[index] = transactions[index * 2 + 1];
          transactions[index * 2 + 1] = temp;
          heapifyDown(index * 2 + 1);
        } else
          return;
      }
      // When there are two child
      else if (index * 2 + 2 >= size - 1) {
        if ((transactions[index * 2 + 1].compareTo(transactions[index * 2 + 2]) > 0)) {
          if (transactions[index].compareTo(transactions[index * 2 + 1]) < 0) {
            Transaction temp = transactions[index];
            transactions[index] = transactions[index * 2 + 1];
            transactions[index * 2 + 1] = temp;
            heapifyDown(index * 2 + 1);
          } else
            return;

        } else if (transactions[index].compareTo(transactions[index * 2 + 2]) < 0) {
          Transaction temp = transactions[index];
          transactions[index] = transactions[index * 2 + 2];
          transactions[index * 2 + 2] = temp;
          heapifyDown(index * 2 + 2);
        } else
          return;
      }



    }
    // public void heapifyDown(int index) {
    // int largest = index; // Assume the current node is the largest
    // int leftChild = 2 * index + 1;
    // int rightChild = 2 * index + 2;
    //
    // // Check if the left child exists and is greater than the current largest
    // if (leftChild < size && transactions[leftChild].compareTo(transactions[largest]) > 0) {
    // largest = leftChild;
    // }
    //
    // // Check if the right child exists and is greater than the current largest
    // if (rightChild < size && transactions[rightChild].compareTo(transactions[largest]) > 0) {
    // largest = rightChild;
    // }
    //
    // // If the largest is not the current index, swap and continue heapifying down
    // if (largest != index) {
    // Transaction temp = transactions[index];
    // transactions[index] = transactions[largest];
    // transactions[largest] = temp;
    //
    // // Recursive call to heapify down the subtree
    // heapifyDown(largest);
    // }
    // }


	/**
	 * Returns the highest priority transaction without removing it from the heap.
	 * 
	 * @return the highest priority transaction without removing it from the heap.
	 * @throws NoSuchElementException if there are no transactions in the heap
	 */
	public Transaction peek() {
		// TODO
        if (isEmpty())
          throw new NoSuchElementException();
        return transactions[0];
	}

	/**
	 * Getter method for the heap size
	 * 
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Tells if the heap has any elements in it
	 * 
	 * @return whether or not the heap is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * PROVIDED Creates and returns a deep copy of the heap's array of data.
	 * 
	 * @return the deep copy of the array holding the heap's data
	 */
	public Transaction[] getHeapData() {
		Transaction[] list = new Transaction[this.transactions.length];
		for (int i = 0; i < list.length; i++)
			list[i] = this.transactions[i];
		return list;
	}

}
