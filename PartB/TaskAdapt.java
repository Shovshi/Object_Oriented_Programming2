package PartB;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * This class is used so we can adapt our runnable objects and use the task object inside the class
 * to gain access to the priority that is used for various methods
 * @param <T>
 */
public class TaskAdapt<T> extends FutureTask<T> implements Comparable<TaskAdapt<T>>
{

    private final Task<T> task;

    /**
     * Class constructor - Build object of type FutureTask then set the task object
     * @param callable - we cast the callable into a Task object so we can later use it
     */
    public TaskAdapt(Callable<T> callable)
    {
        super(callable);
        this.task = (Task<T>) callable;
    }

    /**
     * Second class constructor - Build object of type FutureTask then set the task object
     * @param task - We set this class's Task object to the one given as a parameter
     */
    public TaskAdapt(Task task)
    {
        super(task);
        this.task = task;
    }

    /**
     * @return - We return the task's callable
     */
    public Callable<T> getCallable()
    {
        return this.task.function;
    }

    /**
     * @return - We return the task's priority
     */
    public int getPriority()
    {
        return task.taskType.getPriorityValue();
    }

    /**
     * This method overrides the method from Comparable , we compare two objects of type "TaskAdapt" based
     * on their task's priority
     * @param t the object of type TaskAdapt that we compare
     * @return - We return 0 if the two TaskAdapt have same task priority , -1 if the TaskAdapt we call from is greater
     * 1 if the TaskAdapt we call from is less
     */
    @Override
    public int compareTo(TaskAdapt t)
    {

        if (task.taskType.getPriorityValue() > t.task.taskType.getPriorityValue()) {
            return -1;
        }
        if (task.taskType.getPriorityValue() == t.task.taskType.getPriorityValue()) {
            return 0;
        }

        return 1;
    }
}

