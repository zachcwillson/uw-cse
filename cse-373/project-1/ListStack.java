import java.util.EmptyStackException;

public class ListStack implements DStack {

	private class ListStackNode {

		private double number;
		private ListStackNode next;

		public ListStackNode(double number, ListStackNode node) {
			this.number = number;
			this.next = node;
		}
	}

	private ListStackNode front;

	public ListStack() {
		this.front = null;
	}

	public boolean isEmpty() {
		return front == null;
	}

	public void push(double d) {
		ListStackNode curr = new ListStackNode(d, this.front);
		this.front = curr;
	}

	public double pop() {
		double returnd = peek();
		this.front = this.front.next;
		return returnd;
	}

	public double peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return this.front.number;
	}
}
