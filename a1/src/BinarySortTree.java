
public class BinarySortTree {

	// 构建二叉排序树
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

	// 先序遍历
	public static void preOrderTraverse(BstNode root) {
		if (root != null) {
			System.out.print(root.data + " ");
			preOrderTraverse(root.left);
			preOrderTraverse(root.right);
		}
	}

	// 中序遍历
	public static void inOrderTraverse(BstNode root) {
		if (root != null) {
			inOrderTraverse(root.left);
			System.out.print(root.data + " ");
			inOrderTraverse(root.right);
		}
	}

	// 后序遍历
	public static void postOrderTraverse(BstNode root) {
		if (root != null) {
			postOrderTraverse(root.left);
			postOrderTraverse(root.right);
			System.out.print(root.data + " ");
		}
	}

	// 请实现下面的函数，判断二叉排序树是否包含数据data, 如果不包含，返回false，否则，返回true
	// root指向二叉排序树的根节点
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
