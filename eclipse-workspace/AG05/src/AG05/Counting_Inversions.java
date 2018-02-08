package AG05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Counting_Inversions {
	public static void main(String[] arg) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("data05_inversion_01.txt"));

			String s;

			int i = 0;

			s = br.readLine();
			StringTokenizer str = new StringTokenizer(s, " ");

			ArrayList<Integer> l = new ArrayList<>();
			

			while (str.hasMoreTokens()) {
				l.add(Integer.parseInt(str.nextToken()));
			}
			List L = new List(l);
			L=SortAndCount(L);
			 System.out.println("Output Data : " + L.inversionCount);

		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}
	}

	public static List SortAndCount(List l) {
		if(l.list.size() == 1)
			return l;
		
		int mid = l.list.size() / 2;
		List temp_list1 = new List(new ArrayList<>());
		temp_list1.list.addAll(l.list.subList(0, mid));
		temp_list1 = SortAndCount(temp_list1);
		
		List temp_list2 = new List(new ArrayList<>());
		temp_list2.list.addAll(l.list.subList(mid, l.list.size()));		
		
		temp_list2 = SortAndCount(temp_list2);
		
		l = mergeAndCount(temp_list1, temp_list2);
		
		l.inversionCount += temp_list1.inversionCount+temp_list2.inversionCount;
		
		return l;
	}

	public static List mergeAndCount(List a, List b) {
		int inversion_count = 0;
		int indexA=0,indexB=0;
		int a_count=a.list.size(),b_count=b.list.size();
		List l = new List(new ArrayList<>());
		
		while(a_count != 0 && b_count != 0) {
			if(a.list.get(indexA) > b.list.get(indexB)) {
				inversion_count += a_count;
				
				int temp_value=b.list.get(indexB);
	            l.list.add(temp_value);
	            indexB+=1;
	            b_count-=1;

			} else {
				int temp_value=a.list.get(indexA);
	            l.list.add(temp_value);
	            indexA+=1;
	            a_count-=1;
			
			}
		}
		
		while(a.list.size()-1>=indexA) {
			int temp_value=a.list.get(indexA);
            l.list.add(temp_value);
            indexA+=1;
		}
		
		while(b.list.size()-1>=indexB) {
			int temp_value=b.list.get(indexB);
            l.list.add(temp_value);
            indexB+=1;
		}
		
		l.inversionCount = inversion_count;
		
		return l;
	}
	public static class List{
		public ArrayList<Integer> list;
		public int inversionCount;
		
		public List(ArrayList<Integer> list) {
			this.list = list;
			this.inversionCount = 0;
		}
	}
}
