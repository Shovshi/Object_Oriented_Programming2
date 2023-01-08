package PartB;

import java.util.Comparator;
import java.util.concurrent.*;

public class CustomExecutor<T>
{
    Runtime runtime = Runtime.getRuntime();
    int minNumOfProcessors = runtime.availableProcessors()/2;
    int maxNumOfProcessors = runtime.availableProcessors()-1;
    PriorityBlockingQueue<Runnable> blockingQueue = new PriorityBlockingQueue<>(minNumOfProcessors,
            (t1 , t2 ) -> ((Task) t1).compareTo((Task)t2));
    ThreadPoolExecutor pool = new ThreadPoolExecutor(minNumOfProcessors,maxNumOfProcessors,300,TimeUnit.MILLISECONDS,blockingQueue);

    public Future<T> submit (Task task)
    {
        return pool.submit(task);
    }

    public Future<T> submit (Callable<T> task, TaskType taskType)
    {
        Callable task1 = Task.createTask(task, taskType);
        return submit(task1);
    }

    public Future<T> submit (Callable<T> task)
    {
        Callable task1 = Task.createTask(task);
        return pool.submit(task1);
    }


   /* @Override
    public int compare(Task<T> o1, Task<T> o2)
    {
        if (o1.taskType.getPriorityValue() > o2.taskType.getPriorityValue())
        {
            return 1;
        }
        else if (o1.taskType.getPriorityValue() == o2.taskType.getPriorityValue())
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }*/
}
