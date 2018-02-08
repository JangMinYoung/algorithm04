package AG05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class Karatsuba_Algorithm {

	public static void main(String[] arg) {
		try {

			// input.txt파일을 읽어 오기 위해 객체 생성
			BufferedReader br = new BufferedReader(new FileReader("data05_karatsuba.txt"));

			String s;

			String[] a = new String[2];
			int i = 0;
			while ((s = br.readLine()) != null) {
				a[i] = s;
				i += 1;
			}
			BigInteger A = new BigInteger(a[0]);
			BigInteger B = new BigInteger(a[1]);
			System.out.println("Output Data : " + karatsuba(A, B));

		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}
	}

	public static BigInteger karatsuba(BigInteger A, BigInteger B) {
		int m;
		if (A.toString().length() <= 2 && B.toString().length() <= 2) {
			return A.multiply(B);
		}
		if (A.toString().length() >= B.toString().length()) {
			m = A.toString().length() / 2;
		} else {
			m = B.toString().length() / 2;
		}
		String pow1 = "" + (int) Math.pow(10, m);
		BigInteger temp = new BigInteger(pow1);

		BigInteger x1 = A.divide(temp);
		BigInteger x2 = A.remainder(temp);
		BigInteger y1 = B.divide(temp);
		BigInteger y2 = B.remainder(temp);

		BigInteger z0 = karatsuba(x2, y2);
		BigInteger z2 = karatsuba(x1, y1);
		BigInteger z1 = karatsuba(x2.add(x1), y2.add(y1)).subtract(z2).subtract(z0);
		String pow2 = "" + (int) Math.pow(10, m * 2);
		BigInteger temp2 = new BigInteger(pow2);
		BigInteger result1 = new BigInteger(z1.multiply(temp).toString());
		BigInteger result2 = new BigInteger(z2.multiply(temp2).toString());

		return z0.add(result1).add(result2);

	}
}