package hihocoder;

import java.util.Scanner;

/**
 * http://hihocoder.com/problemset/problem/1032
 * @author BlackDog2016
 * @date 2016/4/18
 */

public class Main1032 {
	
	public static void main(String[] args) {
		Scanner sin = new Scanner(System.in);
		
		int lines = sin.nextInt();
		//sin.nextLine();
		
		while (lines-- > 0) {
			String input = sin.next();
			input = preProcess(input);
			System.out.println(input);
			lps_m(input);
		}
		
	}
	
	// longest palindromic substring
	/**
	 * 比较直观的方法，从中心点往两边比较，复杂度O(n^2)，最后提交超时....
	 */
	public static void lps(String input) {
		int len = 0, max = 0;
		
		for (int i = 0; i < input.length(); i++) {
			int len1 = maxFromCenter(input, i, i);
			int len2 = maxFromCenter(input, i, i + 1);
			len = Math.max(len1, len2);
			max = Math.max(max, len);
		}
		System.out.println(max);
	}
	
	public static int maxFromCenter(String s, int left, int right) {
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}
		return right - left - 1;
	}

	// mancher's algorithm
	// 参考：http://articles.leetcode.com/longest-palindromic-substring-part-ii
	// 注意字符串预处理时头尾的添加，不然判断到字符串结尾时容易数组越界
	public static void lps_m(String input) {
		if (input.length() == 0) System.out.println(0);
		int[] P = new int[input.length()];
		P[0] = 0;
		int C = 0;
		int R = 0;
		int i_aside = 0;
		int max = P[0];
		
		for (int i = 1; i < input.length() - 1; i++) {
			i_aside = 2*C - i;
			
			P[i] = (R > i) ? Math.min(R - i, P[i_aside]) : 0;
			
			while (input.charAt(i-1-P[i]) == input.charAt(i+1+P[i])) {
				P[i]++;
			}
			
			if (i + P[i] > R) {
				C = i;
				R = i + P[i];
			}
			max = Math.max(max, P[i]);
		}
		System.out.println(max);
	}
	
	public static String preProcess(String input) {
		StringBuilder sb = new StringBuilder();
		if (input.length() == 0) return "^$";
		sb.append("^#");
		for (int i = 0; i < input.length(); i++) {
			sb.append(input.charAt(i));
			sb.append('#');
		}
		sb.append('$');
		return sb.toString();
	}
}
