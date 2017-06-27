public class Fibo {
	public static void main(String[] args) {
		Fibo f = new Fibo();
		System.out.println(f.fibo1(9)); // �����ַ�������Ч�ʸ��ߣ�
		System.out.println(f.fibo2(9));
	}

	public int fibo1(int n) { // ʹ�÷������������ݹ���ʵ��
		if(n==1||n==2)
			return 1;
		else
			return fibo1(n-1)+fibo1(n-2);
	}

	public int fibo2(int n) { // ʹ��ѭ����ʵ��
		if(n==1||n==2)
			return 1;
		else
		{
			int last1=1,last2=1;
			int result=0;
			for(int i=2;i<n;i++){
				result=last1+last2;
				last2=last1;
				last1=result;
			}
			return result;
		}
	}
}
