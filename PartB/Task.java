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

    //constructor
    public T Test(Callable<T> function, T value) throws Exception {
        this.function = function;
        return function.call();
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
}
