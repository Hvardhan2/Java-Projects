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

public class LeaderboardTester {

  /////////////////////////////////////////// COMPARE TO ///////////////////////////////////////////

  public static boolean testPlayerCompareTo() {
    boolean test1 = testCompareToDiffScore();
    boolean test2 = testCompareToSameScoreDiffName();
    boolean test3 = testCompareToEqual();
    if (!test1)
      System.out.print("diffScore FAIL ");
    if (!test2)
      System.out.print("diffName FAIL ");
    if (!test3)
      System.out.print("equals FAIL ");
    return test1 && test2 && test3;
  }

  private static boolean testCompareToDiffScore() {
    Player Ironman = new Player("Ironman", 2000);
    Player Spiderman = new Player("Spiderman", 1500);


    return Ironman.compareTo(Spiderman) > 0 && Spiderman.compareTo(Ironman) < 0;
  }

  private static boolean testCompareToSameScoreDiffName() {
    // TODO: compare players with the same score but different names and verify the results
    Player Ironman = new Player("Ironman");
    Player Spiderman = new Player("Spiderman");


    return Ironman.compareTo(Spiderman) < 0 && Spiderman.compareTo(Ironman) > 0;
  }

  private static boolean testCompareToEqual() {
    // TODO: compare players with the same score and name and verify the results
    Player Ironman = new Player("Ironman");
    Player Ironman2 = new Player("Ironman");


    return Ironman.compareTo(Ironman2) == 0 && Ironman2.compareTo(Ironman) == 0;

  }

  ///////////////////////////////////////// LOOKUP:NAME /////////////////////////////////////////
  public static boolean testNameLookup() {
    boolean test1 = testLookupRoot();
    boolean test2 = testLookupLeft();
    boolean test3 = testLookupRight();
    boolean test4 = testLookupNotPresent();
    if (!test1)
      System.out.print("lookupRoot FAIL ");
    if (!test2)
      System.out.print("lookupLeft FAIL ");
    if (!test3)
      System.out.print("lookupRight FAIL ");
    if (!test4)
      System.out.print("lookupNotPresent FAIL");
    return test1 && test2 && test3 && test4;
  }

  // HINT: for these testers, add one Player at the root and then build the rest of the tree
  // manually
  // using the reference returned by getRoot(), so that you are not relying on the correctness of
  // the addPlayer() method.


  private static boolean testLookupRoot() {
  
   Player player1 = new Player("Iron Man", 100); // Root player
   Player player2 = new Player("Captain America", 90); // Left child of Iron Man
   Player player3 = new Player("Thor", 110); // Right child of Iron Man
   Player player4 = new Player("Hulk", 80); // Left child of Captain America
  
   // Step 2: Create the Leaderboard and add the first player (Alice)
   Leaderboard leaderboard = new Leaderboard();
   leaderboard.addPlayer(player1); // Alice will be the root node
  
   // Step 3: Manually add the rest of the tree nodes
   BSTNode<Player> root = leaderboard.getRoot();
  
   // Add Bob to the left of Alice
   BSTNode<Player> node2 = new BSTNode<>(player2);
   root.setLeft(node2);
  
   // Add Charlie to the right of Alice
   BSTNode<Player> node3 = new BSTNode<>(player3);
   root.setRight(node3);
  
   // Add Dave to the left of Bob
   BSTNode<Player> node4 = new BSTNode<>(player4);
   node2.setLeft(node4);
   System.out.println("Root: " + root.getData().getName()); // Should print "Iron Man"
   System.out.println("Left child of root: " + root.getLeft().getData().getName()); // Should

   // "Captain
   // America"
   System.out.println("Right child of root: " + root.getRight().getData().getName()); // Should
   // print
   // "Thor"
   System.out.println("Left child of Captain America: " + node2.getLeft().getData().getName()); //
  
  
   Player player = leaderboard.lookup("Iron Man"); // Look up Iron Man by name
   return player != null && player.getName().equals("Iron Man");
   }

  private static boolean testLookupLeft() {
    Player player1 = new Player("Iron Man", 100); // Root player
    Player player2 = new Player("Captain America", 90); // Left child of Iron Man
    Player player3 = new Player("Thor", 110); // Right child of Iron Man
    Player player4 = new Player("Hulk", 80); // Left child of Captain America

    // Step 2: Create the Leaderboard and add the first player (Alice)
    Leaderboard leaderboard = new Leaderboard();
    leaderboard.addPlayer(player1); // Alice will be the root node

    // Step 3: Manually add the rest of the tree nodes
    BSTNode<Player> root = leaderboard.getRoot();

    // Add Bob to the left of Alice
    BSTNode<Player> node2 = new BSTNode<>(player2);
    root.setLeft(node2);

    // Add Charlie to the right of Alice
    BSTNode<Player> node3 = new BSTNode<>(player3);
    root.setRight(node3);

    // Add Dave to the left of Bob
    BSTNode<Player> node4 = new BSTNode<>(player4);
    node2.setLeft(node4);
    // TODO: look up a player stored in the left subtree

    Player player = leaderboard.lookup("Captain America"); // Look up Captain America by name
    return player != null && player.getName().equals("Captain America");


  }

  private static boolean testLookupRight() {
   Player player1 = new Player("Iron Man", 100); // Root player
   Player player2 = new Player("Captain America", 90); // Left child of Iron Man
   Player player3 = new Player("Thor", 110); // Right child of Iron Man
   Player player4 = new Player("Hulk", 80); // Left child of Captain America
  
   // Step 2: Create the Leaderboard and add the first player (Alice)
   Leaderboard leaderboard = new Leaderboard();
   leaderboard.addPlayer(player1); // Alice will be the root node
  
   // Step 3: Manually add the rest of the tree nodes
   BSTNode<Player> root = leaderboard.getRoot();
  
   // Add Bob to the left of Alice
   BSTNode<Player> node2 = new BSTNode<>(player2);
   root.setLeft(node2);
  
   // Add Charlie to the right of Alice
   BSTNode<Player> node3 = new BSTNode<>(player3);
   root.setRight(node3);
  
   // Add Dave to the left of Bob
   BSTNode<Player> node4 = new BSTNode<>(player4);
   node2.setLeft(node4);
   System.out.println("Root: " + root.getData().getName()); // Should print "Iron Man"
   System.out.println("Left child of root: " + root.getLeft().getData().getName()); // Should print
   // "Captain
   // America"
   System.out.println("Right child of root: " + root.getRight().getData().getName()); // Should
   // print
   // "Thor"
   System.out.println("Left child of Captain America: " + node2.getLeft().getData().getName());
   // TODO: look up a player stored in the right subtree
   Player player = leaderboard.lookup("Thor"); // Look up Thor by name
   return player != null && player.getName().equals("Thor");
   }

  private static boolean testLookupNotPresent() {
   Player player1 = new Player("Iron Man", 100); // Root player
   Player player2 = new Player("Captain America", 90); // Left child of Iron Man
   Player player3 = new Player("Thor", 110); // Right child of Iron Man
   Player player4 = new Player("Hulk", 80); // Left child of Captain America
  
   // Step 2: Create the Leaderboard and add the first player (Alice)
   Leaderboard leaderboard = new Leaderboard();
   leaderboard.addPlayer(player1); // Alice will be the root node
  
   // Step 3: Manually add the rest of the tree nodes
   BSTNode<Player> root = leaderboard.getRoot();
  
   // Add Bob to the left of Alice
   BSTNode<Player> node2 = new BSTNode<>(player2);
   root.setLeft(node2);
  
   // Add Charlie to the right of Alice
   BSTNode<Player> node3 = new BSTNode<>(player3);
   root.setRight(node3);
  
   // Add Dave to the left of Bob
   BSTNode<Player> node4 = new BSTNode<>(player4);
   node2.setLeft(node4);
   System.out.println("Root: " + root.getData().getName()); // Should print "Iron Man"
   System.out.println("Left child of root: " + root.getLeft().getData().getName()); // Should print
   // "Captain
   // America"
   System.out.println("Right child of root: " + root.getRight().getData().getName()); // Should
   // print
   // "Thor"
   System.out.println("Left child of Captain America: " + node2.getLeft().getData().getName());
   // TODO: look up the name of a player who is not present in the tree (NOT on an empty tree!)
   Player player = leaderboard.lookup("Black Widow"); // Look up a non-existent player
   return player == null;
   }

  //////////////////////////////////////////// ADD ////////////////////////////////////////////

  public static boolean testAdd() {
    boolean test1 = testAddPlayerEmpty();
    boolean test2 = testAddPlayer();
    boolean test3 = testAddPlayerDuplicate();
    if (!test1)
      System.out.print("addEmpty FAIL ");
    if (!test2)
      System.out.print("addPlayer FAIL ");
    if (!test3)
      System.out.print("addDuplicate FAIL ");
    return test1 && test2 && test3;
  }

  private static boolean testAddPlayerEmpty() {
    Leaderboard leaderboard = new Leaderboard();
    Player player = new Player("Alice", 100);
    boolean result = leaderboard.addPlayer(player);

    // Check root, size, and result
    if (!result || leaderboard.size() != 1 || !leaderboard.getRoot().getData().equals(player)) {
      return false;
    }
    return true;
  }


  private static boolean testAddPlayer() {
    Leaderboard leaderboard = new Leaderboard();
    Player player1 = new Player("Alice", 100);
    Player player2 = new Player("Bob", 200);

    leaderboard.addPlayer(player1);
    boolean result = leaderboard.addPlayer(player2);

    // Validate tree structure and size
    if (!result || leaderboard.size() != 2
        || !leaderboard.getRoot().getRight().getData().equals(player2)) {
      return false;
    }
    return true;
  }


  private static boolean testAddPlayerDuplicate() {
    Leaderboard leaderboard = new Leaderboard();
    Player player1 = new Player("Alice", 100);
    Player player2 = new Player("Alice", 100); // Duplicate

    leaderboard.addPlayer(player1);
    boolean result = leaderboard.addPlayer(player2);

    // Validate no changes
    if (result || leaderboard.size() != 1 || !leaderboard.getRoot().getData().equals(player1)) {
      return false;
    }
    return true;
  }

  //////////////////////////////////////////// REMOVE ////////////////////////////////////////////

  public static boolean testRemove() {
    boolean test1 = testRemoveLeaf();
    boolean test2 = testRemoveOneChild();
    boolean test3 = testRemoveTwoChildren();
    boolean test4 = testRemoveNotInTree();
    if (!test1)
      System.out.print("remove FAIL ");
    if (!test2)
      System.out.print("removeOneChild FAIL ");
    if (!test3)
      System.out.print("removeTwoChildren FAIL ");
    if (!test4)
      System.out.print("removeNotInTree FAIL ");
    return test1 && test2 && test3 && test4;
  }

  private static boolean testRemoveLeaf() {
    // TODO: verify that removePlayer() correctly removes a leaf node from the tree
    Leaderboard leaderboard = new Leaderboard();
    Player player1 = new Player("Alice", 10);
    leaderboard.addPlayer(player1);

    // Try removing a leaf (a node with no children)
    boolean result = leaderboard.removePlayer(player1);
    return result && leaderboard.count() == 0 && leaderboard.lookup("Alice") == null;
  }

  private static boolean testRemoveOneChild() {
    // TODO: verify that removePlayer() correctly removes a player with ONE child
    // Each time you remove a player, make sure that:
    // (1) the method returns true
    // (2) the size and count have decreased correctly
    // (3) the output of prettyPrint() is the tree that you expect (you may do this one visually)
    Leaderboard leaderboard = new Leaderboard();
    Player player1 = new Player("Alice", 10);
    Player player2 = new Player("Bob", 15);
    leaderboard.addPlayer(player1);
    leaderboard.addPlayer(player2);

    // Remove a player with one child (Bob has one child)
    boolean result = leaderboard.removePlayer(player2);
    return result && leaderboard.count() == 1 && leaderboard.lookup("Bob") == null;
  }

  private static boolean testRemoveTwoChildren() {
    // TODO: verify that removePlayer() correctly removes a player with TWO children
    // Each time you remove a player, make sure that:
    // (1) the method returns true
    // (2) the size and count have decreased correctly
    // (3) the output of prettyPrint() is the tree that you expect (you may do this one visually)
    Leaderboard leaderboard = new Leaderboard();
    Player player1 = new Player("Alice", 10);
    Player player2 = new Player("Bob", 15);
    Player player3 = new Player("Charlie", 20);
    leaderboard.addPlayer(player1);
    leaderboard.addPlayer(player2);
    leaderboard.addPlayer(player3);

    // Remove a player with two children (Bob has two children)
    boolean result = leaderboard.removePlayer(player2);
    return result && leaderboard.count() == 2 && leaderboard.lookup("Bob") == null;
  }

  private static boolean testRemoveNotInTree() {
    // TODO: verify that removing a player not in the tree returns false, does not modify the BST,
    // and does not cause an exception
    Leaderboard leaderboard = new Leaderboard();
    Player player1 = new Player("Alice", 10);
    leaderboard.addPlayer(player1);

    // Try removing a player not in the tree
    Player player2 = new Player("Bob", 15);
    boolean result = leaderboard.removePlayer(player2);
    return !result && leaderboard.count() == 1 && leaderboard.lookup("Bob") == null;
  }

  //////////////////////////////////////////// GET NEXT ////////////////////////////////////////////

  public static boolean testGetNext() {
    boolean test1 = testGetNextAfterRoot();
    boolean test2 = testGetNextAfterLeftSubtree();
    boolean test3 = testGetNextAfterRightSubtree();
    if (!test1)
      System.out.print("afterRoot FAIL ");
    if (!test2)
      System.out.print("afterLeft FAIL ");
    if (!test3)
      System.out.print("afterRight FAIL ");
    return test1 && test2 && test3;
  }

  private static boolean testGetNextAfterRoot() {
    // TODO: verify that next() returns the correct Player when passed the Player in the root node
    Leaderboard leaderboard = new Leaderboard();
    Player p1 = new Player("Alice", 100); // Player 1 with score 100
    Player p2 = new Player("Bob", 200); // Player 2 with score 200
    Player p3 = new Player("Charlie", 150); // Player 3 with score 150

    leaderboard.addPlayer(p1);
    leaderboard.addPlayer(p2);
    leaderboard.addPlayer(p3);

    // Root player should be Alice (score 100), and the next player should be Charlie (score 150)
    Player nextAfterRoot = leaderboard.next(p1);
    if (nextAfterRoot != null && nextAfterRoot.getName().equals("Charlie")) {
      return true; // Correct player found
    } else {
      System.out.println("Test failed: next after root should be Charlie.");
      return false;
    }
  }

  private static boolean testGetNextAfterLeftSubtree() {
    // TODO: verify that next() returns the correct Player when the correct value is in the left
    // subtree of the leaderboard
    Leaderboard leaderboard = new Leaderboard();
    Player p1 = new Player("Alice", 100); // Player 1 with score 100
    Player p2 = new Player("Bob", 200); // Player 2 with score 200
    Player p3 = new Player("Charlie", 150); // Player 3 with score 150

    leaderboard.addPlayer(p1);
    leaderboard.addPlayer(p2);
    leaderboard.addPlayer(p3);

    // p1 is the root with score 100, and p3 is in the left subtree.
    // next(p3) should return Bob (score 200), since Bob is the next player.
    Player nextAfterLeftSubtree = leaderboard.next(p3);
    if (nextAfterLeftSubtree != null && nextAfterLeftSubtree.getName().equals("Bob")) {
      return true; // Correct player found
    } else {
      System.out.println("Test failed: next after left subtree should be Bob.");
      return false;
    }
  }

  private static boolean testGetNextAfterRightSubtree() {
    // TODO: verify that next() returns the correct Player when the correct value is in the right
    // subtree of the leaderboard
    Leaderboard leaderboard = new Leaderboard();
    Player p1 = new Player("Alice", 100); // Player 1 with score 100
    Player p2 = new Player("Bob", 200); // Player 2 with score 200
    Player p3 = new Player("Charlie", 150); // Player 3 with score 150

    leaderboard.addPlayer(p1);
    leaderboard.addPlayer(p2);
    leaderboard.addPlayer(p3);

    // p2 is in the right subtree of p1, and the next player should be the smallest
    // player in the right subtree that is larger than p2, which does not exist. Hence, next(p2)
    // should return null.
    Player nextAfterRightSubtree = leaderboard.next(p2);
    if (nextAfterRightSubtree == null) {
      return true; // No next player found, as expected.
    } else {
      System.out.println("Test failed: next after right subtree should be null.");
      return false;
    }
  }

  //////////////////////////////////////////// MAIN ////////////////////////////////////////////

  public static void main(String[] args) {
    System.out.print("Player compareTo(): ");
    System.out.println(testPlayerCompareTo() ? "PASS" : "");

    // System.out.print("Leaderboard lookup(): ");
    // System.out.println(testNameLookup() ? "PASS" : "");

    System.out.print("Leaderboard add(): ");
    System.out.println(testAdd() ? "PASS" : "");

    System.out.print("Leaderboard remove(): ");
    System.out.println(testRemove() ? "PASS" : "");

    System.out.print("Leaderboard next(): ");
    System.out.println(testGetNext() ? "PASS" : "");
  }

}
