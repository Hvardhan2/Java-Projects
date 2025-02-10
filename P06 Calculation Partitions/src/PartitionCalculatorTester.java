//ADD YOUR FILE HEADER HERE


import java.util.ArrayList;
import java.util.Random;

//ADD YOUR CLASS HEADER HERE
public class PartitionCalculatorTester {

  /**
   * Tests the correctness of PartitionCalculator.numOfPartitions method for base cases n = 1
   * and n = 2. This should also account for unexpected exceptions that MAY occur.
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testNumOfPartitionsBase() {
    return false;
  }

  /**
   * Tests the correctness of PartitionCalculator.numOfPartitions method for recursive cases with
   * n >= 3. There must be a total of 3 test cases. This should also account for unexpected
   * exceptions that MAY occur.
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testNumOfPartitionsRecursive() {
    return false;
  }

  /**
   * Tests the correctness of PartitionCalculator.numOfPartitions method for multiple random cases
   * of N. (See write-up for more details). This should also account for unexpected 
   * exceptions that MAY occur.
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testNumOfPartitionsFuzz() {
    return false;
  }

  /**
   * Tests the correctness of PartitionCalculator.calculatePartitions method for base cases n = 1
   * and n = 2. This should also account for unexpected exceptions that MAY occur.
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testCalcPartitionsBase() {
    return false;
  }


  /**
   * Tests the correctness of PartitionCalculator.calculatePartitions method for recursive cases with
   * n >= 3. There must be a total of 3 test cases. This should also account for unexpected
   * exceptions that MAY occur.
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testCalcPartitionsRecursive() {
    return false;
  }

  /**
   * Tests the correctness of PartitionCalculator.calculatePartitions method for multiple random cases
   * of N. (See write-up for more details). This should also account for unexpected
   * exceptions that MAY occur.
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testCalcPartitionsFuzz() {
    return false;
  }

  /**
   * Tests the correctness of PartitionCalculator.calculateAllPermuations method for base cases n = 1
   * and n = 2. This should also account for unexpected exceptions that MAY occur.
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testCalculateAllPermutationsBase() {
    return false;
  }

  /**
   * Tests the correctness of PartitionCalculator.calculateAllPermutations method for recursive 
   * cases with n >= 3. There must be a total of 3 test cases. This should also account for unexpected
   * exceptions that MAY occur.
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testCalculateAllPermutationsRecursive() {
    return false;
  }

  /**
   * Runs and outputs the results of all tester methods.
   * @return true if all tests return true, false otherwise
   * @author Michelle Jensen
   */
  public static boolean runAllTests() {
    boolean test1 = testNumOfPartitionsBase();
    System.out.println("testNumOfPartitionsBase(): " + (test1 ? "PASS" : "FAIL"));

    boolean test2 = testNumOfPartitionsRecursive();
    System.out.println("testNumOfPartitionsRecursive(): " + (test2 ? "PASS" : "FAIL"));

    boolean test3 = testCalcPartitionsBase();
    System.out.println("testUniquePartitionsBase(): " + (test3 ? "PASS" : "FAIL"));

    boolean test4 = testCalcPartitionsRecursive();
    System.out.println("testUniquePartitionsRecursive(): " + (test4 ? "PASS" : "FAIL"));

    boolean test5 = testCalculateAllPermutationsBase();
    System.out.println("testCalculateAllPermutationsBase(): " + (test5 ? "PASS" : "FAIL"));

    boolean test6 = testCalculateAllPermutationsRecursive();
    System.out.println("testCalculateAllPermutationsRecursive(): " + (test6 ? "PASS" : "FAIL"));
    
    boolean test7 = testNumOfPartitionsFuzz();
    System.out.println("testNumOfPartitionsFuzz(): " + (test7 ? "PASS" : "FAIL"));
    
    boolean test8 = testCalcPartitionsFuzz();
    System.out.println("testUniquePartitionsFuzz(): " + (test8 ? "PASS" : "FAIL"));

    return test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8;
  }

  public static void main(String[] args) {
    System.out.println("runAllTest(): " + (runAllTests()? "PASS" : "FAIL"));
  }

}
