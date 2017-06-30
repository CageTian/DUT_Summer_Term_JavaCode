
public class InsertionSort {

	public static void sort(int[] a) {
		if (a == null || a.length < 2) {
			return;
		}

		for (int i = 1; i < a.length; i++) {
			// 从0开始到i-1元素都是已经排好序的序列，有序序列
			// 将第i个元素和其中的进行比较，如果大于其中的元素，则往后比较，遇到小于某个元素，
			// 则停下来，插入到该位置
			int p = i;
			for (int j = 0; j < i; j++) {
				if (a[i] < a[j]) {
					p = j;
					break;
				}
			}

			if (p == i) {
				continue;
			}

			int key = a[i];

			for (int j = i - 1; j >= p; j--) {

				a[j + 1] = a[j];
			}

			a[p] = key;

		}

	}

	public static void main(String[] args) {
		int[] a = { 1, 9, 8, -3, 5 };
		sort(a);
		for (int i = 0; i < a.length; i++) {
			if (i == a.length - 1) {
				System.out.println(a[i]);
			} else {
				System.out.print(a[i] + ", ");
			}
		}

	}

}
