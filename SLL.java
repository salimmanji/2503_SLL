import java.util.Comparator;

/**
 * COMP 2503 Winter 2020 Assignment 2 February 28, 2020
 * 
 * SLL Class to instantiate singly linked list (SLL). Provides logic for how to add a 
 * Node into the SLL. This class extends the comparable interface.
 * 
 * @author Salim Manji
 *
 */

public class SLL<T extends Comparable<T>> {

	private Node<T> head;
	private int size;
	private Comparator<T> comparator = null;

	/**
	 * Generic constructor of a singly linked list - no parameters.
	 */
	public SLL() {
		head = null;
		size = 0;
	}

	/**
	 * Constructor for a singly linked list that can be sorted in a specified
	 * manner.
	 * 
	 * @param c Comparator to use for comparing.
	 */
	public SLL(Comparator<T> c) {
		head = null;
		size = 0;
		comparator = c;
	}

	/**
	 * Return the number of elements in the list.
	 * 
	 * @return int number of elements in the list.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Adds a Node to the SLL.
	 * 
	 * @param toAdd The token to include within the new Node.
	 */
	public void add(T toAdd) {
		Node<T> nodeToAdd = new Node<T>(toAdd);
		addInOrder(nodeToAdd);
	}

	/**
	 * Determines where to add the new Node in the SLL, then adds the Node at the
	 * correct position.
	 * 
	 * @param toAdd The token to add to the SLL.
	 */
	public void addInOrder(Node<T> toAdd) {
		int position = 0;
		Node<T> mover = null;
		Node<T> previous = null;

		if (isEmpty()) {
			addHead(toAdd);
		} else {
			mover = head;
			if (comparator == null) {
				position = getPositionNoComp(toAdd, mover); 
			} else {
				position = getPosition(toAdd, mover);
			}
			if (position < 0) {
				addHead(toAdd);
			} else {
				while (mover.getNext() != null && position > 0) {
					previous = mover;
					mover = mover.getNext();
					position = getPosition(toAdd, mover);
				}
				if (mover.getNext() == null) {
					addTail(mover, toAdd);
				} else {
					addBetween(previous, toAdd);
				}
			}
		}
		size++;
	}

	/**
	 * Return a specific element contained within the list.
	 * 
	 * @param searched The word desired (in Token form).
	 * 
	 * @return The token with the correct frequency, or null if is not in the SLL.
	 */
	public T find(T searched) {
		T moverToken = null;

		if (isEmpty()) {
			return null;
		} else {
			Node<T> mover = head;
			for (int index = 0; index < size; index++) {
				moverToken = mover.getData();
				if (moverToken.compareTo(searched) == 0) {
					return mover.getData();
				}
				mover = mover.getNext();
			}
		}
		return null;
	}

	/**
	 * Iterates through the list and outputs the desired toString to the .txt file.
	 * 
	 * @param endIndex Signals when to stop iterating through the SLL.
	 */
	public void printList(int endIndex) {
		Node<T> mover = head;

		for (int counter = 0; counter < endIndex; counter++) {
			System.out.println(mover.toString());
			mover = mover.getNext();
		}
	}

	/**
	 * Add a new object t to the head of the list.
	 * 
	 * @param t the object to add.
	 */
	public void addHead(T t) {
		addHead(new Node<T>(t));
	}

	/**
	 * Return the ith element of the list.
	 * 
	 * @param toGet The ith element to return
	 * @return the ith element, null if there isnt one.
	 */
	public T get(int toGet) {
		Node<T> curr = head;
		int counter = 0;

		while (curr != null && counter < toGet) {
			curr = curr.getNext();
			counter++;
		}
		if (curr != null)
			return curr.getData();
		else
			return null;
	}

	/**
	 * Determines if the SLL is empty.
	 * 
	 * @return True if 0 elements in the list, else false.
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Assists in adding a new Node to the head of the SLL.
	 * 
	 * @param toAdd The new Node to add.
	 */
	private void addHead(Node<T> toAdd) {
		toAdd.setNext(head);
		head = toAdd;
	}

	/**
	 * Support method that adds the new Node between two elements, the mover and the
	 * next Node in the SLL
	 * 
	 * @param mover Determines the current position in the SLL.
	 * @param toAdd New Node to add into the SLL.
	 */
	private void addBetween(Node<T> mover, Node<T> toAdd) {
		toAdd.setNext(mover.getNext());
		mover.setNext(toAdd);
	}

	/**
	 * Adds the new Node at the tail of the SLL.
	 * 
	 * @param mover Determines the current position in the SLL.
	 * @param toAdd New Node to add into the SLL.
	 */
	private void addTail(Node<T> mover, Node<T> toAdd) {
		mover.setNext(toAdd);
	}

	/**
	 * Assists in determining where to add the new Node in the SLL.
	 * 
	 * @param toAdd The new Node to add when an SLL is instantiated using the overloaded
	 * constructor.
	 * @param mover Mover to iterate through the SLL.
	 * @return An int that alerts the addInOrder method if the mover needs to
	 *         iterate further through the list.
	 */
	private int getPosition(Node<T> toAdd, Node<T> mover) {
		return comparator.compare(toAdd.getData(), mover.getData());
	}
	
	/**
	 * Assists in determining where to add the new Node in the SLL.
	 * 
	 * @param toAdd The new Node to add when an SLL is instantiated using the default 
	 * constructor.
	 * @param mover Mover to iterate through the SLL.
	 * @return An int that alerts the addInOrder method if the mover needs to
	 *         iterate further through the list.
	 */
	private int getPositionNoComp(Node<T> toAdd, Node<T> mover) {
		return toAdd.getData().compareTo(mover.getData());
	}

}
