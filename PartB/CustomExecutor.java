package PartB;

import java.util.Comparator;
import java.util.concurrent.*;

public class CustomExecutor<T> extends ThreadPoolExecutor
{
    int [] priorityArray = new int [10];
    public CustomExecutor()
    {
        super(Runtime.getRuntime().availableProcessors() / 2, Runtime.getRuntime().availableProcessors() - 1,
                300, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<>(Runtime.getRuntime().availableProcessors()/2,
                        Comparator.comparing(t -> ((TaskAdapt<T>) t))));
    }
    public Future<T> submit (Task<T> task)
    {
        priorityArray[task.taskType.getPriorityValue()]++;
        return super.submit((Callable<T>) task);
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
        return -1;
    }

    public void gracefullyTerminated()
    {
        super.shutdown();
    }
    @Override
    protected void beforeExecute(Thread t, Runnable r)
    {
        TaskAdapt<T> taskAdapt = (TaskAdapt<T>) (r);
        priorityArray[taskAdapt.getPriority()]--;
    }
    @Override
    protected <T> RunnableFuture newTaskFor( Callable<T> callable)
    {
        return new TaskAdapt<T>(callable);
    }
}
