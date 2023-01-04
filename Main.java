import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        // we create n amount of tiles bound to maximum 100000 lines
        String names[]= Ex2_1.createTextFiles(200,10,500000);


        // measure time takes regularly
        double startTime = System.currentTimeMillis();

        int linesRegular = Ex2_1.getNumOfLines(names);
        System.out.println("Regular Total: " + linesRegular);

        CalculateAndDisplayTime(startTime);

        // measure time it takes using a thread for each file
        startTime = System.currentTimeMillis();

        int linesFromThreads = Ex2_1.getNumOfLinesThreads(names);
        System.out.println("Thread Total: " + linesFromThreads);

        CalculateAndDisplayTime(startTime);


        // measure time it takes using a thread for each file using thread pool
        startTime = System.currentTimeMillis();

        int linesFromThreadPool = Ex2_1.getNumOfLinesThreadPool(names);
        System.out.println("Thread Pool Total: " + linesFromThreadPool);

        CalculateAndDisplayTime(startTime);

    }

    public static void CalculateAndDisplayTime(double startMilliSeconds)
    {
        double endTime = System.currentTimeMillis();
        double functionTime = (endTime - startMilliSeconds)/1000;
        System.out.println("Time it took: " + functionTime + " seconds");
    }

}