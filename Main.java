import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        // we create n amount of tiles bound to maximum 100000 lines
        String names[]= Ex2_1.createTextFiles(40,10,100000);

        // measure time takes regularly
        int linesRegular = Ex2_1.getNumOfLines(names);
        System.out.println("Regular Total: " + linesRegular);

        // measure time it takes using a thread for each file
        int linesFromThreads = Ex2_1.getNumOfLinesThreads(names);
        System.out.println("Thread Total: " + linesFromThreads);

        // measure time it takes using a thread for each file using thread pool
        int linesFromThreadPool = Ex2_1.getNumOfLinesThreadPool(names);
        System.out.println("Thread Pool Total: " + linesFromThreadPool);
    }
}