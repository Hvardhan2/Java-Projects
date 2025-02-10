import java.util.NoSuchElementException;

public class Election {
  private Candidate[] candidates;
  private int numCandidates;
  public final String SEAT_NAME;

  // Constructor
  public Election(String seatName, int maxCandidates) {
    if (maxCandidates <= 0) {
      throw new IllegalArgumentException("Max candidates must be greater than 0");
    }
    this.SEAT_NAME = seatName;
    this.candidates = new Candidate[maxCandidates];
    this.numCandidates = 0;
  }

  // Add a candidate
  public void addCandidate(Candidate candidate) {
    if (numCandidates == candidates.length) {
      throw new IllegalArgumentException("Election is full");
    }
    for (int i = 0; i < numCandidates; i++) {
      if (candidates[i].equals(candidate)) {
        throw new IllegalArgumentException("Candidate already exists");
      }
    }
    candidates[numCandidates++] = candidate;
  }

  // Remove a candidate
  public void removeCandidate(Candidate candidate) {
    if (numCandidates == 0) {
      throw new IllegalStateException("No candidates to remove");
    }
    for (int i = 0; i < numCandidates; i++) {
      if (candidates[i].equals(candidate)) {
        candidates[i] = candidates[--numCandidates];
        candidates[numCandidates] = null;
        return;
      }
    }
    throw new NoSuchElementException("Candidate not found");
  }

  // Find the winner
  public Candidate findWinner() {
    if (numCandidates == 0) {
      throw new IllegalStateException("No candidates in the election");
    }
    for (Candidate candidate : candidates) {
      if (candidate != null && candidate.getNumVotes() > 50) {
        return candidate;
      }
    }
    throw new NoSuchElementException("No candidate with >50% votes");
  }

  // Cast a vote for a candidate
  public void vote(Candidate candidate) {
    for (int i = 0; i < numCandidates; i++) {
      if (candidates[i].equals(candidate)) {
        candidates[i].addVote();
        return;
      }
    }
    throw new NoSuchElementException("Candidate not found");
  }

  // Get current number of candidates
  public int getNumCandidates() {
    return numCandidates;
  }

  // Get maximum capacity of candidates
  public int capacity() {
    return candidates.length;
  }

  // Overrides toString method
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder(SEAT_NAME + "\n");
    for (int i = 0; i < numCandidates; i++) {
      result.append(candidates[i].toString()).append("\n");
    }
    return result.toString().trim();
  }

  // Overrides equals method
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    Election election = (Election) obj;
    return SEAT_NAME.equalsIgnoreCase(election.SEAT_NAME);
  }
}
