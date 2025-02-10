public class Candidate {
  private String name;
  private String party;
  private int numVotes;

  // Constructor
  public Candidate(String name, String party) {
    if (name == null || name.isBlank() || party == null || party.isBlank()) {
      throw new IllegalArgumentException("Name and party cannot be null or blank");
    }
    this.name = name;
    this.party = party;
    this.numVotes = 0;
  }

  // Method to add a vote
  public void addVote() {
    this.numVotes++;
  }

  // Accessor method for number of votes
  public int getNumVotes() {
    return numVotes;
  }

  // Overrides toString method
  @Override
  public String toString() {
    return name + " (" + party + "): " + numVotes;
  }

  // Overrides equals method to compare candidates
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    Candidate candidate = (Candidate) obj;
    return numVotes == candidate.numVotes && name.equals(candidate.name)
        && party.equals(candidate.party);
  }
}
