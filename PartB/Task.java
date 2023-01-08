package PartB;

import java.util.concurrent.Callable;

public class Task<T> implements Callable
{
    private int typePriority;
    private TaskType taskType;
    Callable<T> function;
    private T value;
    private T returnVal;
    private Task(Callable<T> function)
    {
        this.function = function;
    }

    private Task(Callable<T> function , TaskType type)
    {
        this.function = function;
        this.taskType.setPriority(type.getPriorityValue());
    }

    @Override
    public T call() throws Exception
    {
        return function.call();
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
