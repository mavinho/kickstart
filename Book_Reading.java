package kickstart;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int numtest = scan.nextInt();
		int page = 0;
		int torn = 0;
		int read = 0;
		int[] dataTorn;
		int temp = 0;
		long count = 0;
		
		for(int i = 1 ; i <= numtest ; i++) {
			
			count = 0;
			
			page = scan.nextInt();
			torn = scan.nextInt();
			read = scan.nextInt();
			dataTorn = new int[page + 1];
			
			for(int j = 1 ; j <= torn ; j++) {
				temp = scan.nextInt();
				for(int k = 1 ; k <= Math.sqrt(temp) ; k++) {
					
					if(temp % k == 0) {
						if(k == Math.sqrt(temp)) {
							dataTorn[k] += 1;
						} else {
							dataTorn[k] += 1;
							dataTorn[temp/k] += 1;
						}
					}
				}
			} 
			
			for(int k = 1 ; k <= read ; k++) {
				temp = scan.nextInt();
				count += page/temp;
				count -= dataTorn[temp];
				
			}
			
			System.out.println("Case #" + i + ": " + count);
		}
	}
}
