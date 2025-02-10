// TODO: file header

import java.util.NoSuchElementException;

/**
 * A tester class for the Election Manager project. It contains various tests
 * to check the correctness of the Candidate, Election, and Ballot classes.
 *
 */
public class ElectionManagerTester {

  /**
   * Tests the Candidate constructor, toString(), and getter method for correctness.
   * This test accounts for the fact that a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testCandidateConstructorAndGetters() {

    // in case we get an unexpected exception from a broken implementation
    // we handle it with a try-catch to avoid our tester from crashing
    try { 
      // test the 2-argument constructor
      Candidate c = new Candidate("lebron james", "basketball");

      // check that the instance data fields have been initialized correctly
      // using the toString to do this we are also checking its correctness!
      // in a bad implementation either:
      //   1) the constructor didn't intialize a data field correctly OR
      //   2) toString() doesn't return the correct value
      if (!c.toString().equals("lebron james (basketball): 0")) return false;
      
      // let's also verify the one getter method agrees with the toString() output:
      if (c.getNumVotes() != 0) return false;

    } catch (Exception e) { 
      // we encountered an exception when we should not have, it is a bad implementation!
      e.printStackTrace();
      return false;
    }
    
    // all tests pass:
    return true;
  }

  /**
   * Verifies that the Candidate constructor throws the correct type of exception(s) 
   * where an exception is expected. See the Candidate documentation for details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testCandidateConstructorExceptions() {
    try {
      new Candidate(null, "basketball");
      return false; // Expected an exception
    } catch (IllegalArgumentException e) {
      // Expected
    }

    try {
      new Candidate("lebron james", null);
      return false; // Expected an exception
    } catch (IllegalArgumentException e) {
      // Expected
    }

    try {
      new Candidate("", "basketball");
      return false; // Expected an exception
    } catch (IllegalArgumentException e) {
      // Expected
    }

    try {
      new Candidate("lebron james", "");
      return false; // Expected an exception
    } catch (IllegalArgumentException e) {
      // Expected
    }

    return true; // All tests passed
  }

  public static boolean testElectionConstructorAndGetters() {
    try {
      Election election = new Election("Best Player", 5);
      if (!election.SEAT_NAME.equals("Best Player"))
        return false;
      if (election.capacity() != 5)
        return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Verifies that the Election constructor throws the correct type of exception(s) 
   * in situations where an exception is expected. See the Election documentation for
   * details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testElectionConstructorExceptions() {
    try {
      new Election(null, 5);
      return false; // Expected an exception
    } catch (IllegalArgumentException e) {
      // Expected
    }

    try {
      new Election("Best Player", -1);
      return false; // Expected an exception
    } catch (IllegalArgumentException e) {
      // Expected
    }

    return true; // All tests passed
  }

  /**
   * Tests the Election's addCandidate() method for correctness in non-Exception situations.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddCandidate() {
    try {
      Election election = new Election("Best Player", 3);
      Candidate candidate = new Candidate("Alice", "Party A");
      election.addCandidate(candidate);
      if (election.getNumCandidates() != 1)
        return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
  
  /**
   * Verifies that the Election's addCandidate() method throws the correct type of exception(s) 
   * in situations where an exception is expected. See the Election documentation for
   * details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddCandidateExceptions() {
    try {
      Election election = new Election("Best Player", 1);
      Candidate candidate = new Candidate("Alice", "Party A");
      election.addCandidate(candidate);
      election.addCandidate(candidate); // Adding same candidate again
      return false; // Expected an exception
    } catch (IllegalArgumentException e) {
      // Expected
    }

    return true; // All tests passed
  }

  /**
   * Tests the Election's vote() method for correctness in non-Exception situations.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testVote() {
    try {
      Election election = new Election("Best Player", 3);
      Candidate candidate = new Candidate("Alice", "Party A");
      election.addCandidate(candidate);
      election.vote(candidate);
      if (candidate.getNumVotes() != 1)
        return false; // Vote should increment
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Verifies that the Election's vote() method throws the correct type of exception(s) 
   * in situations where an exception is expected. See the Election documentation for
   * details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testVoteExceptions() {
    ////////////////////////////////////////////////////////////////////////////////////////
    // we're doing the setup separately, so we can isolate the actual test later.
    // if anything fails HERE, that's a different problem than the one we're trying to test,
    // and the test should fail.
    
    Election election = null; // declare outside of the try block for scope reasons
    try {
      election = new Election("Sportsball", 10);
      Candidate c1 = new Candidate("lebron james", "basketball");
      Candidate c2 = new Candidate("messi", "soccer");
      election.addCandidate(c1); election.addCandidate(c2);
    } catch (Exception e) {
      System.out.println("Unable to continue with this test for unrelated reasons!!");
      e.printStackTrace();
      return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    // THIS part is what we are actually testing:

    try {
      election.vote(new Candidate("usain bolt", "athletics"));
      return false; // this line only runs if NO exception is thrown from the previous line
    } catch(NoSuchElementException e) {
      // this is correct
    } catch(Exception e) {
      // this only runs if an exception other than NoSuchElementException was thrown,
      // which is wrong!
      e.printStackTrace();
      return false;
    }
    
    // all tests pass:
    return true;
  }
  
  /**
   * Tests the Election's removeCandidate() method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveCandidate() {
    try {
      Election election = new Election("Best Player", 5);
      Candidate candidate1 = new Candidate("Alice", "Party A");
      Candidate candidate2 = new Candidate("Bob", "Party B");
      Candidate candidate3 = new Candidate("Charlie", "Party C");

      // Add candidates
      election.addCandidate(candidate1);
      election.addCandidate(candidate2);
      election.addCandidate(candidate3);

      // Remove a candidate and check if it was removed correctly
      election.removeCandidate(candidate2);
      if (election.toString().contains("Bob")) {
        return false; // Bob should have been removed
      }

      // Ensure the remaining candidates are still present
      if (!election.toString().contains("Alice") || !election.toString().contains("Charlie")) {
        return false; // Alice and Charlie should still be in the election
      }

    } catch (Exception e) {
      e.printStackTrace();
      return false; // Test failed due to an unexpected exception
    }

    return true; // All tests passed
  }


  /**
   * Verifies that the Election's removeCandidate() method throws the correct type of exception(s)
   * in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  /**
   * Verifies that the Election's removeCandidate() method throws the correct type of exception(s) 
   * in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveCandidateExceptions() {
    Election election = new Election("Best Player", 3);
    Candidate candidate1 = new Candidate("Alice", "Party A");
    Candidate candidate2 = new Candidate("Bob", "Party B");

    election.addCandidate(candidate1);

    try {
      election.removeCandidate(candidate2); // Trying to remove a candidate not in the election
      return false; // Expected an exception
    } catch (NoSuchElementException e) {
      // Expected
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      election.removeCandidate(null); // Trying to remove a null candidate
      return false; // Expected an exception
    } catch (IllegalArgumentException e) {
      // Expected
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true; // All tests passed
  }


  /**
   * Tests the Ballot two-phase setup process in non-Exception situations.
   * This test accounts for the fact that a bad implementation may throw an Exception.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotSetup() {
    try {
      Ballot ballot = new Ballot();
      Election election = new Election("Best Player", 3);
      ballot.addElection(election); // Phase 1
      if (ballot.getElections().size() != 1)
        return false; // Verify election added

      ballot.createBallot(); // Phase 2
      if (ballot.getCurrentBallot() == null)
        return false; // Ensure ballot created
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Verifies that the Ballot two-phase setup process throws the correct type of exception(s)
   * in situations where an exception is expected. See the Ballot documentation for details.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotSetupExceptions() {
    try {
      Ballot ballot = new Ballot();
      ballot.createBallot(); // Phase 2 without adding elections
      return false; // Expected an exception
    } catch (IllegalStateException e) {
      // Expected
    }

    return true; // All tests passed
  }

  /**
   * Tests the Ballot vote() and hasVoted() methods in non-Exception situations.
   * This test accounts for the fact that a bad implementation may throw an Exception.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotVote() {
    try {
      Ballot ballot = new Ballot();
      Election election = new Election("Best Player", 3);
      ballot.addElection(election);
      ballot.createBallot();

      Candidate candidate = new Candidate("Alice", "Party A");
      election.addCandidate(candidate);
      ballot.vote(candidate); // Cast vote
      if (!ballot.hasVoted(candidate))
        return false; // Check if candidate has voted
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Verifies that the Ballot vote() and hasVoted() methods throw the correct type of
   * exception(s) in situations where an exception is expected. See the Ballot documentation
   * for details.
   * 
   * @return true if all tests pass, false otherwise 
   */
  public static boolean testBallotVoteExceptions() {
    try {
      Ballot ballot = new Ballot();
      ballot.createBallot(); // No elections added
      ballot.vote(new Candidate("Not in Election", "Party X")); // Voting without elections
      return false; // Expected an exception
    } catch (IllegalStateException e) {
      // Expected
    }

    return true; // All tests passed
  }

  /**
   * Runs all testing methods and prints out their results.
   * @return true if and only if all the tests return true, false otherwise
   */
  public static boolean runAllRequiredTests() {
    
    boolean test1 = testCandidateConstructorAndGetters();
    System.out.println("testCandidateConstructorAndGetters(): " + (test1 ? "PASS" : "FAIL"));
    
    boolean test2 = testCandidateConstructorExceptions();
    System.out.println("testCandidateConstructorExceptions(): " + (test2 ? "PASS" : "FAIL"));

    boolean test3 = testElectionConstructorAndGetters();
    System.out.println("testElectionConstructorAndGetters(): " + (test3 ? "PASS" : "FAIL"));

    boolean test4 = testElectionConstructorExceptions();
    System.out.println("testElectionConstructorExceptions(): " + (test4 ? "PASS" : "FAIL"));

    boolean test5 = testAddCandidate();
    System.out.println("testAddCandidate(): " + (test5 ? "PASS" : "FAIL"));
    
    boolean test6 = testAddCandidateExceptions();
    System.out.println("testAddCandidateExceptions(): " + (test6 ? "PASS" : "FAIL"));

    boolean test7 = testVote();
    System.out.println("testVote(): " + (test7 ? "PASS" : "FAIL"));

    boolean test8 = testVoteExceptions();
    System.out.println("testVoteExceptions(): " + (test8 ? "PASS" : "FAIL"));

    boolean test9 = testRemoveCandidate();
    System.out.println("testRemoveCandidate(): " + (test9 ? "PASS" : "FAIL"));

    boolean test10 = testRemoveCandidateExceptions();
    System.out.println("testRemoveCandidateExceptions(): " + (test10 ? "PASS" : "FAIL"));

    boolean test11 = testBallotSetup();
    System.out.println("testBallotSetup(): " + (test11 ? "PASS" : "FAIL"));

    boolean test12 = testBallotSetupExceptions();
    System.out.println("testBallotSetupExceptions(): " + (test12 ? "PASS" : "FAIL"));

    boolean test13 = testBallotVote();
    System.out.println("testBallotVote(): " + (test13 ? "PASS" : "FAIL"));

    boolean test14 = testBallotVoteExceptions();
    System.out.println("testBallotVoteExceptions(): " + (test14 ? "PASS" : "FAIL"));

    return test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8 && test9 && test10
        && test11 && test12 && test13 && test14;
  }

  /**
   * Calls runAllRequiredTests and displays the output. If you add additional private testers, call 
   * them directly in the main method rather than adding them to the previous method.
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("runAllRequiredTests(): " + runAllRequiredTests());
  }


}
