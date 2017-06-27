
public class FractionalDigit {

	public static void main(String[] args) {
		int d = 13;
		int q = 17;
		int a = 0;

        int n=100;
        for(int i=0;i<n;i++){
            a=d*10/q;
            d=(d*10)%q;
        }
		System.out.println(a);

	}


}
