public class SingleLinkedList {
	public Node head, tail; // 都声明为公开成员
	public int size = 0; // 这是一个派生数据，冗余数据，

	// 可以遍历链表获得链表元素个数，但是效率低
	public int getSize() { // 判断元素的数量，如果没有size数据的话，
		return size; // 需要遍历这个链表，然后计数
	}

	public void clear() { // 清空单链表，回到单链表的初始状态
		head = tail = null;
		size = 0;
	}

	public void add(int data) { // 向链表末尾追加元素，append操作
		Node n = new Node();
		n.data = data;
		if (size == 0) { // 如果链表为空，没有一个元素
			head = n; // head，tail都指向这个元素就可以了
			tail = n;
		} else { // 如果链表不为空
			/*
			 * n.next = head; head = n; //头插法，在最前面插入一个元素
			 */
			tail.next = n; // 尾插法，在链表末尾添加新节点
			tail = n;
		}
		size++; // 链表元素数量+1
	}

	public void add(int index, int data) { // 在指定位置插入元素，index从0开始
		/*
		 * 如果位置index非法，应该抛出IndexOutOfBoundsException异常， 异常处理将在后续章节介绍
		 */
		if (index > size || index < 0) {
			return;
		}
		Node n1 = new Node();
		n1.data = data;
		if (size == 0) { // 链表为空的条件下，
			head = n1;
			tail = n1;
		} else { // 如果链表不为空
			Node n = head; // 当前节点
			Node p = head; // 当前节点的前一个节点
			for (int i = 0; i < index; i++) { // 开始遍历，直到到达插入位置
				p = n;
				n = n.next;
			}
			if (n == p) { // 插入位置就是0，头插法。
				n1.next = head;
				head = n1;
			} else if (n == null) { // 插入位置是末尾，尾插法。
				tail.next = n1;
				tail = n1;
			} else { // 中间位置插入一个元素
				p.next = n1;
				n1.next = n;
			}
		}
		size++; // 将元素个数+1
	}

	public void remove(int index) { // 删除指定位置的元素
		// 注意，不是删除某个数
		if (index >= size || index < 0) {// 非法的位置参数，应该抛出异常，
			return; // 在此处简单的返回处理，什么也不做
		}
		if (size == 1) { // 单链表中只有一个元素，删除
			head = tail = null;
		} else if (index == 0) { // 不止有一个元素，如果删除第一个元素
			head = head.next; // 进行第一个元素的删除操作
		} else {
			Node n = head; // 如果不是删除第一个元素，则遍历
			Node p = head; // 到该位置
			for (int i = 0; i < index; i++) {
				p = n;
				n = n.next;
			}
			p.next = n.next; // 进行该指定位置的删除操作
			if (p.next == null) { // 如果删除的是末尾节点，那么p.next为空
				tail = p; // 还需要设置tail引用
			}
		}
		size--; // 元素的个数-1
	}

	//返回链表指定下标位置的数据，不考虑index的值为非法数据的情况
	//暂时不用考虑链表中数据为空的情况
	public int get(int index) {
		Node n = head;
		for(int i=0;i<index;i++) {
			n = n.next;
		}
		return n.data;
	}

	public void printElements() { // 输出链表中所有数据，遍历单链表
		if (size == 0) {
			System.out.println("empty single linkedlist!");
		} else {
			Node n = head;
			System.out.print("elements: ");
			while (n != null) { // 循环遍历程序
				if (n == tail) { // println函数输出后自动添加回车换行
					System.out.println(n.data); // 最后的元素输出后，回车换行
				} else { // print函数输出后不自动添加回车换行
					System.out.print(n.data + ", ");
				}
				n = n.next; // 单链表的遍历，后续章节的异常链也是单链表
			}
		}
	}

	public void printFirst() { // 输出头节点元素信息
		if (head != null) {
			System.out.println("first = " + head.data);
		} else {
			System.out.println("no first element.");
		}
	}

	public void printLast() { // 输出末尾节点元素信息
		if (tail != null) {
			System.out.println("last = " + tail.data);
		} else {
			System.out.println("no last element.");
		}
	}

	public void printSize() { // 打印输出单链表的大小
		System.out.println("size = " + size);
	}

	public void printAll() { // 输出全部信息
		System.out.println("-----------------------------------------------------------");
		printFirst();
		printLast();
		printSize();
		printElements();
	}

	public void bubbleSort() { // 冒泡排序，算法简单
		if (size < 2) {
			return;
		}
		Node tail2 = tail; // 第一遍，从头比较到最后一个节点，比较出最大
		while (tail2 != head) { // 的元素放到最后一个节点，第二编，从头比较到
			Node t1 = head; // 倒第二个元素，tail2是每次比较的最后一个元素
			Node t2 = t1.next; // tail2的值不断前移，直至head
			Node p1 = null;
			while (t1 != tail2) { // 内层循环，每次都是从头比较到tail2
				if (t1.data > t2.data) { // 使用t1,t2来指向相邻的比较数据
					swapData(t1, t2); // 交换数据，见后面的程序
				}
				p1 = t1; // 一趟比较完毕，p1指向下一趟的最后一个元素
				t1 = t2; // t1, t2向后移
				t2 = t2.next;
			}
			tail2 = p1; // 一趟结束，开始下一趟比较，设定新的末尾位置
		}
	}

	public void swapData(Node t1, Node t2) { // 交换两个节点的数据
		if (t1 == null || t2 == null) { // 并非交换链中的两个节点
			return;
		}
		int temp = t1.data;
		t1.data = t2.data;
		t2.data = temp;
	}

	public void insertionSort() {
		// 直接插入排序，这个例子给出的实现不好，三重循环
		if (size < 2) {
			return;
		}
		Node p1 = head.next; // p1始终指向未排序数据的第一个元素位置
		while (p1 != null) { // 默认head指向的第一个元素已排好序
			// p1如为空，已经指向单链表的末尾，排序完毕
			Node p2 = head; // p2指向排好序的元素，它是移动变化，依次
			while (p1 != p2) { // 比较数据是否全部比较结束？
				if (p1.data < p2.data) {
					int i1 = p1.data;
					while (p2 != p1.next) { // 这段循环程序完成“插入”
						int i2 = p2.data; // 需要插入
						p2.data = i1;
						i1 = i2;
						p2 = p2.next;
					}
					break;
				} else {
					p2 = p2.next; // 如不需要插入，则再同已排序数据的
					// 下一个元素进行比较，直至比较完毕
				}
			}
			p1 = p1.next;
		}
	}

	//请实现indexOf(int data)函数
	//如果链表中没有数据，返回-1
	//只允许实现indexOf函数，禁止修改其它函数代码
	public int indexOf(int data) { 	// 返回数据在单链表中第一次的出现位置的下标
									// 如果数据在单链表中不存在，则返回-1
		Node n = head;
		for(int i=0;i<size;i++) {
			if(data==n.data)
				return i;
			n = n.next;
		}
		return -1;
	}

	public static void main(String[] args) { // 下面是测试程序
		SingleLinkedList sll = new SingleLinkedList(); // 创建单链表对象
		sll.add(5); // 添加元素
		sll.add(9);
		sll.add(3);
		sll.printAll(); // 输出全部信息
		sll.remove(0); // 删除第一个元素(头元素)
		sll.printAll(); // 再次输出，检查结果

		// 下面的程序用来测试排序，查找。
		SingleLinkedList s2 = new SingleLinkedList();
		s2.add(49);
		s2.add(38);
		s2.add(65);
		s2.add(97);
		s2.add(76);
		s2.add(13);
		s2.add(27);
		s2.add(49);
		s2.insertionSort(); // 调用插入排序方法，在下一节定义
		s2.printAll();

		s2.clear();
		s2.add(49);
		s2.add(38);
		s2.add(65);
		s2.add(97);
		s2.add(76);
		s2.add(13);
		s2.add(27);
		s2.add(49);
		s2.bubbleSort(); // 调用冒泡排序方法，在下一节定义
		s2.printAll();

		System.out.println(s2.indexOf(5)); // 调用数据查找方法
		s2.remove(s2.indexOf(27));
		s2.remove(3);
		System.out.println(s2.indexOf(27));
		System.out.println(s2.indexOf(38));
		s2.printAll();
	}
}
