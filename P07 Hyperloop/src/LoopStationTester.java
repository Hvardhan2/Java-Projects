//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: (descriptive title of the program making use of this file)
// Course: CS 300 Fall 2024
//
// Author: Harshvardhan Singh Rathore
// Email: hvardhan2@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources: (identify each by URL and describe how it helped)
//
/**
 * This class tests the LoopStation class, and by extension, the Track class.
 */
/**
 * This class tests the LoopStation class, and by extension, the Track class
 */
import java.util.NoSuchElementException;

public class LoopStationTester {

  /**
   * Checks the correctness of the createPod() method. This method should: - create a Pod with the
   * given capacity and podClass - add it to the correct end of the correct Track in the LoopStation
   * - return a reference (shallow copy) to that Pod Note that the tracks in LoopStation are
   * protected, so you may access them directly for testing purposes.
   *
   * @return true if createPod() is functioning correctly, false otherwise
   */
  public static boolean testCreatePod() {
    // Initialize a LoopStation for testing
    LoopStation station = new LoopStation();

    // Create a first-class Pod and check the track
    Pod firstClassPod = station.createPod(5, true); // Capacity 5, first-class
    if (station.waitingFirst.size() != 1 || !station.waitingFirst.contains(firstClassPod)) {
      return false; // First-class Pod should be in waitingFirst
    }

    // Create an economy Pod and check the track
    Pod economyPod = station.createPod(10, false); // Capacity 10, economy
    if (station.waitingEconomy.size() != 1 || !station.waitingEconomy.contains(economyPod)) {
      return false; // Economy Pod should be in waitingEconomy
    }

    return true; // Both pods are correctly created and added to the right track
    }

  /**
   * Checks the correctness of the launchPod() method. This method should: - throw a
   * NoSuchElementException if no pods are waiting to launch - launch first class pods from the END
   * of the waitingFirst track - launch economy class pods from the BEGINNING of the waitingEconomy
   * track - launch ALL first class pods before launching ANY economy class pods
   * 
   * @return true if launchPod() is functioning correctly, false otherwise
   */
    public static boolean testLaunchPod() {
    LoopStation station = new LoopStation();

    // Try launching with no pods
    try {
      station.launchPod();
      return false; // Should throw NoSuchElementException
    } catch (NoSuchElementException e) {
      // Correct behavior
    }

    // Add some pods
    Pod firstClassPod = station.createPod(5, true);
    Pod economyPod = station.createPod(10, false);

    // Launch first-class Pod
        station.launchPod();
    if (station.launched.size() != 1 || station.launched.get(0) != firstClassPod) {
      return false; // First-class Pod should be launched first
    }

    // Launch economy Pod
        station.launchPod();
    if (station.launched.size() != 2 || station.launched.get(1) != economyPod) {
      return false; // Economy Pod should be launched after all first-class pods
        }

    return true;
    }

  /**
   * Checks the correctness of the clearMalfunctioning() method. This method should: - repeatedly
   * check the launched track for malfunctioning pods - remove those pods correctly - report the
   * number of pods it removed once there are no longer any malfunctioning pods
   *
   * @return true if clearMalfunctioning() is functioning correctly, false otherwise
   */
    public static boolean testClearMalfunctioning() {
    LoopStation station = new LoopStation();

    // Add pods to the launched track
    Pod functionalPod = station.createPod(5, true);
    Pod malfunctioningPod = station.createPod(10, false);
    malfunctioningPod.setNonFunctional(); // Simulate malfunction

    // Launch the pods
    station.launchPod();
        station.launchPod();

    // Clear malfunctioning pods
    int removedCount = station.clearMalfunctioning();
    if (removedCount != 1 || station.launched.size() != 1) {
      return false; // Only one pod should be removed
    }

    return true;
    }

    /**
     * Checks the correctness of the three getNumXXX() methods from LoopStation.
     * This will require adding Pods of various types, loading them with passengers,
     * and launching them.
     * 
     * @return true if the getNumXXX() methods are all functioning correctly, false
     * otherwise
     */
    public static boolean testGetNums() {
        LoopStation station = new LoopStation();

        // Add pods and passengers
        Pod firstClassPod = station.createPod(5, true);
        try {
          firstClassPod.addPassenger("Alice");
        } catch (MalfunctioningPodException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
         try {
         firstClassPod.addPassenger("Bob");
         } catch (MalfunctioningPodException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         }

        Pod economyPod = station.createPod(10, false);
        try {
          economyPod.addPassenger("Charlie");
        } catch (MalfunctioningPodException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

        // Check getNumWaiting
        if (station.getNumWaiting() != 2) {
            return false; // Should have 2 pods waiting
        }

        // Launch pods
//        station.launchPod();
//
//        // Check getNumLaunched
//        if (station.getNumLaunched() != 1) {
//            return false; // One pod should have been launched
//        }
//
//        // Check getNumPassengers
////        if (station.getNumPassengers() != 3) {
//            return false; // Total passengers should be 3


// }
//
        return true;
    }

    public static void main(String[] args) {
      boolean test1 = testCreatePod();
      System.out.println("testCreatePod: " + (test1 ? "PASS" : "FAIL"));
      boolean test2 = testLaunchPod();
      System.out.println("testLaunchPod: " + (test2 ? "PASS" : "FAIL"));
      boolean test3 = testClearMalfunctioning();
      System.out.println("testClearMalfunctioning: " + (test3 ? "PASS" : "FAIL"));
      boolean test4 = testGetNums();
      System.out.println("testGetNums: " + (test4 ? "PASS" : "FAIL"));
      System.out.println("ALL TESTS: " + (test1 && test2 && test3 && test4 ? "PASS" : "FAIL"));
    }
}
