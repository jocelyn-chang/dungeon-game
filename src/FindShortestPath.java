/**
 * FindShortestPath represents a class that uses the main method to find the shortest path you can take to get from start to exit in the dungeon.
 * 
 * @author jocelynchang
 *
 */
public class FindShortestPath {
	public static void main(String[] args) {
		String dungeonFileName;
		Hexagon startingChamber;
		Dungeon chambers;
		Hexagon smallest;
		Hexagon neighbour;
		int D = 0;
		boolean foundExit = false;
		
		try{
			//throws exception if there is no specified input file
			if (args.length < 1) throw new Exception("No input file specified"); 
			dungeonFileName = args[0];
			chambers = new Dungeon(dungeonFileName);
			
			//create empty priority queue
			DLPriorityQueue<Hexagon> prioQueue = new DLPriorityQueue<Hexagon>();
			//gets starting chamber from the dungeon
			startingChamber = chambers.getStart();
			//throws exception if there is no starting chamber
			if (startingChamber == null) throw new Exception("No starting chamber");
			//adds starting chamber to priority queue with priority of zero and marks it as enqueued
			prioQueue.add(startingChamber, 0.0);
			startingChamber.markEnqueued();
			
			//loops through the priority queue while it is not empty and until the exit has been found 
			while (prioQueue.isEmpty() == false && foundExit == false) {
				smallest = prioQueue.removeMin();
				smallest.markDequeued();
				foundExit = smallest.isExit();
				//if the current chamber is the exit then the loop breaks
				if (foundExit == true) {
					System.out.println("Path of length " + (smallest.getDistanceToStart() + 1) + " found");
					break;
				}
				
				//loops through the current chamber's neighbouring chambers to check if they are dragon chambers
				boolean loopStatus = false;
				for (int i = 0; i < 6; i++) {
					if (smallest.getNeighbour(i) != null) {
						if (smallest.getNeighbour(i).isDragon() == true) {
							loopStatus = true;
						}
					}
				}
				
				//loops again to next chamber if the current chamber has a dragon or the neighbouring chambers hold a dragon
				if (smallest.isDragon() == true || loopStatus == true || smallest.isCacti() || smallest.isLava()) {
					continue;
				} else {
				//loops through each of the neighbouring chambers that aren't null, a wall, or marked as dequeued
					for (int i = 0; i < 6; i++) {
						if ((smallest.getNeighbour(i) != null) && (smallest.getNeighbour(i).isWall() == false) && (smallest.getNeighbour(i).isMarkedDequeued() == false)) {
							boolean change = false;
							neighbour = smallest.getNeighbour(i);
							
							//set D as the distance from the current chamber to the initial chamber plus one
							D = 1 + smallest.getDistanceToStart();
							//distance of the neighbouring chamber is changed to D if it is bigger than D and then current chamber is set as its predecessor
							if (neighbour.getDistanceToStart() > D) {
								neighbour.setDistanceToStart(D);
								change = true;
								neighbour.setPredecessor(smallest);
							}
							
							//updates priority with new priority if the neighbouring chamber is marked as enqueued and the distance was changed in this iteration
							if ((neighbour.isMarkedEnqueued() == true) && (change == true)) {
								prioQueue.updatePriority(neighbour, (neighbour.getDistanceToExit(chambers) + neighbour.getDistanceToStart()));
							//adds the neighbouring chamber if it was not marked as enqueued and marks it as enqueued
							} else if (neighbour.isMarkedEnqueued() == false) {
								prioQueue.add(neighbour, (neighbour.getDistanceToExit(chambers) + neighbour.getDistanceToStart()));
								neighbour.markEnqueued();
							}
						}
					}
					
				}
			}
			//throws exception if no exit is found and the queue is empty
			if (foundExit == false && prioQueue.isEmpty() == true) throw new Exception ("Path not found");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
