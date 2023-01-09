package PartB;

import org.junit.Test;


import java.util.concurrent.*;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class Tests {
    private static java.util.logging.Logger LoggerFactory;
    public static final Logger logger = LoggerFactory.getLogger(String.valueOf(Tests.class));

    @Test
    public void partialTest()
    {
        CustomExecutor customExecutor = new CustomExecutor();
        Callable task = Task.createTask(() -> {
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            sleep(1000);
            return sum;
        }, TaskType.COMPUTATIONAL);
        Future<Integer> sumTask = customExecutor.submit(task);
        final int sum;
        try {

            sum = sumTask.get(100000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
        logger.info(() -> "Sum of 1 through 10 = " + sum);
        Callable<Double> callable1 = () -> {
            return 1000 * Math.pow(1.02, 5);
        };
        Callable<String> callable2 = () -> {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            sleep(1000);
            return sb.reverse().toString();
        };
        Callable<String> callable3 = () -> {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            sleep(1000);
            return sb.reverse().toString();
        };
        Callable<String> callable4 = () -> {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            sleep(1000);
            return sb.reverse().toString();
        };
        Callable<String> callable5 = () -> {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            sleep(1000);
            return sb.reverse().toString();
        };
        logger.info(() -> "Current maximum priority = " + customExecutor.getCurrentMax());
        Callable<String> callable6 = () -> {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            sleep(1000);
            return sb.reverse().toString();
        };
        Future<Double> priceTask = customExecutor.submit(() -> {
            sleep(10000);
            return 1000 * Math.pow(1.02, 5);
        }, TaskType.COMPUTATIONAL);
        logger.info(() -> "Current maximum priority = " + customExecutor.getCurrentMax());
        Future<String> reverseTask = customExecutor.submit(callable2, TaskType.OTHER);
        Future<String> reverseTask2 = customExecutor.submit(callable3, TaskType.OTHER);
        Future<String> reverseTask3 = customExecutor.submit(callable4, TaskType.IO);
        Future<String> reverseTask4 = customExecutor.submit(callable5, TaskType.IO);
        Future<String> reverseTask5 = customExecutor.submit(callable6, TaskType.IO);
        logger.info(() -> "Current maximum priority = " + customExecutor.getCurrentMax());

        final Double totalPrice;
        final String reversed,reversed2,reversed3;
        try {
            totalPrice = priceTask.get();
            reversed = reverseTask.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        logger.info(() -> "Reversed String = " + reversed);
        logger.info(() -> String.valueOf("Total Price = " + totalPrice));
        logger.info(() -> "Current maximum priority = " + customExecutor.getCurrentMax());
    }
}
