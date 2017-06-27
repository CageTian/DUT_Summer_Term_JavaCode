
public class BinarySortTree {

	// ��������������
	public static BstNode create(int[] a) {
		if (a == null || a.length == 0) {
			return null;
		} else {
			BstNode root = new BstNode();
			root.data = a[0];

			for (int i = 1; i < a.length; i++) {
				add(root, a[i]);
			}
			return root;
		}
	}

	public static void add(BstNode p, int d) {
		if (d > p.data) {
			if (p.right == null) {
				p.right = new BstNode();
				p.right.data = d;
			} else {
				add(p.right, d);
			}
		} else {
			if (p.left == null) {
				p.left = new BstNode();
				p.left.data = d;
			} else {
				add(p.left, d);
			}
		}
	}

	// �������
	public static void preOrderTraverse(BstNode root) {
		if (root != null) {
			System.out.print(root.data + " ");
			preOrderTraverse(root.left);
			preOrderTraverse(root.right);
		}
	}

	// �������
	public static void inOrderTraverse(BstNode root) {
		if (root != null) {
			inOrderTraverse(root.left);
			System.out.print(root.data + " ");
			inOrderTraverse(root.right);
		}
	}

	// �������
	public static void postOrderTraverse(BstNode root) {
		if (root != null) {
			postOrderTraverse(root.left);
			postOrderTraverse(root.right);
			System.out.print(root.data + " ");
		}
	}

	// ��ʵ������ĺ������ж϶����������Ƿ��������data, ���������������false�����򣬷���true
	// rootָ������������ĸ��ڵ�
	public static boolean contains(BstNode root, int data) {
		if(root!=null){
			if(root.data<data)
				return contains(root.right,data);
			else if(root.data>data)
				return contains(root.left,data);
			else
				return true;
		}
		return false;



	}

	public static void main(String[] args) {
		int[] a = { 1, 9, 8, 7, -3, -5 };

		BstNode root = create(a);

		inOrderTraverse(root);

		System.out.println();

		System.out.println(contains(root, 9));
		System.out.println(contains(root, -1));
	}

}
