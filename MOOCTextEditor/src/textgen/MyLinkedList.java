package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		head = null;
		tail = null;
		size=0;
	}

	private boolean isElementNull(LLNode<E> element) {
		return element == null;
	}
	
	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if(element == null) {
			throw new NullPointerException();
		}
		
		if(isElementNull(this.head)) {
			// For first element
			LLNode<E> singleElement = new LLNode<E>(element);
			head = singleElement;
			tail = singleElement;
			
			singleElement.prev = head;
			singleElement.next = null;
			size++;
		}
		else {
			// other elements
			LLNode<E> singleElement = new LLNode<E>(element);
			
			singleElement.prev = tail;
			singleElement.prev.next = singleElement;
			singleElement.next = null;
			tail = singleElement;
			size++;
		}
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{	
		if(index < 0 || index > size - 1 ) {
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> startReference = head;
		
		int indexTracker = 0;
		while(indexTracker < index) {
			startReference = startReference.next;
			indexTracker++;
		}
		return startReference.data;
		
	}
	
	public LLNode<E> getRefrence(int index) 
	{
		LLNode<E> startReference = head;
		
		int indexTracker = 0;
		while(indexTracker < index) {
			startReference = startReference.next;
			indexTracker++;
		}
		return startReference;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if(index < 0 ) {
			throw new IndexOutOfBoundsException();
		}
		
		if(element == null) {
			throw new NullPointerException();
		}
		
		if(isElementNull(head)) {
			//First Element
			LLNode<E> singleElement = new LLNode<E>(element);
			head = singleElement;
			tail = singleElement;
		}
		else {
			LLNode<E> refrenceNode = getRefrence(index);
			LLNode<E> singleElement = new LLNode<E>(element);
			
			singleElement.prev = refrenceNode.prev;
			singleElement.next = refrenceNode;
			
			refrenceNode.prev.next = singleElement;
			refrenceNode.prev = singleElement;
		}
		
		size += 1;
	}

	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if(index < 0 || index > size-1) {
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E>  currentNode = getRefrence(index);
		
		if(index == 0) {
			// Only one element
			if(currentNode.next == null) {
				head = tail = null;
			}
			else {
				head = currentNode.next;
				currentNode.prev = null;
			}
		}
		else if(index == size-1) {
			// Last element
			currentNode.prev.next = null;
			tail = currentNode.prev;
		}
		else {
			currentNode.prev.next = currentNode.next.prev;
			currentNode.next.prev = currentNode.prev;
		}
		
		size -= 1;
		return currentNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if(index < 0 || index > this.size - 1 ) {
			throw new IndexOutOfBoundsException();
		}
		
		if(element == null) {
			throw new NullPointerException();
		}
		
		LLNode<E> startReference = head;
		
		int indexTracker = 0;
		while(indexTracker < index) {
			startReference = startReference.next;
			indexTracker++;
		}
		startReference.data = element;
		return startReference.data;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	public LLNode(LLNode<E> startHead) 
	{
		this.next = startHead;
	}
	
	public LLNode(E e, MyLinkedList<E> previousNode) {
		this.data = e;
		this.prev = previousNode.tail;
		this.next = null;
		previousNode.tail = this;
	}

}
