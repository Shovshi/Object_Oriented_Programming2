package PartB;

import java.util.Comparator;
import java.util.concurrent.*;

/**
 * This class is a custom "ThreadPoolExecutor" class that executes instances of type "Task" asynchronously
 * the queue in the custom executor will sort the Task instances according to their priority
 * @param <T>
 */
public class CustomExecutor<T> extends ThreadPoolExecutor
{
    /**
     * We use the following array as a counting array , if the user wants to find out the maximum priority Task
     * that is still in the queue waiting to be executed we go over the array and return the task with the highest
     * priority
     */
    int [] priorityArray = new int [10];

    /**
     * Class constructor we send the needed variables and a new PriorityBlockingQueue that can
     * compare two objects that are casted to our adapter class, to the ThreadPoolExecutor
     */
    public CustomExecutor()
    {
        super(Runtime.getRuntime().availableProcessors() / 2, Runtime.getRuntime().availableProcessors() - 1,
                300, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<>(Runtime.getRuntime().availableProcessors()/2,
                        Comparator.comparing(t -> ((TaskAdapt<T>) t))));
    }

    /**
     * This function will submit the callable task to our thread pool executor
     * @param task - The instance of task we want to submit to our executor
     * @return - Object of type "FutureTask"
     */
    public Future<T> submit (Task<T> task)
    {
        task.hasBeenCalled = true;
        priorityArray[task.taskType.getPriorityValue()]++;
        return super.submit(task);
    }

    /**
     * This function will create a task instance using our asynchronous operation and task priority
     * then will submit it through our previous submit
     * @param call - The asynchronous operation we submit
     * @param taskType - The priority of this asynchronous operation
     * @return - Object of type "FutureTask"
     */
    public Future<T> submit (Callable call, TaskType taskType)
    {
        Callable task1 = Task.createTask(call, taskType);
        return submit((Task)task1);
    }

    /**
     * This function will create a task instance using our asynchronous operation then will submit it through our
     * previous submit
     * @param call - the asynchronous operation we submit
     * @return - Object of type "FutureTask"
     */
    public Future<T> submit (Callable call)
    {
        Callable task1 = Task.createTask(call);
        return submit((Task)task1);
    }

    /**
     * This function will loop through our counting array and return the highest priority task found
     * will return -1 if queue is empty
     * @return
     */
    public int getCurrentMax()
    {
        int maxPriority = 0;
        for (int i = 0; i <= 9; i++)
        {
            if (priorityArray[i] > 0)
            {
                 return maxPriority = i;
            }
        }
        return -1;
    }

    /**
     * This function simply calls shutdown since the specifics needed for "gracefullyTerminated"
     * shutdown already has
     */
    public void gracefullyTerminated()
    {
        super.shutdown();
    }

    /**
     * This function is called automatically once the Task has left the queue and before it is executed
     * we go to its priority in the array and decrease it by one , we first use our adapter to cast the runnable
     * object as an object we can get its priority from
     * @param t the thread that will run task {@code r}
     * @param r the task that will be executed
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r)
    {
        TaskAdapt<T> taskAdapt = (TaskAdapt<T>) (r);
        priorityArray[taskAdapt.getPriority()]--;
    }

    /**
     * We wrap the callable task as a TaskAdapter so we can later use its functionality to get
     * its priority
     * @param callable - the callable task being wrapped
     * @return
     * @param <T>
     */
    @Override
    protected <T> RunnableFuture newTaskFor( Callable<T> callable)
    {
        return new TaskAdapt<T>(callable);
    }

    public String toString()
    {
        return super.toString();
    }
}
