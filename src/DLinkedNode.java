/**
 * DLinkedNode represents a doubly linked node with a generic type.
 * 
 * @author jocelynchang
 */
public class DLinkedNode<T> {
	/**
	 * Refers to the data item stored in the node.
	 */
	private T dataItem; 
	/**
	 * Priority of data item stored in the node.
	 */
	private double priority;
	/**
	 * Refers to the next node in the linked list.
	 */
	private DLinkedNode<T> next;
	/**
	 * Refers to the previous node in the linked list.
	 */
	private DLinkedNode<T> prev;
	
	/**
	 * Creates a doubly linked node assigned with a specified data item and priority.
	 * 
	 * @param data  the data item to be stored in the node
	 * @param prio  the priority of the data item
	 */
	public DLinkedNode (T data, double prio) {
		dataItem = data;
		priority = prio;
	}
	
	/**
	 * Creates an empty doubly linked node with null dataItem and zero priority.
	 */
	public DLinkedNode() {
		dataItem = null;
		priority = 0.0;
	}
	
	/**
	 * Gets the priority of this doubly linked node's data item.
	 * @return  the priority of this node's data item
	 */
	public double getPriority() {
		return priority;
	}
	
	/**
	 * Gets the data item of this doubly linked node.
	 * @return  this node's data item
	 */
	public T getDataItem() {
		return dataItem;
	}
	
	/**
	 * Gets the doubly linked node that comes after this one.
	 * @return  the next following node
	 */
	public DLinkedNode<T> getNext() {
		return next;
	}
	
	/**
	 * Gets the doubly linked node that comes before this one.
	 *
	 * @return  the previous node
	 */
	public DLinkedNode<T> getPrev() {
		return prev;
	}
	
	/**
	 * Sets this doubly linked node's data item with a new specified data item.
	 * 
	 * @param data  data item to replace the dataItem of this node
	 */
	public void setDataItem(T data) {
		dataItem = data;
	}
	
	/**
	 * Sets the specified doubly linked node to be the node after this node.
	 * 
	 * @param nextNode  node to be the next node
	 */
	public void setNext(DLinkedNode<T> nextNode) {
		next = nextNode;
	}
	
	/**
	 * Sets the specified doubly linked node to be the node before this node.
	 * 
	 * @param prevNode  node to be the previous node
	 */
	public void setPrev(DLinkedNode<T> prevNode) {
		prev = prevNode;
	}
	
	/**
	 * Sets the priority of this node's data item.
	 * 
	 * @param prio  priority to be assigned to this node's data item
	 */
	public void setPriority(double prio) {
		priority = prio;
	}
}
