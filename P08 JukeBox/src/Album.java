import java.util.NoSuchElementException;

public class Album {
  private String albumName; // Name of the album
  private int size; // Number of songs in the album
  private LinkedStack<Song> trackList; // Stack to store the songs

  // Constructor to initialize the Album
  public Album(String albumName) {
    if (albumName == null || albumName.isEmpty()) {
      throw new IllegalArgumentException("Album name cannot be null or empty");
    }

    this.albumName = albumName;
    this.size = 0;
    this.trackList = new LinkedStack<>();
  }

  // Adds a song to the top of the album's track list
  public void addSong(Song s) {
    if (s == null) {
      throw new IllegalArgumentException("Song cannot be null");
    }

    if (trackList.contains(s)) {
      throw new IllegalArgumentException("Song already exists in the album");
    }

    trackList.push(s); // Add the song to the stack
    size++; // Increment the size
    s.setAlbum(this); // Set the reference to this album in the Song object
  }

  // Removes the most recently added song from the album
  public Song removeSong() {
    if (size == 0) {
      throw new NoSuchElementException("Album is empty");
    }

    Song removedSong = trackList.pop(); // Remove the song from the stack
    size--; // Decrement the size
    return removedSong; // Return the removed song
  }

  // Retrieves the song currently at the top of the album's track list
  public Song firstSong() {
    return trackList.peek(); // Returns the top song without removing it
  }

  // Retrieves the name of the album
  public String getAlbumName() {
    return albumName;
  }

  // Returns the number of songs in the album
  public int size() {
    return size;
  }

  // Returns a string representation of the album
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(albumName).append("\n");

    for (Song song : trackList.getList()) {
      sb.append(song.toString()).append("\n");
    }

    return sb.toString().trim(); // Trim to remove the trailing newline
  }
}
