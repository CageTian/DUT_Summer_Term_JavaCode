
//程序运行后输出八皇后问题的解的数量
public class EightQueens {
	public int[] a = { -1, -1, -1, -1, -1, -1, -1, -1 }; // 元素默认值是-1
	public int ways = 0; // 解决方案计数器

	public boolean check(int row, int col) { // 在row行，col列可以放置皇后吗？
		// 初始行，第一行，无须判断，可以放皇后，下面的循环不执行
		for (int i = 0; i < row; i++) {
			if (a[i] == col) { // 判断同列是否有皇后冲突
				return false; // 返回0表示不能放，有冲突
			}
			if (row - i == Math.abs(col - a[i])) { // 判断对角线是否有皇后冲突，注意调用了绝对值函数
				return false;
			}
		}

		return true;
	}

	// 此函数实现在第n行放置皇后, 第n行的某一列如果放皇后，则a[n]的值由-1改为该列的列值
	public void place(int n) {
		if (n == 8) { // 递归函数的出口，从0到7行都已经放置完毕，并且都是正确的，所以当n=8的时候，
			// 方案数加1

			ways++;

			// 可以将a[n]的值输出，此处代码省略

		} else {
			//代码开发
			// 请将else语句块中的代码给以实现

			for(int i = 0; i < 8; i++){
				if(check(n,i)){
					a[n]=i;
					place(n+1);
				}
			}





			//代码结束
		}
	}

	public static void main(String[] args) {
		EightQueens eq = new EightQueens();
		eq.place(0);
		System.out.println(eq.ways);
	}

}
