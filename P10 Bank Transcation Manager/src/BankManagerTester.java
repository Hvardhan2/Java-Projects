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

public class BankManagerTester {

  /**
   * Tests the constructor for the Transaction class.
   * 
   * @return true if the test passes
   */
  public static boolean testTransactionConstructor() {
    // TODO (1)Verify if the transaction was created correctly and assigned the
    // correct priority.
    Account acc1 = new Account(10101, 100.00);
    Transaction t1 = new Transaction(acc1, 5000.00, Transaction.Type.LOAN_APPLICATION);
    if (!(t1.getPriority() == Transaction.Priority.LOW))
      return false;
    // (2)Verify the only correct amounts can be used, otherwise verify that an
    // exception is thrown.
    if (!(t1.getAmount() == 5000.00))
      return false;
    boolean val = true;
    try {
      Transaction t2 = new Transaction(acc1, -50.00, Transaction.Type.LOAN_APPLICATION);
      val = false;
    } catch (IllegalArgumentException e) {

    }
    if (!val)
      return false;
    return true;
  }

  /**
   * tests the Transaction.compareTo when the priorities are different
   * 
   * @return true if the test passes
   */
  public static boolean testTransactionCompareToPriority() {
    // TODO verify that the transaction with the higher priority is greater than the
    // one with the lower.
    Account acc1 = new Account(10101, 100.00);
    Transaction t1 = new Transaction(acc1, 500.0, Transaction.Type.DEPOSIT);
    Transaction t2 = new Transaction(acc1, 1000.0, Transaction.Type.LOAN_APPLICATION);
    return (t1.compareTo(t2) > 0);


  }

  /**
   * tests the Transaction.compareTo when the priorities are the same
   * 
   * @return true if the test passes
   */
  public static boolean testTransactionCompareToAccountBalance() {
    // TODO verify that when the transaction has the same priority, the account
    // balance is used.
    Account acc1 = new Account(10101, 100.00);
    Account acc2 = new Account(10101, 200.00);
    Transaction t1 = new Transaction(acc1, 500.0, Transaction.Type.DEPOSIT);
    Transaction t2 = new Transaction(acc2, 1000.0, Transaction.Type.DEPOSIT);
    return t2.compareTo(t1) > 0;
  }

  /**
   * Tests the TransactionHeap.addTransaction() method
   * 
   * @return true if the test passes
   */
  public static boolean testAddTransactionToHeap() {
    // TODO Verify that the transaction is added to the transaction heap
    Account acc1 = new Account(10101, 100.00);
    Transaction t1 = new Transaction(acc1, 500.0, Transaction.Type.DEPOSIT);
    Transaction t2 = new Transaction(acc1, 1000.0, Transaction.Type.DEPOSIT);
    Transaction t3 = new Transaction(acc1, 100.0, Transaction.Type.WITHDRAWAL);
    TransactionHeap heap1 = new TransactionHeap(2);
    heap1.addTransaction(t1);
    heap1.addTransaction(t3);
    boolean bool1 = false;
    for (int i = 0; i < heap1.getSize(); i++) {
      if (heap1.getHeapData()[i] == t1)
        bool1 = true;
    }
    if (!bool1)
      return false;

    boolean bool2 = false;
    try {
      heap1.addTransaction(t2);
      bool2 = true;
    } catch (IllegalStateException e) {
    }
    if (bool2)
        return false;

      return true;



  }

  /**
   * Tests the TransactionHeap.heapifyUp() and TransactionHeap.heapifyDown() methods
   * 
   * @return true if the test passes
   */
  // public static boolean testHeapify() {
  // // TODO (1) verify that the heap property is restored after adding a transaction
  // // to the heap
  // Account acc1 = new Account(10101, 100.00);
  // Account acc2 = new Account(10102, 200.00);
  // Account acc3 = new Account(10103, 300.00);
  // Transaction t1 = new Transaction(acc1, 500.0, Transaction.Type.DEPOSIT);
  // Transaction t2 = new Transaction(acc2, 1000.0, Transaction.Type.WITHDRAWAL);
  // Transaction t3 = new Transaction(acc3, 1000.0, Transaction.Type.LOAN_APPLICATION);
  // Transaction t4 = new Transaction(acc3, 100.0, Transaction.Type.LOAN_APPLICATION);
  // TransactionHeap heap1 = new TransactionHeap(10);
  // heap1.addTransaction(t1);
  // heap1.addTransaction(t3);
  // heap1.addTransaction(t2);
  // heap1.addTransaction(t4);
  // System.out.println(heap1.getHeapData());
  //
  // if (!((heap1.getHeapData()[0] == t4) & (heap1.getHeapData()[1] == t1)
  // & (heap1.getHeapData()[2] == t3) & (heap1.getHeapData()[3] == t2)))
  // return false;
  //
  //
  // // (2)verify that the heap property is restored after removing a transaction
  // // from the heap
  // heap1.getNextTransaction();
  // if (!((heap1.getHeapData()[0] == t1) & (heap1.getHeapData()[0] == t1)
  // & (heap1.getHeapData()[0] == t1)))
  // return false;
  // return true;



  /**
   * Tests the TransactionHeap.heapifyUp() and TransactionHeap.heapifyDown() methods
   * 
   * @return true if the test passes
   */
  public static boolean testHeapify() {
    // Verify that the heap property is restored after adding a transaction to the heap
    Account acc1 = new Account(10101, 100.00);
    Account acc2 = new Account(10102, 200.00);
    Account acc3 = new Account(10103, 300.00);
    Transaction t1 = new Transaction(acc1, 500.0, Transaction.Type.DEPOSIT);
    Transaction t2 = new Transaction(acc2, 1000.0, Transaction.Type.WITHDRAWAL);
    Transaction t3 = new Transaction(acc3, 1000.0, Transaction.Type.LOAN_APPLICATION);
    Transaction t4 = new Transaction(acc3, 100.0, Transaction.Type.LOAN_APPLICATION);
    TransactionHeap heap1 = new TransactionHeap(10);

    // Adding transactions
    heap1.addTransaction(t1);
    // System.out.println("After adding t1: " + Arrays.toString(heap1.getHeapData()));

    heap1.addTransaction(t3);
    // System.out.println("After adding t3: " + Arrays.toString(heap1.getHeapData()));

    heap1.addTransaction(t2);
    // System.out.println("After adding t2: " + Arrays.toString(heap1.getHeapData()));

    heap1.addTransaction(t4);
    // System.out.println("After adding t4: " + Arrays.toString(heap1.getHeapData()));

    // Validate heap structure
    if (!((heap1.getHeapData()[0] == t4) & (heap1.getHeapData()[1] == t1)
        & (heap1.getHeapData()[2] == t2) & (heap1.getHeapData()[3] == t3))) {
      System.out.println("Heap property violated after adding transactions.");
      return false;
    }

    // Verify that the heap property is restored after removing a transaction
    Transaction removed = heap1.getNextTransaction();
    // System.out.println("Removed transaction: " + removed);
    // System.out.println("After removing a transaction: " +
    // Arrays.toString(heap1.getHeapData()));
    // System.out.println("t1:" + t1);
    // System.out.println("t2:" + t2);
    // System.out.println("t3:" + t3);
    // System.out.println("t4:" + t4);
    if (!((heap1.getHeapData()[0] == t1) & (heap1.getHeapData()[1] == t3)
        & (heap1.getHeapData()[2] == t2))) {
      // System.out.println("Heap property violated after removing a transaction.");
      return false;
    }

    // System.out.println("Heapify test passed.");
    return true;
  }



  /**
   * Tests the TransactionHeap.getNextTransaction() method
   * 
   * @return true if the test passes
   */
  public static boolean testGetNextTransactionFromHeap() {
    // TODO (1) verify that the correct transaction is removed
    Account acc1 = new Account(10101, 100.00);
    Account acc2 = new Account(10102, 200.00);
    Account acc3 = new Account(10103, 300.00);
    Transaction t1 = new Transaction(acc1, 500.0, Transaction.Type.DEPOSIT);
    Transaction t2 = new Transaction(acc2, 1000.0, Transaction.Type.WITHDRAWAL);
    Transaction t3 = new Transaction(acc3, 1000.0, Transaction.Type.LOAN_APPLICATION);
    TransactionHeap heap1 = new TransactionHeap(10);
    heap1.addTransaction(t2);
    heap1.addTransaction(t1);
    heap1.addTransaction(t3);

    if (!(heap1.getNextTransaction() == t1)) {
      return false;
    }
    // (2) verify that an exception is thrown if the heap is empty
    boolean bool = true;
    TransactionHeap heap2 = new TransactionHeap(10);
    try {

      heap2.getNextTransaction();
      bool = false;
    } catch (NoSuchElementException e) {

    }
    if (!bool)
      return false;
    return true;



  }

  /**
   * Tests the BankManager.queueTransaction() method
   * 
   * @return true if the test passes
   */
  public static boolean testQueueTransaction() {
    // TODO Verify that a transaction is added in the correct queue:
    // (1) Verify that a transaction with amount <1000 is added to the "low" heap
    Account acc1 = new Account(10101, 100.00);
    Account acc2 = new Account(10102, 200.00);
    Account acc3 = new Account(10103, 300.00);
    Transaction t1 = new Transaction(acc1, 500.0, Transaction.Type.DEPOSIT);
    Transaction t2 = new Transaction(acc2, 1000.0, Transaction.Type.WITHDRAWAL);
    // Transaction t3 = new Transaction(acc3, 1000.0, Transaction.Type.LOAN_APPLICATION);
    Transaction t4 = new Transaction(acc3, 1000000.0, Transaction.Type.LOAN_APPLICATION);
    BankManager bm1 = new BankManager(10);
    bm1.queueTransaction(t1);



    // (2) Verify that a transaction with amount greater or equal to 1000 but less
    // than 1000000 is added to the "medium" heap
    bm1.queueTransaction(t2);
    // (3) Verify that a transaction with amount greater or equal to 1000000 is
    // added to the "high" heap
    bm1.queueTransaction(t4);
    if (!(t4 == bm1.getNextTransaction()))
      return false;
    if (!(t2 == bm1.getNextTransaction()))
      return false;
    if (!(t1 == bm1.getNextTransaction()))
      return false;
    return true;



  }

  /**
   * Tests the BankManager.performTransaction() method
   * 
   * @return true if the test passes
   */
  public static boolean testPerformTransaction() {
    // TODO (1) Verify the transaction is removed from the correct heap
    Account acc1 = new Account(10101, 100.00);
    Account acc2 = new Account(10102, 200.00);
    Account acc3 = new Account(10103, 3.00);
    Transaction t1 = new Transaction(acc1, 50000000.0, Transaction.Type.DEPOSIT);
    Transaction t2 = new Transaction(acc2, 1000.0, Transaction.Type.WITHDRAWAL);
    Transaction t3 = new Transaction(acc3, 1000.0, Transaction.Type.LOAN_APPLICATION);
    Transaction t4 = new Transaction(acc3, 100.0, Transaction.Type.LOAN_APPLICATION);
    BankManager bm1 = new BankManager(10);
    bm1.queueTransaction(t1);
    bm1.queueTransaction(t2);
    bm1.queueTransaction(t3);
    // bm1.queueTransaction(t4);
    bm1.performTransaction();
    if (!bm1.high.isEmpty())
      return false;



    // (2) Verify the transaction is processed correctly:

    // (2a) Verify that the transaction is done when that is possible based on the
    // rules

    if (!(acc1.getBalance() == 50000100.0))
      return false;


    // (2b) Verify that an exception is thrown when necessary
    boolean bool = false;
    try {
      bm1.performTransaction();
      bool = true;
    } catch (IllegalStateException e) {
    }
    if (bool)
      return false;


    bm1.performTransaction();
    if (!(acc3.getBalance() == 3))
      return false;



    return true;
  }

  /**
   * Tests the BankManager.peekHighestPriorityTransaction() method
   * 
   * @return true if the test passes
   */
  public static boolean testPeekHighestPriorityTransaction() {
    // TODO verify that the transaction with the highest priority is returned
    // without being removed
    Account acc1 = new Account(10101, 100.00);
    Account acc2 = new Account(10102, 200.00);
    Account acc3 = new Account(10103, 300.00);
    Transaction t1 = new Transaction(acc1, 500.0, Transaction.Type.DEPOSIT);
    Transaction t2 = new Transaction(acc2, 1000.0, Transaction.Type.WITHDRAWAL);
    Transaction t3 = new Transaction(acc2, 10000000.0, Transaction.Type.LOAN_APPLICATION);
    Transaction t4 = new Transaction(acc3, 1000000.0, Transaction.Type.LOAN_APPLICATION);
    BankManager bm1 = new BankManager(10);
    bm1.queueTransaction(t1);
    bm1.queueTransaction(t2);
    bm1.queueTransaction(t3);
    bm1.queueTransaction(t4);

    if (!(bm1.peekHighestPriorityTransaction() == t4))
      return false;
    if (!(bm1.getNextTransaction() == t4))
      return false;
    if (!(bm1.getNextTransaction() != t4))
      return false;
    return true;
  }

  public static void main(String[] args) {
    System.out.println(
        "Transaction Constructor Tests: " + (testTransactionConstructor() ? "PASS" : "FAIL"));
    System.out.println(
        "CompareTo Tests for Priority: " + (testTransactionCompareToPriority() ? "PASS" : "FAIL"));
    System.out.println("CompareTo Tests for Account Balance: "
        + (testTransactionCompareToAccountBalance() ? "PASS" : "FAIL"));
    System.out.println(
        "Testing Add Transaction to Heap: " + (testAddTransactionToHeap() ? "PASS" : "FAIL"));
    System.out.println("Testing Heapify: " + (testHeapify() ? "PASS" : "FAIL"));
    System.out.println(
        "Testing Get Next Transaction: " + (testGetNextTransactionFromHeap() ? "PASS" : "FAIL"));
    System.out.println("Testing Queue Transaction: " + (testQueueTransaction() ? "PASS" : "FAIL"));
    System.out
        .println("Testing Perform Transaction: " + (testPerformTransaction() ? "PASS" : "FAIL"));
    System.out.println("Testing Peek Highest Priority Transaction: "
        + (testPeekHighestPriorityTransaction() ? "PASS" : "FAIL"));
  }
}
