import java.util.concurrent.Callable;

public class MyThreadPollExecutor {
}

class MyCallable implements Callable<Long> {

    @Override
    public Long call() throws Exception {
        try{

        }
        catch (Exception ex){
            ex.printStackTrace(System.out);
        }
        return  Thread.currentThread().getId();
    }
}