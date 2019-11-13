package coding;

import java.util.Scanner;

public class Shifts {

    public static long[] a;
    public static long[] b;
    public static long[] maxsumA;
    public static long[] maxsumB;
    public static int numTest = 0;
    public static int numShift = 0;
    public static long happiness = 0;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        numTest = scan.nextInt();
        numShift = 0;
        happiness = 0;

        for (int i = 1; i <= numTest; i++) {

            numShift = scan.nextInt();
            happiness = scan.nextLong();
            a = new long[numShift];
            b = new long[numShift];
            maxsumA = new long[numShift];
            maxsumB = new long[numShift];

            for (int j = 0; j < numShift; j++) {
                a[j] = scan.nextLong();
            }
            for (int j = 0; j < numShift; j++) {
                b[j] = scan.nextLong();
            }
            for (int j = numShift - 1; j >= 0; j--) {
                if (j == numShift - 1) {
                    maxsumA[j] = a[j];
                    maxsumB[j] = b[j];
                } else {
                    maxsumA[j] = maxsumA[j + 1] + a[j];
                    maxsumB[j] = maxsumB[j + 1] + b[j];
                }
            }

            long result = compute(0, happiness, happiness);

            System.out.println("Case #" + i + ": " + result);

        }
    }

    public static long compute(int index, long hapA, long hapB) {

        if (index == numShift) {
            if (hapA <= 0 && hapB <= 0) {
                return 1;
            }
            return 0;
        }

        if (maxsumA[index] < hapA || maxsumB[index] < hapB) {
            return 0;
        }

        if (hapA <= 0 && hapB <= 0) {
            return (long) Math.pow(3, numShift - index);
        }

        long retval = compute(index + 1, hapA - a[index], hapB - b[index]) + compute(index + 1, hapA - a[index], hapB)
                + compute(index + 1, hapA, hapB - b[index]);

        return retval;
    }
}
