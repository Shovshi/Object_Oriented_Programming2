package PartB;

import java.util.concurrent.Callable;

public class Task <T> implements Callable
{
    private int typePriority;
    private TaskType taskType;
    TaskFunction<T> function;
    private T value;
    private T returnVal;
    public Task()
    {

    }

    //constructor
    public Task(TaskFunction<T> function)
    {
        this.function = function;
    }

    @Override
    public T call() throws Exception
    {
        System.out.println("this is ID");
        returnVal = function.genericFunction(value);
        return returnVal;

    }
}

  //  public T doSomething(MyFunction<T> function, T value) {
  //      return function.apply(value);
  //  }