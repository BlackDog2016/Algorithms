package hihocoder;

import java.util.Scanner;

/**
 * http://hihocoder.com/problemset/problem/1037
 * @author BlackDog2016
 *
 */

public class Main1037 {
	
	public static void main(String[] args) {
		Scanner sin = new Scanner(System.in);
		
		int numlines = Integer.valueOf(sin.nextInt());
		int n = numlines;
		sin.nextLine();
		
		int price[][] = new int[numlines][numlines];
		int getPrices[][] = new int[numlines][numlines];
		
		while (numlines-- != 0) {
			String tmp[] = sin.nextLine().split(" ");
			for (int i = 0; i < n - numlines; i++) {
				price[n-numlines-1][i] = Integer.valueOf(tmp[i]);
			}
		}
		
		getPrices[0][0] = price[0][0];
		for (int i = 1; i < price.length; i++) {
			for (int j = 0; j < price[0].length; j++) {
				if (j == 0) {
					getPrices[i][j] = getPrices[i-1][j] + price[i][j];
				} else if (i == j) {
					getPrices[i][j] = getPrices[i-1][j-1] + price[i][j];
				} else {
					getPrices[i][j] = Math.max(getPrices[i-1][j-1], getPrices[i-1][j]) + price[i][j];
				}
			}
		}
		
		int res = getPrices[n-1][0];
		for (int i = 1; i < n; i++) {
			if (getPrices[n-1][i] > res) {
				res = getPrices[n-1][i];
			}
		}
		System.out.println(res);
	}
}
