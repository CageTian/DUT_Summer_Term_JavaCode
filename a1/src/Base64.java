
public class Base64 {

    static private final int  BASELENGTH         = 128;
    static private final int  LOOKUPLENGTH       = 64;

    static final private byte [] base64Alphabet        = new byte[BASELENGTH];
    static final private char [] lookUpBase64Alphabet  = new char[LOOKUPLENGTH];

    static {

        for (int i = 0; i < BASELENGTH; ++i) {
            base64Alphabet[i] = -1;
        }
        for (int i = 'Z'; i >= 'A'; i--) {
            base64Alphabet[i] = (byte) (i-'A');
        }
        for (int i = 'z'; i>= 'a'; i--) {
            base64Alphabet[i] = (byte) ( i-'a' + 26);
        }

        for (int i = '9'; i >= '0'; i--) {
            base64Alphabet[i] = (byte) (i-'0' + 52);
        }

        base64Alphabet['+']  = 62;
        base64Alphabet['/']  = 63;

        for (int i = 0; i<=25; i++)
            lookUpBase64Alphabet[i] = (char)('A'+i);

        for (int i = 26,  j = 0; i<=51; i++, j++)
            lookUpBase64Alphabet[i] = (char)('a'+ j);

        for (int i = 52,  j = 0; i<=61; i++, j++)
            lookUpBase64Alphabet[i] = (char)('0' + j);
        lookUpBase64Alphabet[62] = (char)'+';
        lookUpBase64Alphabet[63] = (char)'/';

    }
    static byte b1=0,b2=0,b3=0,c1=0,c2=0,c3=0,c4=0;
	public static String encode(byte[] binaryData) {
		String s="";
		for(int i=0;i<binaryData.length;i++){
			b1=binaryData[i++];
			b2=binaryData[i++];
			b3=binaryData[i];
			int tmp1=b1;
			int tmp2;
            char[] c=new char[4];
			c1=(byte)((tmp1>>2)&0x3F);
			tmp1=b1;
			tmp2=b2;
			c2=(byte)((((tmp1&0x03)<<4)|(tmp2>>4)&0x0F)&0x3F);
			tmp1=b2;
			tmp2=b3;
			c3=(byte)((((tmp1&0x0f)<<2)|(tmp2>>6))&0x3F);
			tmp1=b3;
			c4=(byte)((tmp1&0x3f)&0x3F);
            c[0]=lookUpBase64Alphabet[c1];
            c[1]=lookUpBase64Alphabet[c2];
            c[2]=lookUpBase64Alphabet[c3];
            c[3]=lookUpBase64Alphabet[c4];
            s+=String.valueOf(c);
			// System.out.println(c1+" "+c2+" "+c3+" "+c4+" ");
			//System.out.println(s);

		}
		return s;
	}

	public static byte[] decode(String s) {

        char[] dc=s.toCharArray();
        byte[] nc=new byte[dc.length];
        byte[] rowb=new byte[4];
        byte[] deb=new byte[3];
        byte[] ans=new byte[dc.length/4*3];
        byte tmp1,tmp2;
		byte[] a= { 1, 2, 3, -7, -9, 110 };
        int j=0;
        for(int i=0;i<dc.length;i++){
            nc[i]=base64Alphabet[dc[i]];
            //System.out.println(nc[i]);
        }
        for(int i=0;i<nc.length;i++){
            rowb[0]=nc[i++];
            rowb[1]=nc[i++];
            rowb[2]=nc[i++];
            rowb[3]=nc[i];
            tmp1=rowb[0];
            tmp2=rowb[1];
            deb[0]=(byte)((tmp1<<2)|(tmp2)>>4);
            tmp1=rowb[1];
            tmp2=rowb[2];
            deb[1]=(byte)((tmp1<<4)|(tmp2)>>2);
            tmp1=rowb[2];
            tmp2=rowb[3];
            deb[2]=(byte)((tmp1<<6)|tmp2);
            ans[j++]=deb[0];
            ans[j++]=deb[1];
            ans[j++]=deb[2];
            // System.out.println(deb[0]);
            // System.out.println(deb[1]);
            // System.out.println(deb[2]);
        }
		return ans;
	}

	public static void main(String[] args) {
		byte[] a = { 1, 2, 3, -7, -9, 110 };
		String s = encode(a);
		System.out.println(s);
		byte[] b = decode(s);
		for(int i=0;i<b.length;i++) {
			System.out.print(b[i] + " ");
		}
		System.out.println();

	}

}
