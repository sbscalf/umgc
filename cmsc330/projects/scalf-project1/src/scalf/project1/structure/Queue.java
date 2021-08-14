package scalf.project1.structure;

public class Queue<E> {
	private Node<E> front,end;
	private int size;
	
	public Queue() {
		front = end = null;
		size = 0;
	}
	
	public void add(E value) {
		Node<E> node = new Node<>(value);
		if (isEmpty()) {
			front = node;
		} else {
			end.setNext(node);
		}
		end = node;
		size++;
	}
	
	public E getNext() {
		if (isEmpty()) {
			return null;
		} else {
			E value = front.value();
			front = front.next();
			size--;
			if (isEmpty())
				end = null;
			return value;
		}
	}
	
	public E peek() {
		if (isEmpty()) {
			return null;
		} else {
			return front.value();
		}
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Node<E> node = front;
		do {
			sb.append(node.value() + "\n");
			node = node.next();
		} while (node != end);
		sb.append(node.value());
		return sb.toString();
	}
}
