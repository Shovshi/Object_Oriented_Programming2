import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        System.out.println("Shoval was here");
        String names[]= Ex2_1.createTextFiles(10,10,100000);
        int lines= Ex2_1.getNumOfLines(names);
        System.out.println("total: "+lines);
        for (int i = 0; i < 10; i++) {
            //System.out.println(names[i]);
        }
        Ex2_1 ex2 = new Ex2_1();
        int x = ex2.getNumOfLinesThreads(names);
        System.out.println("Thread: " +x);

        int y = ex2.getNumOfLinesThreadPool(names);
        System.out.println("Thread Pool: "+y);
    }
}