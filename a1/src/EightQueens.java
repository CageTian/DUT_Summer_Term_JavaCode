
//�������к�����˻ʺ�����Ľ������
public class EightQueens {
	public int[] a = { -1, -1, -1, -1, -1, -1, -1, -1 }; // Ԫ��Ĭ��ֵ��-1
	public int ways = 0; // �������������

	public boolean check(int row, int col) { // ��row�У�col�п��Է��ûʺ���
		// ��ʼ�У���һ�У������жϣ����ԷŻʺ������ѭ����ִ��
		for (int i = 0; i < row; i++) {
			if (a[i] == col) { // �ж�ͬ���Ƿ��лʺ��ͻ
				return false; // ����0��ʾ���ܷţ��г�ͻ
			}
			if (row - i == Math.abs(col - a[i])) { // �ж϶Խ����Ƿ��лʺ��ͻ��ע������˾���ֵ����
				return false;
			}
		}

		return true;
	}

	// �˺���ʵ���ڵ�n�з��ûʺ�, ��n�е�ĳһ������Żʺ���a[n]��ֵ��-1��Ϊ���е���ֵ
	public void place(int n) {
		if (n == 8) { // �ݹ麯���ĳ��ڣ���0��7�ж��Ѿ�������ϣ����Ҷ�����ȷ�ģ����Ե�n=8��ʱ��
			// ��������1

			ways++;

			// ���Խ�a[n]��ֵ������˴�����ʡ��

		} else {
			//���뿪��
			// �뽫else�����еĴ������ʵ��

			for(int i = 0; i < 8; i++){
				if(check(n,i)){
					a[n]=i;
					place(n+1);
				}
			}





			//�������
		}
	}

	public static void main(String[] args) {
		EightQueens eq = new EightQueens();
		eq.place(0);
		System.out.println(eq.ways);
	}

}
