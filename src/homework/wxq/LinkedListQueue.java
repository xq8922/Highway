package homework.wxq;

import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListQueue {
	private LinkedList list;
//	public ListIterator lit = list.listIterator();

	public LinkedListQueue() {
		list = new LinkedList();
	}

	public int size() {
		return list.size();
	}

	public void enQueue(Object o) {
		list.addLast(o);
	}

	public Object deQueue() {
		return list.removeFirst();
	}

	public Object front() {
		return list.peekFirst();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}
}
