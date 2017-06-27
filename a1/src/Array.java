

public class Array {

	// 作业要求
	// 判断数据data是否在array数组中，如果在数组中，则函数返回第一次出现在数组中的位置
	// 如果不在数组中，则返回-1, 不用考虑array引用变量为null的情况
	// 请实现该函数，禁止修改其它函数代码或者其它类的代码
	public static int indexOf(int data, int[] array) {
	for(int i=0;i<array.length;i++){
		if(array[i]==data)
			return i;
	}
		return -1;
	}

	// 该程序运行结果应该是输出1和-1
	public static void main(String[] args) {
		int[] array = { 1, 3, -1, 9, 7, 3 };
		System.out.println(indexOf(3, array));
		System.out.println(indexOf(5, array));

	}

}
