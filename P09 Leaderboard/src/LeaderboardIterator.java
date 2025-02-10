import java.util.Iterator;
import java.util.NoSuchElementException;

public class LeaderboardIterator implements Iterator<Player> {
  private Player nextPlayer;
  private Leaderboard leaderboard;
  private Player currentPlayer;

  // Constructor that initializes the iterator with the leaderboard and the first player
  public LeaderboardIterator(Leaderboard leaderboard) {
    this.leaderboard = leaderboard;
    // Initialize nextPlayer to the player with the smallest score
    this.nextPlayer = leaderboard.getMinScore(); // Using the existing method in Leaderboard
    this.currentPlayer = null;
    }

    @Override
    public boolean hasNext() {
      return nextPlayer != null;
    }

    @Override
    public Player next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      // Save the current player and move to the next player in the leaderboard
      Player current = nextPlayer;
      nextPlayer = leaderboard.next(current); // Using the existing next() method in Leaderboard
      return current;
    }
}
