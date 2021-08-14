package scalf.project1.structure;

class Node<T> {
	private final T value;
	private Node<T> next;
	
	Node(T value) {
		this.value = value;
	}
	
	T value() {
		return value;
	}
	
	Node<T> next() {
		return next;
	}
	
	void setNext(Node<T> next) {
		this.next = next;
	}
}
