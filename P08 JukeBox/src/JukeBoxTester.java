//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: JukeBox
// Course: CS 300 Fall 2024
//
// Author: Harshvardhan Singh Rathore
// Email: hvardhan2@wisc.edu
// Lecturer: Hobbes LeGault
//
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Tester class for testing the functionality of the LinkedQueue, LinkedStack, Album, Song, and
 * Jukebox classes.
 */
public class JukeBoxTester {

  /**
   * Test the behavior of adding an element to the stack.
   * 
   * @return true if element is correctly added to the stack, false otherwise
   */
  public static boolean testStackAdd() {
    try {
      LinkedStack<Integer> stack = new LinkedStack<>();
      stack.push(10);
      stack.push(20);
      stack.push(30);

      // Verify the top element is the most recently added element
      if (stack.peek() != 30)
        return false;

      return true; // Test passes if no issues are encountered
    } catch (Exception e) {
      return false; // Fail test on exception
    }
  }


  /**
   * Test the behavior of removing an element from the stack.
   * 
   * @return true if element is correctly removed from the stack, false otherwise
   */
  public static boolean testStackRemove() {
    try {
      LinkedStack<Integer> stack = new LinkedStack<>();
      stack.push(10);
      stack.push(20);
      stack.push(30);

      // Verify the correct order of removal
      if (stack.pop() != 30 || stack.pop() != 20 || stack.pop() != 10)
        return false;

      // Verify stack is empty
      if (!stack.isEmpty())
        return false;

      return true;
    } catch (Exception e) {
      return false;
    }
  }


  /**
   * Test the behavior of adding an element to the queue.
   * 
   * @return true if element is correctly added to the queue, false otherwise
   */
  public static boolean testQueueAdd() {
    try {
      // Create a new queue and enqueue elements
      LinkedQueue<Integer> queue = new LinkedQueue<>();
      queue.enqueue(10); // First in
      queue.enqueue(20); // Second in
      queue.enqueue(30); // Third in

      // Use getList to retrieve the elements in the queue as an ArrayList
      ArrayList<Integer> actualList = queue.getList();
      ArrayList<Integer> expectedList = new ArrayList<>();
      expectedList.add(10);
      expectedList.add(20);
      expectedList.add(30);

      // Verify the actual order matches the expected order
      if (!actualList.equals(expectedList))
        return false;

      // Verify the front element using peek
      if (queue.peek() != 10)
        return false;

      // Ensure the size is as expected
      if (queue.size() != 3)
        return false;

      return true; // All tests passed
    } catch (Exception e) {
      return false; // Fail if any unexpected exception occurs
    }
  }



  /**
   * Test the behavior of removing an element from the queue.
   * 
   * @return true if element is correctly removed from the queue, false otherwise
   */
  public static boolean testQueueRemove() {
    try {
      LinkedQueue<Integer> queue = new LinkedQueue<>();
      queue.enqueue(10);
      queue.enqueue(20);
      queue.enqueue(30);

      // Verify the correct order of removal (FIFO)
      if (queue.dequeue() != 10 || queue.dequeue() != 20 || queue.dequeue() != 30)
        return false;

      // Verify queue is empty
      if (!queue.isEmpty())
        return false;

      return true;
    } catch (Exception e) {
      return false;
    }
  }


  /**
   * Test the behavior of peeking at the top element (for stack) and the front element (for queue).
   * 
   * @return true if the correct element returned for both data structures, false otherwise
   */
  public static boolean testPeek() {
    try {
      // Test LinkedStack
      LinkedStack<Integer> stack = new LinkedStack<>();
      stack.push(10);
      stack.push(20); // Stack: [20, 10] (top -> bottom)

      int peekedValue = stack.peek(); // Peek should return the top value (20)
      if (peekedValue != 20)
        return false; // Fail if peek doesn't return the correct value

      // Verify stack structure remains unchanged after peek
      int nextPeek = stack.peek(); // Another peek should still return 20
      if (nextPeek != 20)
        return false; // Fail if the stack was modified

      // Verify stack functionality after peek
      int poppedValue = stack.pop(); // Pop should still return 20
      if (poppedValue != 20)
        return false; // Fail if the top element is not as expected

      if (stack.peek() != 10)
        return false; // Next top element should be 10

      // Test LinkedQueue
      LinkedQueue<Integer> queue = new LinkedQueue<>();
      queue.enqueue(10);
      queue.enqueue(20); // Queue: [10, 20] (front -> rear)

      int frontValue = queue.peek(); // Peek should return the front value (10)
      if (frontValue != 10)
        return false; // Fail if peek doesn't return the correct value

      // Verify queue structure remains unchanged after peek
      int nextFront = queue.peek(); // Another peek should still return 10
      if (nextFront != 10)
        return false; // Fail if the queue was modified

      // Verify queue functionality after peek
      int dequeuedValue = queue.dequeue(); // Dequeue should still return 10
      if (dequeuedValue != 10)
        return false; // Fail if the front element is not as expected

      if (queue.peek() != 20)
        return false; // Next front element should be 20

      return true; // All tests passed
    } catch (Exception e) {
      return false; // Fail if any exception occurs
    }
  }



  /**
   * This method tests whether the contains method correctly identifies whether a specific element
   * exists in a stack and a queue.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testContains() {
    try {
      // Test stack contains
      LinkedStack<Integer> stack = new LinkedStack<>();
      stack.push(10);
      stack.push(20);
      if (!stack.contains(10) || !stack.contains(20) || stack.contains(30))
        return false;

      // Test queue contains
      LinkedQueue<Integer> queue = new LinkedQueue<>();
      queue.enqueue(30);
      queue.enqueue(40);
      if (!queue.contains(30) || !queue.contains(40) || queue.contains(50))
        return false;

      return true;
    } catch (Exception e) {
      return false;
    }
  }


  /**
   * Test the behavior of getting the list of elements in the stack and queue.
   * 
   * @return true if method returns a correctly ordered list for both data structures, false
   *         otherwise
   */
  public static boolean testGetList() {
    try {
      // Test stack getList
      LinkedStack<Integer> stack = new LinkedStack<>();
      stack.push(10);
      stack.push(20);
      stack.push(30);
      ArrayList<Integer> stackList = stack.getList();

      // Manually creating the expected list
      ArrayList<Integer> expectedStackList = new ArrayList<>();
      expectedStackList.add(30);
      expectedStackList.add(20);
      expectedStackList.add(10);

      if (!stackList.equals(expectedStackList))
        return false;

      // Test queue getList
      LinkedQueue<Integer> queue = new LinkedQueue<>();
      queue.enqueue(10);
      queue.enqueue(20);
      queue.enqueue(30);
      ArrayList<Integer> queueList = queue.getList();

      // Manually creating the expected list
      ArrayList<Integer> expectedQueueList = new ArrayList<>();
      expectedQueueList.add(10);
      expectedQueueList.add(20);
      expectedQueueList.add(30);

      if (!queueList.equals(expectedQueueList))
        return false;

      return true;
    } catch (Exception e) {
      return false;
    }
  }



  /**
   * Tests adding songs to an Album and verifies the size and content. Checks if songs are correctly
   * added in LIFO order.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAddSongToAlbum() {
    try {
      // Create a new album
      Album album = new Album("Greatest Hits");

      // Create songs
      Song song1 = new Song("Song One", "Artist A");
      Song song2 = new Song("Song Two", "Artist B");
      Song song3 = new Song("Song Three", "Artist C");

      // Add songs to the album
      album.addSong(song1);
      album.addSong(song2);
      album.addSong(song3);

      // Check the size of the album (should be 3)
      if (album.size() != 3)
        return false;

      // Verify the first song is song3 (LIFO order)
      if (!album.firstSong().equals(song3))
        return false;

      return true;
    } catch (Exception e) {
      return false;
    }
  }


  /**
   * Tests removing a song from an Album and verifies the size and content after removal. Checks if
   * songs are correctly removed in LIFO order.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testRemoveSongFromAlbum() {
    try {
      // Create a new album
      Album album = new Album("Greatest Hits");

      // Create songs
      Song song1 = new Song("Song One", "Artist A");
      Song song2 = new Song("Song Two", "Artist B");
      Song song3 = new Song("Song Three", "Artist C");

      // Add songs to the album
      album.addSong(song1);
      album.addSong(song2);
      album.addSong(song3);

      // Remove a song and check the size
      album.removeSong(); // Removes song3
      if (album.size() != 2)
        return false;

      // Verify the first song is now song2
      if (!album.firstSong().equals(song2))
        return false;

      // Remove another song and check the size
      album.removeSong(); // Removes song2
      if (album.size() != 1)
        return false;

      // Verify the first song is now song1
      if (!album.firstSong().equals(song1))
        return false;

      // Remove the last song
      album.removeSong(); // Removes song1
      if (album.size() != 0)
        return false;

      // Test exception when removing from an empty album
      try {
        album.removeSong(); // Should throw NoSuchElementException
        return false; // Fail if no exception is thrown
      } catch (NoSuchElementException e) {
        // Pass if the correct exception is thrown
      }

      return true; // All tests passed
    } catch (Exception e) {
      return false; // Fail if any unexpected exception occurs
    }
  }


  /**
   * Tests the toString method of the Album class. Verifies that the returned string correctly
   * represents all songs in LIFO order.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAlbumToString() {
    try {
      // Create a new album
      Album album = new Album("Greatest Hits");

      // Create songs
      Song song1 = new Song("Song One", "Artist A");
      Song song2 = new Song("Song Two", "Artist B");
      Song song3 = new Song("Song Three", "Artist C");

      // Add songs to the album
      album.addSong(song1);
      album.addSong(song2);
      album.addSong(song3);

      // Get the string representation of the album
      String albumString = album.toString();

      // Verify that the string representation contains all songs in LIFO order
      String expected =
          "Greatest Hits\n" + song3.toString() + "\n" + song2.toString() + "\n" + song1.toString();

      if (!albumString.equals(expected))
        return false;

      return true;
    } catch (Exception e) {
      return false;
    }
  }


  /**
   * Tests adding a song to the Jukebox and verifies the queue contents and size.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAddSongToJukebox() {
    try {
      // Create a new JukeBox with a capacity of 3
      JukeBox jukeBox = new JukeBox(3);

      // Create songs
      Song song1 = new Song("Song One", "Artist A");
      Song song2 = new Song("Song Two", "Artist B");

      // Add songs to the jukeBox
      jukeBox.addSongToQueue(song1);
      jukeBox.addSongToQueue(song2);

      // Check the size of the jukeBox (should be 2)
      if (jukeBox.size() != 2)
        return false;

      // Verify the contents of the queue (should be song1 and song2)
      if (!jukeBox.toString().contains(song1.toString())
          || !jukeBox.toString().contains(song2.toString()))
        return false;

      return true;
    } catch (Exception e) {
      return false;
    }
  }


  /**
   * Tests adding an album to the Jukebox and verifies the queue contents and size.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAddAlbumToJukebox() {
    try {
      // -------- Test Case 1: Adding a full album when space is sufficient --------
      // Create a new JukeBox with a capacity of 5
      JukeBox jukeBox1 = new JukeBox(5);

      // Create an album and add songs to it
      Album album1 = new Album("Greatest Hits");
      Song song1 = new Song("Song One", "Artist A");
      Song song2 = new Song("Song Two", "Artist B");
      Song song3 = new Song("Song Three", "Artist C");

      album1.addSong(song1);
      album1.addSong(song2);
      album1.addSong(song3);

      // Add the album to the jukebox
      jukeBox1.addAlbumToQueue(album1);

      // Check the size of the jukebox (should be 3)
      if (jukeBox1.size() != 3)
        return false;

      // Verify the contents of the queue (should contain all 3 songs)
      if (!jukeBox1.toString().contains(song1.toString())
          || !jukeBox1.toString().contains(song2.toString())
          || !jukeBox1.toString().contains(song3.toString()))
        return false;

      // Verify the album remains unchanged
      if (album1.size() != 3 || !album1.firstSong().equals(song3))
        return false;

      // -------- Test Case 2: Adding a partial album when space is limited --------
      // Create a new JukeBox with a capacity of 2
      JukeBox jukeBox2 = new JukeBox(2);

      // Create another album with 3 songs
      Album album2 = new Album("Top Tracks");
      Song song4 = new Song("Song Four", "Artist D");
      Song song5 = new Song("Song Five", "Artist E");
      Song song6 = new Song("Song Six", "Artist F");

      album2.addSong(song4);
      album2.addSong(song5);
      album2.addSong(song6);

      // Add the album to the jukebox
      jukeBox2.addAlbumToQueue(album2);

      // Check the size of the jukebox (should be 2, the max capacity)
      if (jukeBox2.size() != 2)
        return false;

      // Verify that only the first 2 songs were added
      if (!jukeBox2.toString().contains(song4.toString())
          || !jukeBox2.toString().contains(song5.toString()))
        return false;

      // Verify that the third song was NOT added
      if (jukeBox2.toString().contains(song6.toString()))
        return false;

      // Verify the album remains unchanged and the remaining song is in the album
      if (album2.size() != 3 || !album2.firstSong().equals(song6))
        return false;

      return true; // All test cases passed
    } catch (Exception e) {
      return false; // Fail the test if any exception occurs
    }
  }



  /**
   * Tests playing a song from the JukeboxQueue. Verifies that the song is removed from the queue
   * after playback.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testPlaySongFromJukebox() {
    try {
      // Create a new JukeBox with a capacity of 3
      JukeBox jukeBox = new JukeBox(3);

      // Create songs
      Song song1 = new Song("Song One", "Artist A");
      Song song2 = new Song("Song Two", "Artist B");

      // Add songs to the jukeBox
      jukeBox.addSongToQueue(song1);
      jukeBox.addSongToQueue(song2);

      // Play the first song and check the queue size
      Song playedSong = jukeBox.playSong();
      if (!playedSong.equals(song1))
        return false; // Check if the played song is song1
      if (jukeBox.size() != 1)
        return false; // Size should now be 1

      // Play the second song and check the queue size
      playedSong = jukeBox.playSong();
      if (!playedSong.equals(song2))
        return false; // Check if the played song is song2
      if (jukeBox.size() != 0)
        return false; // Size should now be 0

      return true;
    } catch (Exception e) {
      return false;
    }
  }


  /**
   * Tests shuffling the JukeBox queue. Verifies that the songs are reordered randomly after the
   * operation.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testJukeboxShuffle() {
    try {
      // Create a new JukeBox with a capacity of 5
      JukeBox jukeBox = new JukeBox(5);

      // Create songs
      Song song1 = new Song("Song One", "Artist A");
      Song song2 = new Song("Song Two", "Artist B");
      Song song3 = new Song("Song Three", "Artist C");

      // Add songs to the jukeBox
      jukeBox.addSongToQueue(song1);
      jukeBox.addSongToQueue(song2);
      jukeBox.addSongToQueue(song3);

      // Get the initial queue order
      String initialQueue = jukeBox.toString();

      // Shuffle the songs in the queue
      jukeBox.shuffleSongQueue();

      // Get the shuffled queue order
      String shuffledQueue = jukeBox.toString();
      System.out.println(initialQueue);
      System.out.println(shuffledQueue);
      // Check if the order has changed (should not be the same)
      if (initialQueue.equals(shuffledQueue))
        return false;

      return true;
    } catch (Exception e) {
      return false;
    }
  }


  public static void main(String[] args) {
    // Running and printing results for all the tests



boolean test1 = testStackAdd();
    System.out.println("testStackAdd: " + (test1 ? "PASS" : "FAIL"));

    boolean test2 = testStackRemove();
    System.out.println("testStackRemove: " + (test2 ? "PASS" : "FAIL"));

    boolean test3 = testQueueAdd();
    System.out.println("testQueueAdd: " + (test3 ? "PASS" : "FAIL"));

    boolean test4 = testQueueRemove();
    System.out.println("testQueueRemove: " + (test4 ? "PASS" : "FAIL"));

    boolean test5 = testPeek();
    System.out.println("testPeek: " + (test5 ? "PASS" : "FAIL"));

    boolean test6 = testContains();
    System.out.println("testContains: " + (test6 ? "PASS" : "FAIL"));

    boolean test7 = testGetList();
    System.out.println("testGetList: " + (test7 ? "PASS" : "FAIL"));

    boolean test8 = testAddSongToAlbum();
    System.out.println("testAddSongToAlbum: " + (test8 ? "PASS" : "FAIL"));

    boolean test9 = testRemoveSongFromAlbum();
    System.out.println("testRemoveSongFromAlbum: " + (test9 ? "PASS" : "FAIL"));

    boolean test10 = testAlbumToString();
    System.out.println("testAlbumToString: " + (test10 ? "PASS" : "FAIL"));

    boolean test11 = testAddSongToJukebox();
    System.out.println("testAddSongToJukebox: " + (test11 ? "PASS" : "FAIL"));

    boolean test12 = testAddAlbumToJukebox();
    System.out.println("testAddAlbumToJukebox: " + (test12 ? "PASS" : "FAIL"));

    boolean test13 = testPlaySongFromJukebox();
    System.out.println("testPlaySongFromJukebox: " + (test13 ? "PASS" : "FAIL"));

    boolean test14 = testJukeboxShuffle();
    System.out.println("testJukeboxShuffle: " + (test14 ? "PASS" : "FAIL"));

    System.out.println("ALL TESTS: " + (test1 && test2 && test3 && test4 && test5 && test6 && test7
        && test8 && test9 && test10 && test11 && test12 && test13 && test14 ? "PASS" : "FAIL"));
  }
}
