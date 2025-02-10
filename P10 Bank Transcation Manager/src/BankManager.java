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

public class BankManager {
  protected TransactionHeap low;
  protected TransactionHeap medium;
  protected TransactionHeap high;

  public BankManager(int capacity) {
    low = new TransactionHeap(capacity);
    medium = new TransactionHeap(capacity);
    high = new TransactionHeap(capacity);
  }

  /**
   * Gets and removes the next transaction from the priority queues. Take the transaction from the
   * highest available priority queue (high -> medium -> low).
   * 
   * @return The next transaction to process, null if there are no transactions.
   */
  public Transaction getNextTransaction() {
    // TODO
    if (!high.isEmpty()) {
      return high.getNextTransaction();
    }
    if (!medium.isEmpty()) {
      return medium.getNextTransaction();
    }
    if (!low.isEmpty()) {
      return low.getNextTransaction();
    }
    return null;


  }

  /**
   * Gets the highest priority transaction from the priority queues without removing it. Take the
   * transaction from the highest available priority queue (high -> medium -> low).
   * 
   * @return the transaction with highest priority from all heaps and null if there are no
   *         transactions.
   */
  public Transaction peekHighestPriorityTransaction() {
    // TODO
    if (!high.isEmpty()) {
      return high.peek();
    }
    if (!medium.isEmpty()) {
      return medium.peek();
    }
    if (!low.isEmpty()) {
      return low.peek();
    }
    return null;
  }

  /**
   * Adds a transaction to the BankManager according to the amount that the transaction is for low:
   * < 1000 medium: 1000 <= t < 1000000 high: >= 1000000
   * 
   * @param transaction the transaction to add to the BankManager
   */
  public void queueTransaction(Transaction transaction) {
    if (transaction.getAmount() < 1000) {
      low.addTransaction(transaction);
    } else if (1000 <= transaction.getAmount() & transaction.getAmount() < 1000000) {
      medium.addTransaction(transaction);
    } else if (transaction.getAmount() >= 1000000) {
      high.addTransaction(transaction);
    }
  }

  /**
   * Removes and processes the next transaction in the priority queue. Withdrawals should remove the
   * amount from the balance, deposits should add the amount to the balance, and loan applications
   * add the amount to the balance only if the loan amount is less than ten (10) times the account's
   * current balance.
   * 
   * @throws NoSuchElementException if there are no transactions to process
   * @throws IllegalStateException  if the account would overdraft
   */
  public void performTransaction() {
    Transaction currTransaction = getNextTransaction();
    if (currTransaction.getType() == Transaction.Type.DEPOSIT) {
      currTransaction.getUser().deposit(currTransaction.getAmount());
    } else if (currTransaction.getType() == Transaction.Type.WITHDRAWAL) {
      currTransaction.getUser().withdraw(currTransaction.getAmount());
    } else if (currTransaction.getType() == Transaction.Type.LOAN_APPLICATION) {
      if (currTransaction.getAmount() < 10 * (currTransaction.getUser().getBalance())) {
        currTransaction.getUser().deposit(currTransaction.getAmount());
      }
    }
  }


}
