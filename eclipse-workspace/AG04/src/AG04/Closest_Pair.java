package AG04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Closest_Pair {
   // 점들을 저장할 배열
   static ArrayList<Double[]> array = new ArrayList<Double[]>();
   static ArrayList<Double[]> minArray;

   public static void main(String[] arg) {
      try {

         // input.txt파일을 읽어 오기 위해 객체 생성
         BufferedReader br = new BufferedReader(new FileReader("closest_data.txt"));

         String s;

         minArray = new ArrayList<>();
         Double[] temp_1 = { 0.0, Double.MAX_VALUE };
         Double[] temp_2 = { Double.MAX_VALUE, 0.0 };
         minArray.add(temp_1);
         minArray.add(temp_2);

         while ((s = br.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(s, " ");
            array.add(new Double[] { Double.parseDouble(str.nextToken()), Double.parseDouble(str.nextToken()) });
         }
         quicksort(array, 0, array.size() - 1, "x");
         System.out.println("Output Data : " + closest_pair(array));
         System.out
         .println("(x1, y1) = " + "(" + minArray.get(0)[0] + ", " + minArray.get(0)[1] + ")");
   System.out
         .println("(x2, y2) = " + "(" + minArray.get(1)[0] + ", " + minArray.get(1)[1] + ")");

      } catch (IOException e) {
         System.err.println(e);
         System.exit(1);
      }
   }

   public static int partition(ArrayList<Double[]> array1, int start, int end, String n) {
      Double[] list = array1.get(end);

      int i = start - 1;

      for (int j = start; j < end; j++) {
         Double[] list2 = array1.get(j);
         if (n.equals("x")) {
            if (list2[0] <= list[0]) {
               i += 1;
               Double[] temp = array1.get(i);
               array1.add(i, array1.get(j));
               array1.remove(i + 1);
               array1.add(j, temp);
               array1.remove(j + 1);
            }
         } else {
            if (list2[1] <= list[1]) {
               i += 1;
               Double[] temp = array1.get(i);
               array1.add(i, array1.get(j));
               array1.remove(i + 1);
               array1.add(j, temp);
               array1.remove(j + 1);
            }
         }
      }
      i += 1;
      Double[] temp = array1.get(i);
      array1.add(i, array1.get(end));
      array1.remove(i + 1);
      array1.add(end, temp);
      array1.remove(end + 1);

      return i;
   }

   public static void quicksort(ArrayList<Double[]> array1, int p, int r, String n) {
      int q;
      if (p < r) {
         q = partition(array1, p, r, n);
         quicksort(array1, p, q - 1, n);
         quicksort(array1, q + 1, r, n);
      }

   }

   public static double closest_pair(ArrayList<Double[]> array1) {
      if (array1.size() <= 3) {
         return brute_force(array1);
      }
      int mid = array1.size() / 2;
      double min;
      ArrayList<Double[]> array_left = new ArrayList<Double[]>();
      ArrayList<Double[]> array_right = new ArrayList<Double[]>();
      array_left.addAll(array1.subList(0, mid));
      array_right.addAll(array1.subList(mid, array1.size()));
      double left_min = closest_pair(array_left);
      double right_min = closest_pair(array_right);
      min = Double.min(left_min, right_min);

      ArrayList<Double[]> window = new ArrayList<Double[]>();

      Double[] temp_list1 = array_right.get(0);
      Double[] temp_list2 = array_left.get(array_left.size() - 1);
      // 수직선 L
      Double L = (temp_list2[0] + temp_list1[0]) / 2;

      for (int i = 0; i < array.size(); i++) {
         temp_list1 = array.get(i);
         if (temp_list1[0] >= (L - min) && temp_list1[0] <= (L + min)) {
            window.add(temp_list1);
         }
      }
      // window를 y관련하여 정렬
      quicksort(window, 0, window.size() - 1, "y");
      // window 내부의 최단거리 구하기
      for (int i = 0; i < window.size() - 1; i++) {
         Double[] list1 = window.get(i);
         Double[] list2 = window.get(i + 1);
         if (list2[1] - list1[1] < min) {
            if (getDistance(list1, list2) < min) {
               min = getDistance(list1, list2);
               minArray.set(0, list1);
               minArray.set(1, list2);
            }
         }
      }

      return min;
   }

   public static double brute_force(ArrayList<Double[]> array) {
      double minDist = getDistance(minArray.get(0), minArray.get(1));
      if (array.size() == 2) {
         Double[] list1 = array.get(0);
         Double[] list2 = array.get(1);
         double temp = getDistance(list1, list2);
         if (temp < minDist) {
            minDist = temp;
            minArray.set(0, list1);
            minArray.set(1, list2);
         }
         return minDist;

      } else {
         Double[] list1 = array.get(0);
         Double[] list2 = array.get(1);
         Double[] list3 = array.get(2);
         double x = getDistance(list1, list2);
         double y = getDistance(list1, list3);
         double z = getDistance(list2, list3);
         if (x < y && x < z) {
        	 if (x < minDist) {
        	 minDist = x;
            minArray.set(0, list1);
            minArray.set(1, list2);
        	 }
            return minDist;
         } else if (y < z) {
        	 if (y < minDist) {
        	 minDist = y;
            minArray.set(0, list1);
            minArray.set(1, list3);
        	 }
            return minDist;
         } else {
        	 if (z < minDist) {
        	 minDist = z;
            minArray.set(0, list2);
            minArray.set(1, list3);
        	 }
            return minDist;
         }

      }

   }

   public static double getDistance(Double[] list1, Double[] list2) {
      return Math.sqrt(Math.pow(list1[0] - list2[0], 2) + Math.pow(list1[1] - list2[1], 2));
   }
}