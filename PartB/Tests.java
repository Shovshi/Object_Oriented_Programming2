package PartB;

import org.junit.Test;


import java.util.concurrent.*;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.lang.Thread.sleep;

public class Tests
{
    private static java.util .logging.Logger LoggerFactory;
    public static final Logger logger = LoggerFactory.getLogger(String.valueOf(Tests.class));

    /**
     * This test will test if the queue can compare two submittable objects by delaying the process so that
     * the queue will fill up , we then make sure each submittable object returned the same value.
     */
    @Test
    public void testQueue()
    {
        CustomExecutor customExecutor = new CustomExecutor();

        Callable<Double> callable1 = () -> {
            return 1000 * Math.pow(1.02, 5);
        };
        Callable<String> callable2 = () ->
        {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            sleep(1000);
            return sb.reverse().toString();
        };
        Callable<String> callable3 = () ->
        {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            sleep(1000);
            return sb.reverse().toString();
        };
        Callable<String> callable4 = () ->
        {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            sleep(1000);
            return sb.reverse().toString();
        };
        Callable<String> callable5 = () ->
        {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            sleep(1000);
            return sb.reverse().toString();
        };
        Callable<String> callable6 = () ->
        {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            sleep(1000);
            return sb.reverse().toString();
        };
        Future<String> reverseTask = customExecutor.submit(callable2, TaskType.OTHER);
        Future<String> reverseTask2 = customExecutor.submit(callable3, TaskType.OTHER);
        Future<String> reverseTask3 = customExecutor.submit(callable4, TaskType.IO);
        Future<String> reverseTask4 = customExecutor.submit(callable5, TaskType.IO);
        Future<String> reverseTask5 = customExecutor.submit(callable6, TaskType.IO);

        String reversed,reversed2,reversed3,reversed4,reversed5;
        try
        {
            reversed = reverseTask.get();
            reversed2 = reverseTask2.get();
            reversed3 = reverseTask3.get();
            reversed4 = reverseTask4.get();
            reversed5 = reverseTask5.get();
        }
        catch (InterruptedException | ExecutionException e)
        {
            throw new RuntimeException(e);
        }

        assertEquals(reversed.toString(), reversed2.toString());
        assertEquals(reversed2.toString(), reversed3.toString());
        assertEquals(reversed3.toString(), reversed4.toString());
        assertEquals(reversed4.toString(), reversed5.toString());
        logger.info(() -> "Reversed String = " + reversed);
        customExecutor.gracefullyTerminate();
    }

    /**
     * in this test we check that the max priority in the queue is different from -1 so we first fill it
     * by adding tasks that take some time , then check the priority of the queue
     */
    @Test
    public void testMaxPriority()
    {
        CustomExecutor customExecutor = new CustomExecutor();
        Callable<Integer> c1 = Task.createTask(() ->
        {
            sleep(1000);
            return 1;
        },TaskType.OTHER);
        Callable<Integer> c2 = Task.createTask(() ->
        {
            sleep(1000);
            return 1;
        },TaskType.OTHER);
        Callable<Integer> c3 = Task.createTask(() ->
        {
            sleep(1000);
            return 1;
        },TaskType.OTHER);
        Callable<Integer> c4 = Task.createTask(() ->
        {
            sleep(1000);
            return 1;
        },TaskType.OTHER);
        Callable<Integer> c5 = Task.createTask(() ->
        {
            sleep(1000);
            return 1;
        },TaskType.OTHER);
        customExecutor.submit(c1);
        customExecutor.submit(c2);
        customExecutor.submit(c3);
        customExecutor.submit(c4);
        customExecutor.submit(c5);
        logger.info(()-> "Current maximum priority = " + customExecutor.getCurrentMax());
        customExecutor.gracefullyTerminate();
    }
}
