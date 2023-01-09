package PartB;

import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Task<T> implements Callable<T> , Comparable<Task<T>>
{
    public TaskType taskType;
    Callable<T> function;

    private Task(Callable<T> function)
    {
        this.function = function;
        taskType = taskType.OTHER;
    }

    private Task(Callable<T> function , TaskType type)
    {
        this.function = function;
        this.taskType = type;
    }
    @Override
    public T call()
    {
        try
        {
            return function.call();
        }
        catch (Exception e)
        {
            System.err.println("ERROR");
            return null;
        }

    }
    public static Task createTask(Callable function)
    {
        return new Task(function);
    }

    public static Task createTask(Callable function, TaskType type)
    {
        return new Task(function , type);
    }
    @Override
    public int compareTo(Task<T> task)
    {
        if (this.taskType.getPriorityValue() > task.taskType.getPriorityValue())
        {
            return 1;
        }
        else if (this.taskType.getPriorityValue() == task.taskType.getPriorityValue())
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }
}
