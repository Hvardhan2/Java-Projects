import java.util.NoSuchElementException;

public class LoopStation {
  protected Track waitingFirst; // track for waiting first-class Pods
  protected Track waitingEconomy; // track for waiting economy-class Pods
  protected Track launched; // track for launched Pods

  // Constructor
  public LoopStation() {
    waitingFirst = new Track();
    waitingEconomy = new Track();
    launched = new Track();
  }

  // Creates a new Pod and adds it to the appropriate waiting track
  public Pod createPod(int capacity, boolean isFirstClass) {
    Pod newPod = new Pod(capacity, isFirstClass ? Pod.FIRST : Pod.ECONOMY); // Use boolean to
                                                                            // determine class
    if (isFirstClass) {
      waitingFirst.add(newPod); // Add to waitingFirst if it's first-class
    } else {
      waitingEconomy.add(newPod); // Add to waitingEconomy if it's economy-class
    }
    return newPod;
  }

  // Finds and removes any malfunctioning Pods from the launched track
  public int clearMalfunctioning() {
    int removedCount = 0;

    // Check for malfunctioning pods in the launched track
    int index = 0;
    while (index < launched.size()) {
      Pod currentPod = launched.get(index);
      if (!currentPod.isFunctional()) {
        launched.remove(index); // Remove malfunctioning pod
        removedCount++;
      } else {
        index++;
      }
    }
    return removedCount;
  }

  // Launches the highest-priority, least-recently-created Pod from the waiting tracks
  // Launches the highest-priority, least-recently-created Pod from the waiting tracks
  public void launchPod() {
    // Check if there are any pods to launch
    if (waitingFirst.isEmpty() && waitingEconomy.isEmpty()) {
      throw new NoSuchElementException("No pod is available to launch");
    }

    Pod podToLaunch = null;

    // Try to find the smallest first-class pod (smallest capacity and functional)
    if (!waitingFirst.isEmpty()) {
      LinkedNode current = waitingFirst.head;
      while (current != null) {
        Pod currentPod = current.getPod();
        try {
          // Only consider functional pods
          if (currentPod.isFunctional()) {
            // If no pod has been selected or current pod is smaller, choose it
            if (podToLaunch == null || currentPod.getCapacity() < podToLaunch.getCapacity()) {
              podToLaunch = currentPod;
            }
          }
        } catch (MalfunctioningPodException e) {
          // Skip malfunctioning pods
        }
        current = current.getNext();
      }
    }

    // If no first-class pod is found, look for a functional economy-class pod
    if (podToLaunch == null && !waitingEconomy.isEmpty()) {
      LinkedNode current = waitingEconomy.head;
      while (current != null) {
        Pod currentPod = current.getPod();
        try {
          // Only consider functional pods
          if (currentPod.isFunctional()) {
            // If no pod has been selected or current pod is smaller, choose it
            if (podToLaunch == null || currentPod.getCapacity() < podToLaunch.getCapacity()) {
              podToLaunch = currentPod;
            }
          }
        } catch (MalfunctioningPodException e) {
          // Skip malfunctioning pods
        }
        current = current.getNext();
      }
    }

    // Remove the selected pod from the appropriate waiting list
    try {
      if (podToLaunch != null) {
        if (podToLaunch.getPodClass() == Pod.FIRST) {
          removePodFromList(waitingFirst, podToLaunch);
        } else if (podToLaunch.getPodClass() == Pod.ECONOMY) {
          removePodFromList(waitingEconomy, podToLaunch);
        }

        // Add the selected pod to the launched track
        launched.add(podToLaunch);
      }
    } catch (MalfunctioningPodException e) {
      e.printStackTrace();
    }
  }


  private void removePodFromList(Track track, Pod pod) {
    LinkedNode current = track.head;
    int index = 0;
    while (current != null) {
      if (current.getPod().equals(pod)) {
        track.remove(index); // Remove pod from the track
        break;
      }
      current = current.getNext();
      index++;
    }
  }

  // Gets the total number of unlaunched Pods at this LoopStation
  public int getNumWaiting() {
    return waitingFirst.size() + waitingEconomy.size();
    }

  // Reports the total number of launched Pods
  public int getNumLaunched() {
    return launched.size();
  }

  // Reports the total number of passengers in functional Pods across all tracks
  public int getNumPassengers() {
    int totalPassengers = 0;

    // Count passengers in functional Pods in waitingFirst
    for (int i = 0; i < waitingFirst.size(); i++) {
      Pod pod = waitingFirst.get(i);
      if (pod.isFunctional()) {
            try {
          totalPassengers += pod.getNumPassengers();
            } catch (MalfunctioningPodException e) {
          // Handle exception if necessary
          e.printStackTrace();
            }
      }
        }

    // Count passengers in functional Pods in waitingEconomy
    for (int i = 0; i < waitingEconomy.size(); i++) {
      Pod pod = waitingEconomy.get(i);
      if (pod.isFunctional()) {
            try {
          totalPassengers += pod.getNumPassengers();
            } catch (MalfunctioningPodException e) {
          // Handle exception if necessary
          e.printStackTrace();
            }
      }
        }

    // Count passengers in functional Pods in launched
    for (int i = 0; i < launched.size(); i++) {
      Pod pod = launched.get(i);
      if (pod.isFunctional()) {
        try {
          totalPassengers += pod.getNumPassengers();
        } catch (MalfunctioningPodException e) {
          // Handle exception if necessary
          e.printStackTrace();
            }
      }
        }

    return totalPassengers;
    }
}
