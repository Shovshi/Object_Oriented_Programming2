package PartB;

import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Task<T> implements Callable<T>
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
    public T call()
    {
        try
        {
            return function.call();
        }
        catch (Exception e)
        {
            System.err.println("Error");
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

}
