

public class Array {

	// ��ҵҪ��
	// �ж�����data�Ƿ���array�����У�����������У��������ص�һ�γ����������е�λ��
	// ������������У��򷵻�-1, ���ÿ���array���ñ���Ϊnull�����
	// ��ʵ�ָú�������ֹ�޸����������������������Ĵ���
	public static int indexOf(int data, int[] array) {
	for(int i=0;i<array.length;i++){
		if(array[i]==data)
			return i;
	}
		return -1;
	}

	// �ó������н��Ӧ�������1��-1
	public static void main(String[] args) {
		int[] array = { 1, 3, -1, 9, 7, 3 };
		System.out.println(indexOf(3, array));
		System.out.println(indexOf(5, array));

	}

}
