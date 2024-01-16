/**
 * DLPriorityQueue represents a collection of data items, each with a value and priority.
 * 
 * @author jocelynchang
 */
public class DLPriorityQueue<T> implements PriorityQueueADT<T> {
	/**
	 * Refers to the first node of the doubly linked list.
	 */
	private DLinkedNode<T> front;
	/**
	 * Refers to the last node of the doubly linked list.
	 */
	private DLinkedNode<T> rear;
	/**
	 * Refers to the number of data items in the priority queue.
	 */
	private int count;
	
	/**
	 * Creates an empty priority queue.
	 */
	public DLPriorityQueue() {
		front = null;
		rear = null;
	}
	
	/**
	 * Adds the given dataItem to the priority queue and stores it by its associated priority while keeping the queue in sorted by non-decreasing priority order.
	 * 
	 * @param dataItem  the specified data item to add to the queue
	 * @param piority  the specified priority of the data item being added
	 */
	public void add (T dataItem, double priority) {
		count ++;
		DLinkedNode<T> currNode = front;
		DLinkedNode<T> newNode = new DLinkedNode<>(dataItem, priority);
		//checks if the queue is empty
		if (front == null) {
			front = newNode;
			rear = newNode;
		//checks if the new node will need to be added as the new front
		} else if (front.getPriority() > priority) {
			newNode.setNext(front); 
			newNode.setPrev(null);
			front.setPrev(newNode);
			front = newNode;
		} else {
			//loops through the priority queue until the end or until a node with a bigger priority is found
			while (currNode.getNext() != rear.getNext() && (currNode.getNext()).getPriority() <= priority) {
				currNode = currNode.getNext();
			}
			//checks if if the new node is being added to the end of the queue or not
			if (currNode.getNext() != rear.getNext()) {
				(currNode.getNext()).setPrev(newNode);
			} else {
				rear = newNode;
			}
			newNode.setNext(currNode.getNext());
			newNode.setPrev(currNode);
			currNode.setNext(newNode);
		}
		
	}
	
	/**
	 * Updates the specified dataItem with a new specified priority and, if needed, fixes the order to keep the queue in non-decreasing order of priority.
	 * 
	 * @param dataItem  the specified data item to update the priority of
	 * @param newPriority  the specified priority to update the data item with
	 */
	public void updatePriority(T dataItem, double newPriority) throws InvalidElementException {
		DLinkedNode<T> currNode = front;
		//throws exception if queue is empty
		if (front == null) throw new InvalidElementException("Queue is empty");
		//finds data item in queue
		while (!currNode.getDataItem().equals(dataItem)) {
			//throws exception if there is no data item found and the end of queue has been reached
			if (currNode.getNext() == null) throw new InvalidElementException("Data item not found");
			currNode = currNode.getNext();
		}
		//sets data item with the new priority
		currNode.setPriority(newPriority);
		//checks if the node needs to move if the next one has a lower priority or the one before has a higher priority
		if (front != rear && ((currNode != rear && (currNode.getNext()).getPriority() < currNode.getPriority()) 
				|| (currNode != front && (currNode.getPrev()).getPriority() > currNode.getPriority()))){
			//checks if current node is the rear one so there can be a new rear labeled with null set next
			if (currNode == rear) {
				rear = currNode.getPrev();
				rear.setNext(null);
			//check if current node is the front one so there can be a new front labeled with null set before
			} else if (currNode == front) {
				front = currNode.getNext();
				front.setPrev(null);
			} else {
			//if the current node is in the middle remove it from queue
				(currNode.getPrev()).setNext(currNode.getNext());		
				(currNode.getNext()).setPrev(currNode.getPrev());
			}
			//decreases count of queue after removal and add it back after
			count --;
			this.add(dataItem, newPriority);
		}
	}
	
	/**
	 * Remove and return the data item with the smallest priority in the queue, the front node.
	 * 
	 * @return  the front node's data item
	 */
	public T removeMin() throws EmptyPriorityQueueException {
		//throws exception if the front of the queue is empty
		if (front == null) throw new EmptyPriorityQueueException("No path found");
		DLinkedNode<T> min = front;
		//checks if front and rear point to the same node
		if (front == rear) {
			front = null;
			rear = null;
		} else {
			front = min.getNext();
			front.setPrev(null);
		}
		count --;
		return min.getDataItem();
	}

	/**
	 * Checks if the queue is empty and if it is, it return true and returns false otherwise.
	 * 
	 * @return  the state of if the queue is empty or not
	 */
	public boolean isEmpty() {
		if (front == null && rear == null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns the number of data items in the queue.
	 * 
	 * @return  number of data items
	 */
	public int size() {
		return count;
	}
	
	/**
	 * Returns a String representation of the queue.
	 * 
	 * @return  string representation
	 */
	public String toString() {
		String statement = "";
		DLinkedNode<T> currNode = front;
		//loops through the queue and adds each node's data item to the returned String
		while (currNode != null) {
			statement += (currNode.getDataItem()).toString();
			currNode = currNode.getNext();
		}
		return statement;
	}
	
	/**
	 * Returns the rear node, the node with the highest priority in the queue.
	 * @return  the doubly linked node that is the rear
	 */
	public DLinkedNode<T> getRear() {
		return rear;
	}
}
