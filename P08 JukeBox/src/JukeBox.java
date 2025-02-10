import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class JukeBox {
  private int capacity; // Maximum capacity of the jukebox queue
  private LinkedQueue<Song> songQueue; // Queue to store songs in FIFO order

  // Constructor to initialize the jukebox with a specified capacity
  public JukeBox(int capacity) {
    if (capacity < 0) {
      throw new IllegalArgumentException("Capacity cannot be negative");
    }

    this.capacity = capacity;
    this.songQueue = new LinkedQueue<>();
  }

  // Adds a single song to the end of the queue if space allows
  public void addSongToQueue(Song song) {
    if (song == null) {
      throw new IllegalArgumentException("Song cannot be null");
    }

    if (isFull()) {
      throw new IllegalStateException("JukeBox queue is full");
    }

    if (songQueue.contains(song)) {
      throw new IllegalArgumentException("Song already exists in the queue");
    }

    songQueue.enqueue(song); // Add the song to the queue
  }

  // Adds an entire album to the queue if space allows
  public void addAlbumToQueue(Album album) {
    if (album == null) {
      throw new IllegalArgumentException("Album cannot be null");
    }

    LinkedStack<Song> tempStack = new LinkedStack<>(); // Temporary stack to hold songs
    int songsAdded = 0;

    // Remove songs from the album and add to the queue
    while (album.size() > 0 && !isFull()) {
      Song song = album.removeSong(); // Remove song from the album
      if (!songQueue.contains(song)) { // Add song to queue if not already present
        songQueue.enqueue(song);
        songsAdded++;
      }
      tempStack.push(song); // Store the song in the temporary stack
    }

    // Restore the album to its original state
    while (!tempStack.isEmpty()) {
      album.addSong(tempStack.pop());
    }

    // If no songs were added, throw an exception
    if (songsAdded == 0) {
      throw new IllegalStateException("No songs were added to the queue");
    }
  }


  // Plays and removes the song at the front of the queue
  public Song playSong() {
    if (isEmpty()) {
      throw new NoSuchElementException("No songs in the queue");
    }

    return songQueue.dequeue(); // Remove and return the front song
  }

  // Shuffles the songs present in the queue
  public void shuffleSongQueue() {
    ArrayList<Song> songs = songQueue.getList(); // Get the queue as a list
    Collections.shuffle(songs); // Shuffle the list
    songQueue.clear(); // Clear the current queue

    for (Song song : songs) {
      songQueue.enqueue(song); // Enqueue songs in shuffled order
    }
  }

  // Returns the current number of songs in the queue
  public int size() {
    return songQueue.size();
  }

  // Returns the maximum number of songs that can be in the queue
  public int capacity() {
    return capacity;
  }

  // Checks if the queue is full
  public boolean isFull() {
    return size() >= capacity;
  }

  // Checks if the queue is empty
  public boolean isEmpty() {
    return songQueue.isEmpty();
  }

  // Provides a string representation of the jukebox queue
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (Song song : songQueue.getList()) {
      sb.append(song.toString()).append(" -> ");
    }

    sb.append("END");
    return sb.toString();
  }
}
