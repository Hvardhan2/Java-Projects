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
// No pair programming for this project
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources: (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents a binary search tree of Player objects with various scores. We sort the
 * Players by a combination of their scores and names (see the Player.compareTo() method).
 * 
 * The class provides methods to insert, search, and remove Player objects from the tree, as well
 * as a couple of methods for viewing its contents in printed form.
 * 
 * TODO: modify this class so that objects created from this class may be used in an enhanced for-loop
 */
public class Leaderboard implements Iterable<Player> {
  
  /** The root node of the binary tree */
  private BSTNode<Player> root;
  
  /** The current number of nodes that have been added to the tree */
  private int size;
  
  /**
   * Initializes an empty leaderboard
   */
  public Leaderboard() {
    this.size = 0;
    this.root = null;
  }
  
  /**
   * An accessor method for the size variable. If everything has been implemented properly, this
   * should produce the same results as count() below, but in O(1) time instead of O(N).
   * @return the number of nodes expected to be present in this BST
   */
  public int size() { return this.size; }
  
  /**
   * Returns the root node of the binary tree, for testing purposes. You can use this reference to
   * manually build a tree structure without using the add method: save this value in a variable in
   * your tester method and use setLeft/setRight to add nodes manually.
   * 
   * @return a reference to the root node of the binary tree
   */
  protected BSTNode<Player> getRoot() { return this.root; }
  
  //////////////////////////////////////////// COUNT ////////////////////////////////////////////
  
  /**
   * PROVIDED:
   * Returns the number of players currently present in the BST.
   * @return the number of players in the leaderboard
   */
  public int count() {
    return countHelper(root);
  }
  
  /**
   * THIS METHOD MUST BE IMPLEMENTED _RECURSIVELY_
   * 
   * Returns the size of the subtree rooted at the given node.
   * @param node the root of this subtree
   * @return the number of players in this subtree
   */
  protected int countHelper(BSTNode<Player> node) {
    if (node == null) return 0;
    
    return 1 + countHelper(node.getLeft()) + countHelper(node.getRight());
  }
  
  ///////////////////////////////////////// LOOKUP: NAME /////////////////////////////////////////
  
  /**
   * PROVIDED:
   * Returns a Player reference with the given name. You may assume that there is AT MOST ONE Player
   * with the name in the tree.
   * @param name the name of the player to find
   * @return a reference to the Player with the given name, or null if no Player with this name is
   * present in the tree
   */
  public Player lookup(String name) {
    if (root == null) return null;
    
    return lookupHelper(root, name);
  }
  
  /**
   * THIS METHOD MUST BE IMPLEMENTED _RECURSIVELY_
   * 
   * Exhaustively searches the Leaderboard tree for a Player with the given name. Note that the tree
   * is ordered primarily by SCORE and binary search cannot be used for this lookup!
   * @param node the root of this subtree
   * @param name the name of the player to find
   * @return a reference to the Player with the given name, or null if no Player with this name is
   * present in the subtree
   */
  protected Player lookupHelper(BSTNode<Player> node, String name) {
    if (node == null) {
      return null; // Base case: reached a null node
    }

    Player currentPlayer = node.getData();

    // Check if the current player's name matches
    if (currentPlayer != null && currentPlayer.getName().equals(name)) {
      return currentPlayer; // Player found
    }

    // Recursively search both subtrees (because we can't rely on score ordering for name)
    Player leftResult = lookupHelper(node.getLeft(), name);
    if (leftResult != null) {
      return leftResult; // Player found in the left subtree
    }

    // If not found on the left, check the right subtree
    return lookupHelper(node.getRight(), name);
  }

  

  //////////////////////////////////////////// ADD ////////////////////////////////////////////
  
  /**
   * PROVIDED:
   * Adds a new Player to this Leaderboard.
   * 
   * If the root is null, creates a new node with the given Player and sets it as the root. Otherwise,
   * calls the addPlayerHelper() method to recursively add the Player to the tree. If the Player is
   * already in the tree, they will NOT be added again (return false).
   * @param player the player to add to the tree
   * @return true if the Player was successfully added, false otherwise
   */
  public boolean addPlayer(Player player) {
    if (root == null) {
      root = new BSTNode<Player>(player);
      size++;
      return true;
    }
    return addPlayerHelper(root, player);
  }
  
  /**
   * THIS METHOD MUST BE IMPLEMENTED _RECURSIVELY_
   * 
   * Adds a new player to the leaderboard rooted at the given node. If the player is already in the
   * tree, they will NOT be added again.
   * 
   * @param node the root node of the tree to add the player to
   * @param player the player to add to the tree
   * @return true if they player was successfully added, false otherwise
   */
  protected boolean addPlayerHelper(BSTNode<Player> node, Player player) {
    if (node == null) {
      // We should never reach here if the tree was not empty
      return false;
    }

    // If the player already exists (based on comparison), return false
    if (node.getData().compareTo(player) == 0) {
      return false;
    }

    // Determine whether to go left or right
    if (player.compareTo(node.getData()) < 0) {
      if (node.getLeft() == null) {
        node.setLeft(new BSTNode<>(player));
        size++;
        return true;
      }
      return addPlayerHelper(node.getLeft(), player);
    } else {
      if (node.getRight() == null) {
        node.setRight(new BSTNode<>(player));
        size++;
        return true;
      }
      return addPlayerHelper(node.getRight(), player);
    }
  }

  
  //////////////////////////////////////////// REMOVE //////////////////////////////////////////// 
  
  /**
   * PROVIDED:
   * Removes the given Player from this Leaderboard.
   * 
   * This method handles the case where the tree is empty, and otherwise uses the helper method to
   * recursively remove nodes from the tree.
   * @param player the player to remove from the tree
   * @return true if the player was successfully removed, false otherwise
   */
  public boolean removePlayer(Player player) {
    // remove from an empty tree
    if (root == null) return false;
    
    try {   
      root = removePlayerHelper(root, player);
      size--;
      return true;
    } catch (NoSuchElementException e) {
      // the player was not found in this tree
      return false;
    }
  }
  
  /**
   * THIS METHOD MUST BE IMPLEMENTED _RECURSIVELY_
   * 
   * Removes a player from the BST rooted at the given node and returns the new root of the subtree
   * (which may or may not be the same as the old root). When you have a choice of replacements for
   * a removed node, this method should use the NEXT player - as there is a helper method to find 
   * that value.
   * @param node the current root node of the subtree
   * @param player the player to remove from the tree
   * @return the root node of this subtree after the player has been removed (may be the same as the
   * old root of this subtree)
   * @throws NoSuchElementException if the player is not found in the tree
   */
  protected BSTNode<Player> removePlayerHelper(BSTNode<Player> node, Player player) {
    if (node == null) {
      throw new NoSuchElementException("Player not found in the tree");
    }

    // Compare the player to decide whether to go left or right
    if (player.compareTo(node.getData()) < 0) {
      node.setLeft(removePlayerHelper(node.getLeft(), player));
    } else if (player.compareTo(node.getData()) > 0) {
      node.setRight(removePlayerHelper(node.getRight(), player));
    } else {
      // Player found, handle the removal

      // Case 1: Node has no left child
      if (node.getLeft() == null) {
        return node.getRight(); // Replace with right child
      }

      // Case 2: Node has no right child
      if (node.getRight() == null) {
        return node.getLeft(); // Replace with left child
      }

      // Case 3: Node has two children, replace with the next larger element (in-order successor)
      // Find the next larger node (in-order successor)
      BSTNode<Player> successor = findMin(node.getRight());
      node.setLeft(removePlayerHelper(node.getRight(), successor.getData())); // Remove the
                                                                              // successor
      node.setRight(successor.getRight()); // Attach the right subtree of the successor

      node.setLeft(successor.getLeft()); // Attach the left subtree of the successor to the current
                                         // node
    }
    return node;
  }

  private BSTNode<Player> findMin(BSTNode<Player> node) {
    while (node.getLeft() != null) {
      node = node.getLeft();
    }
    return node;
  }

  
  //////////////////////////////////////////// GET MIN //////////////////////////////////////////// 
  
  /**
   * PROVIDED:
   * Finds the player with the lowest score in the leaderboard. If the tree is empty, returns null.
   * @return the Player with the lowest score, or null if the tree is empty.
   */
  public Player getMinScore() {
    if (root == null) return null;
    
    return getMinScoreHelper(root);
  }
  
  /**
   * THIS METHOD MUST BE IMPLEMENTED _RECURSIVELY_
   * 
   * Returns the Player with the smallest score in the current subtree.
   * @param node the root node of the tree to search; must not be null
   * @return the Player with the smallest score in the current subtree
   */
  protected Player getMinScoreHelper(BSTNode<Player> node) {
    if (node.getLeft() == null) {
      return node.getData(); // This node is the leftmost (min score)
    }
    return getMinScoreHelper(node.getLeft());
  }

  
  //////////////////////////////////////////// GET MAX //////////////////////////////////////////// 
  
  /**
   * PROVIDED:
   * Finds the player with the highest score in the leaderboard. If the tree is empty, returns null.
   * @return the Player with the highest score, or null if the tree is empty.
   */
  public Player getMaxScore() {
    if (root == null) return null;
    
    return getMaxScoreHelper(root);
  }
  
  /**
   * THIS METHOD MUST BE IMPLEMENTED _RECURSIVELY_
   * 
   * Returns the Player with the largest score in the current subtree.
   * @param node the root node of the tree to search; must not be null
   * @return the Player with the largest score in the current subtree
   */
  protected Player getMaxScoreHelper(BSTNode<Player> node) {
    if (node.getRight() == null) {
      return node.getData(); // This node is the rightmost (max score)
    }
    return getMaxScoreHelper(node.getRight());
  }

  
  //////////////////////////////////////////// NEXT ////////////////////////////////////////////
  
  /**
   * PROVIDED:
   * Finds the "smallest" Player in the leaderboard whose object is still "larger" than the provided
   * Player (see Player.compareTo()). If the tree is empty, returns null. 
   * @param player the player whose successor value we are searching for
   * @return the next largest player in the leaderboard, or null if no such player exists
   */
  public Player next(Player player) {
    if (root == null) return null;
    return nextHelper(root, player);
  }
  
  /**
   * THIS METHOD MUST BE IMPLEMENTED _RECURSIVELY_
   * 
   * Returns the Player with the next largest score (or equal score but next largest name) in the
   * subtree rooted at the current node.
   * 
   * Hint: consider the three places that this player might be:
   *   (1) the left subtree,
   *   (2) `node` itself, or
   *   (3) the right subtree
   * Find the best candidate Player in each of those three places and choose the best among them.
   * 
   * @param node the root of the relevant subtree
   * @param player the player whose successor value we are searching for
   * @return the next largest player in the leaderboard, or null if no player in this subtree is
   * larger than the given player
   */
  protected Player nextHelper(BSTNode<Player> node, Player player) {
    if (node == null)
      return null;

    // If player is larger, move to the right
    if (player.compareTo(node.getData()) < 0) {
      Player right = nextHelper(node.getRight(), player);
      return (right != null) ? right : node.getData();
    }

    // If player is smaller or equal, move to the left
    return nextHelper(node.getLeft(), player);
  }

  
  //////////////////////////////////////////// STRINGS ////////////////////////////////////////////
  
  /**
   * PROVIDED:
   * Creates a list of the players in this leaderboard from lowest to highest score.
   * 
   * TODO: modify the Leaderboard class so that this method does not cause a compiler error.
   * 
   * @return a list of all players in increasing order of score
   */
  @Override
  public String toString() {
    String leaders = "";
    for (Player p : this) { // TODO: modify the class so this does not cause a compiler error
      leaders += p.toString()+"\n";
    }
    return leaders.trim();
  }
  
  /**
   * PROVIDED:
   * Creates a tree-formatted version of the current state of the leaderboard
   * @return a tree-formatted string representation of this leaderboard
   */
  public String prettyPrint() { return prettyPrintHelper("", root, false); }

  /**
   * PROVIDED:
   * Recursive helper method for the prettyPrint() method
   */
  private String prettyPrintHelper(String prefix, BSTNode<Player> node,
                                  boolean isLeft) {
    if (node == null) {
      return "";
    }
    String str =
        prefix + (isLeft ? "├──" : "└──") + node.getData().toString() + "\n";
    str += prettyPrintHelper(prefix + (isLeft ? "│   " : "    "), node.getLeft(),
                          true);
    str += prettyPrintHelper(prefix + (isLeft ? "│   " : "    "), node.getRight(),
                          false);
    return str;
  }

  @Override
  public Iterator<Player> iterator() {
    return new LeaderboardIterator(this);
  }

}
