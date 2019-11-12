/*
https://codingcompetitions.withgoogle.com/kickstart/round/0000000000050e02/000000000018fe36
*/

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int numTest = scan.nextInt();
        long total = 0;
        long longest = 0;
        long temp = 0;
        long max = 0;
        long minimal = 0;
        long special = 0;
        int[] sumBinary;
        String str = "";
        String[] binary;

        for (int i = 1; i <= numTest; i++) {

            total = scan.nextInt();
            max = scan.nextLong();
            binary = new String[(int) total];

            for (int j = 0; j < total; j++) {
                temp = scan.nextLong();
                str = Long.toBinaryString(temp);
                binary[j] = str;
                if (str.length() > longest) {
                    longest = str.length();
                }
            }

            sumBinary = new int[(int) longest];

            for (int j = 0; j < total; j++) {
                str = binary[j];
                int length = str.length();
                for (int k = length - 1; k >= 0; k--) {
                    if (str.charAt(k) == '1')
                        sumBinary[length - k - 1]++;
                }
            }

            minimal = 0;

            for (int j = 0; j < longest; j++) {
                minimal += ((long) Math.min(sumBinary[j], total - sumBinary[j]) << j);
            }

            if (minimal > max) {
                System.out.println("Case #" + i + ": -1");
                continue;
            }

            if (longest == 1 && sumBinary[0] == 0) {
                System.out.println("Case #" + i + ": " + (max / total));
                continue;
            }

            str = "";
            special = (long) ((max - minimal) / (1L << longest));
            if (special != 0) {
                str = str + Long.toBinaryString(special);
            }

            max -= (special * (1L << longest));
            str += findMax(sumBinary, max, total);
            System.out.println("Case #" + i + ": " + Long.parseLong(str, 2));
        }
    }

    public static String findMax(int[] sumBinary, long max, long total) {

        long[] data = new long[sumBinary.length];
        String str = "";

        for (int j = 0; j < sumBinary.length; j++) {
            if (j == 0) {
                data[j] = Math.min(sumBinary[j], total - sumBinary[j]) * (1L << j);
            } else {
                data[j] = data[j - 1] + Math.min(sumBinary[j], total - sumBinary[j]) * (1L << j);
            }
        }

        for (int j = sumBinary.length - 1; j >= 0; j--) {
            long temp = (total - sumBinary[j]) * (1L << j);
            long min = Math.min(sumBinary[j], total - sumBinary[j]) * (1L << j);
            if (j == 0) {
                if (temp > max) {
                    str += '0';
                } else {
                    str += '1';
                }
            } else if (temp + data[j - 1] > max) {
                str += '0';
                max = max - min;
            } else {
                str += '1';
                max = max - temp;
            }
        }
        return str;
    }
}
