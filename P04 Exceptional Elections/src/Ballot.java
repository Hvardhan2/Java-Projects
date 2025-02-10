import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Ballot {
  private static ArrayList<Election> elections = new ArrayList<>();
  private static boolean ballotsCreated = false;
  private boolean[] hasVoted;

  // Constructor
  public Ballot() {
    if (elections.isEmpty()) {
      throw new IllegalStateException("No elections available");
    }
    this.hasVoted = new boolean[elections.size()];
    ballotsCreated = true;
  }

  // Add a new election
  public static void addElection(Election election) {
    if (ballotsCreated) {
      throw new IllegalStateException("Cannot add election after ballots are created");
    }
    for (Election e : elections) {
      if (e.equals(election)) {
        throw new IllegalArgumentException("Election already exists");
      }
    }
    elections.add(election);
  }

  // Check if the ballot has voted in an election
  public boolean hasVoted(String seatName) {
    for (int i = 0; i < elections.size(); i++) {
      if (elections.get(i).SEAT_NAME.equalsIgnoreCase(seatName)) {
        return hasVoted[i];
      }
    }
    throw new NoSuchElementException("Election not found");
  }

  // Cast a vote
  public void vote(String seatName, Candidate candidate) {
    for (int i = 0; i < elections.size(); i++) {
      if (elections.get(i).SEAT_NAME.equalsIgnoreCase(seatName)) {
        if (hasVoted[i]) {
          throw new IllegalStateException("Already voted in this election");
        }
        elections.get(i).vote(candidate);
        hasVoted[i] = true;
        return;
      }
    }
    throw new NoSuchElementException("Election not found");
  }

  // Overrides toString method
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < elections.size(); i++) {
      result.append(elections.get(i).SEAT_NAME).append(": ").append(hasVoted[i]).append("\n");
    }
    return result.toString().trim();
  }
}
