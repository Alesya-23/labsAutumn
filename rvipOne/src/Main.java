import java.io.Console;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {
    static int[][] array;

    static int n = 30000;

    static int maxJ;

    static HashMap<Integer, Integer> maxMap = new HashMap<Integer, Integer>(maxJ);

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("input N");
        generateMass(n, n);
        long start = System.currentTimeMillis();
        int max = oneThreadAlgorithm();
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("Прошло времени, мс: " + elapsed + " максимум " + max);
        long startForkJoin = System.currentTimeMillis();
        int maxForkJoin = ForkJoinPollAlgotitm();
        long endForkJoin = System.currentTimeMillis();
        long elapsedForkJoin = endForkJoin - startForkJoin;
        System.out.println("Прошло времени, мс: " + elapsedForkJoin + " максимум " + maxForkJoin);
    }

    public static void generateMass(int n, int m) {
        array = new int[n][m];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = (int) (Math.random() * +1000);
            }
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
        RecursiveTask recursiveTaskFindMax = new RecursiveTask(array, 1, n - 2);
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        forkJoinPool.invoke(recursiveTaskFindMax);
        return forkJoinPool.invoke(recursiveTaskFindMax);
    }
}