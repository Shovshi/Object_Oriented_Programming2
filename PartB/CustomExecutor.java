package PartB;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.*;

public class CustomExecutor<T> extends ThreadPoolExecutor
{
    int [] priorityArray = new int [10];
    Runtime runtime = Runtime.getRuntime();
    int minNumOfProcessors = runtime.availableProcessors()/2;
    int maxNumOfProcessors = runtime.availableProcessors()-1;
    PriorityBlockingQueue<Runnable> blockingQueue = new PriorityBlockingQueue<>(minNumOfProcessors,
            (t1 , t2 ) -> ((Task) t1).compareTo((Task)t2));
    ThreadPoolExecutor pool = new ThreadPoolExecutor(minNumOfProcessors,maxNumOfProcessors,300,TimeUnit.MILLISECONDS,blockingQueue);

    public CustomExecutor()
    {
        super(Runtime.getRuntime().availableProcessors() / 2, Runtime.getRuntime().availableProcessors() - 1,
                300, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<>(Runtime.getRuntime().availableProcessors()/2,
                    (t1 , t2 ) -> ((Task) t1).compareTo((Task)t2)));
    }


    public Future<T> submit (Task task)
    {
        priorityArray[task.taskType.getPriorityValue()]++;
        return pool.submit(task);
    }

    public Future<T> submit (Callable task, TaskType taskType)
    {
        Callable task1 = Task.createTask(task, taskType);
        return submit((Task)task1);
    }

    public Future<T> submit (Callable task)
    {
        Callable task1 = Task.createTask(task);
        return submit((Task)task1);
    }

    public int getCurrentMax()
    {
        int maxPriority = 0;
        for (int i = 9; i >= 0; i--)
        {
            if (priorityArray[i] > 0)
            {
                 return maxPriority = i;
            }
        }
        System.out.println("The Queue is empty");
        return 0;
    }
    @Override
    protected void afterExecute(Runnable r, Throwable t)
    {
        priorityArray[((Task)r).taskType.getPriorityValue()]--;
    }
}
