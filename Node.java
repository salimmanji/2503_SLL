/**
 * COMP 2503 Winter 2020 Assignment 2 February 28, 2020
 * 
 * Node Class to instantiate Node object. Extends the comparable interface.
 * 
 * @author Salim Manji
 *
 */
public class Node<T extends Comparable<T>> {

	private T data;
	private Node<T> next;

	/**
	 * Constructor for a new Node.
	 * 
	 * @param Token is the data the Node holds.
	 */
	public Node(T token) {
		data = token;
		next = null;
	}

	/**
	 * Gets the generic data object reference held by the current Node.
	 * @return Genric object (Token) held by the current Node.
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * Sets a reference to a generic data object held by the current Node.
	 * @param newToken is the new generic object to reference.
	 */
	public void setData(T newToken) {
		data = newToken;
	}
	
	/**
	 * Provides the next Node in the SLL.
	 * @return the reference of the next Node object in the SLL.
	 */
	public Node<T> getNext() {
		return next;
	}
	
	/**
	 * Sets the next node in the SLL.
	 * @param nextNode is the Node to point to.
	 */
	public void setNext(Node<T> nextNode) {
		next = nextNode;
	}

	public String toString() {
		return data.toString();
	}

}
