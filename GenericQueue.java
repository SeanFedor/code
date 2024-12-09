
class Node<X> {
	X data;
	Node<X> next;

	public Node(X data) {
		this.data = data;
		this.next = null;
	}
}

public class GenericQueue<X> {
	private Node<X> front;
	private Node<X> rear;
	private int size;
	
	public GenericQueue() {
		this.front = null;
		this.rear = null;
		this.size = 0;
	}
	public boolean empty() {
		return front == null;
	}
	
	//add element to end of queue
	public void enqueue(X data) {
		Node<X> newNode = new Node<>(data);
		
		if (empty()) {
			front = newNode;
		} else {
			rear.next = newNode;
		}
		
		rear = newNode;
		size++;
	}
	
	public X dequeue() {
		if (empty()) {
			throw new IllegalStateException("Empty");
		}
		
		X data = front.data;
		front = front.next;
		
		//set rear to null if empty
		if (front == null) {
			rear = null;
		}
		size--;
		return data;
	}
	public X poll() {
		if (empty()) {
			throw new IllegalStateException("Empty");
		}
		return front.data;
	}
	public int size() {
		return size;
	}
	public void clear() {
		front = null;
		rear = null;
		size = 0;
	}
}
