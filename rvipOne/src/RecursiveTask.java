import java.util.Arrays;
import java.util.OptionalInt;

public class RecursiveTask extends java.util.concurrent.RecursiveTask<Integer> {

    private int[][] array;
    int begin;
    int end;

    @Override
    protected Integer compute() {
        int len = end - begin;
        int max = -1000;
        int iMax = 0;
        if (len <= 40) {
            for(int i = begin; i < end; i++ ){
                for ( int j = 0; j < i; j++){
                    if(max < array[i][j]){
                        max = array[i][j];
                        iMax = i;
                    }
                }
            }
        }
        else {
            RecursiveTask firstNewArray = new RecursiveTask(array, begin, begin + len / 2);
            RecursiveTask secondNewArray = new RecursiveTask(array, +begin + len / 2 + 1, end);
            firstNewArray.fork();
            secondNewArray.fork();
            max = Math.max(firstNewArray.join(), secondNewArray.join());
        }
        return max;
    }

    public RecursiveTask(int[][] array, int begin, int end) {
        this.array = array;
        this.begin = begin;
        this.end = end;
    }
}
//    protected Integer compute() {
//        if (array.length <= 2) {
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            OptionalInt max = Arrays.stream(array).max();
//            return max.getAsInt();
//        }
//        RecursiveTask firstNewArray = new RecursiveTask(Arrays.copyOfRange(array, 0, array.length/2));
//        RecursiveTask secondNewArray = new RecursiveTask(Arrays.copyOfRange(array, array.length/2,array.length));
//        firstNewArray.fork();
//        secondNewArray.fork();
//        return xMath.max(firstNewArray.join(), secondNewArray.join());
//    }
//
//    public RecursiveTask(int[] array) {
//        this.array = array;
//    }