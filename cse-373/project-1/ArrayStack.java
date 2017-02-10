import java.util.EmptyStackException;

public class ArrayStack implements DStack {

	private double[] aStack;
	private int back;

	public ArrayStack() {
		this.aStack = new double[10];
		this.back = -1;
	}

	public boolean isEmpty() {
		return this.back == -1;
	}

	public void push(double d) {
		isFull();
		this.back++;
		this.aStack[this.back] = d;
	}
	
	private void isFull() {
		if(this.back == this.aStack.length - 1) {
			double[] newStack = new double[this.aStack.length * 2];
			for(int i = 0; i < this.aStack.length; i++) {
				newStack[i] = this.aStack[i];
			}
			this.aStack = newStack;
		}
	}

	public double pop() {
		double returnd = peek();
		this.back--;
		return returnd;
	}

	public double peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return this.aStack[this.back];
	}
}
