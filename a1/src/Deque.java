
//下面的程序为一个整数双向队列，double ended queue，支持先进先出
public class Deque {

	private SingleLinkedList list = new SingleLinkedList();

	public int size() {
		return list.getSize();
	}

	public void addFirst(int data) {
		list.add(0, data);
	}

	public void addLast(int data) {
		list.add(data);
	}

	//请实现removeLast函数
	public void removeLast() {
		int size=list.getSize()-1;
		list.remove(size);
	}

	public void removeFirst() {
		list.remove(0);
	}

	public int getFirst() {
		return list.get(0);
	}

	public int getLast() {
		return list.get(list.getSize()-1);
	}

	public int get(int index) {
		return list.get(index);
	}

	public Deque clone() {
		Deque d = new Deque();
		for(int i=0;i<list.getSize();i++) {
			d.list.add(list.get(i));
		}
		return d;
 	}

	public static void main(String[] args) {
		Deque deque = new Deque();
		deque.addFirst(5);
		deque.addFirst(6);
		deque.addLast(-5);
		deque.addLast(-4);

		System.out.println(deque.size());
		System.out.println(deque.getLast());

		deque.removeLast();
		System.out.println(deque.getLast());

	}

}
