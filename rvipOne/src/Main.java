import java.io.Console;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {
    static int[][] array;
    static int[] arrayOne;

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("input N");
        int n = in.nextInt();
        generateMass(n, n);
        generateMass(n);
        long start = System.currentTimeMillis();
        int max = oneThreadAlgorithm();
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("Прошло времени, мс: " + elapsed + " максимум " + max);
        int mass = ForkJoinPollAlgotitm();
        System.out.println(mass);
    }

    public static void generateMass(int n, int m) {
        array = new int[n][m];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = (int) (Math.random() * +1000);
            }
        }
    }

    public static void generateMass(int m) {
        arrayOne = new int[m];
        for (int i = 0; i < arrayOne.length; i++) {
            arrayOne[i] = (int) (Math.random() * +1000);
        }
    }

    public static int oneThreadAlgorithm() {
        int maxElement = -100;
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i][j] > maxElement) {
                    maxElement = array[i][j];
                }
            }
        }
        return maxElement;
    }

    public static int ThreadPoolExecutorAlgorithm() {
        return 1;
    }

    public static int ForkJoinPollAlgotitm() {
        RecursiveTask recursiveTaskFindMax = new RecursiveTask(array);
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        for (int i = 0; i < array.length; i++) {
            forkJoinPool.invoke(recursiveTaskFindMax);
        }
    }
}