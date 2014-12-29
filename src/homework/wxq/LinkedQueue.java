package homework.wxq;

import java.util.*;

public class LinkedQueue {

	// 字段
	private LinkedList list;
	
	// 无参数构造
	public LinkedQueue() {
		list = new LinkedList();
	}

	// 队列元素的个数
	public int size() {
		return list.size();
	}

	// 进入队列
	public void enQueue(Object obj) {
		list.addLast(obj);

	}

	// 对头出来
	public Object deQueue() {
		return list.removeFirst();
	}

	// 浏览对头元素
	public Object front() {
		// return list.getFirst();
		return list.peekFirst();
	}

	// 判断队列是否为空
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//		LinkedListQueue llq = new LinkedListQueue();
//		System.out.println(llq.isEmpty());
//		llq.enqueue("147");
//		llq.enqueue("258");
//		llq.enqueue("369");
//		System.out.println(llq.size());
//		System.out.println("移除队列头元素：" + llq.dequeue());
//		System.out.println(llq.size());
//		llq.enqueue("abc");
//		llq.enqueue("def");
//		System.out.println(llq.size());
//		System.out.println("查看队列的头元素：" + llq.front());
//		System.out.println(llq.size());
//		System.out.println(llq.isEmpty());
//
//	}

}