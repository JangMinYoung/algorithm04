package AG04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Loop_Invariant {

	static int binary_search(int a[], int size, int search_number) {
		int lower = 0;
		int upper = size - 1;

		while (lower <= upper) {
		
			int index = lower + (upper - lower) / 2;
			if (search_number == a[index]) {
				return index;
			} else if (search_number < a[index]) {
				upper = index - 1;
			} else { // search_number > a[index]
				lower = index + 1;
			}
		}
	return -1;
	}

	public static void main(String[] arg) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("input.txt"));

			String s;

			int i = 0;

			s = br.readLine();
			StringTokenizer str = new StringTokenizer(s, " ");

			int array[] = new int[str.countTokens()];

			while (str.hasMoreTokens()) {
				array[i] = Integer.parseInt(str.nextToken());
				i++;
			}
			System.out.print("찾고자 하는 숫자를 입력하시오:");
			Scanner scan = new Scanner(System.in);
			int search_number = scan.nextInt();
			
			int index=binary_search(array,i,search_number);
			System.out.println(search_number+"이 저장되어 있는 배열의 인덱스 값 : "+index);

		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}
	}

}
