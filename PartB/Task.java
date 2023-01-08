package PartB;

import java.util.Comparator;
import java.util.concurrent.Callable;

public class Task<T> implements Callable<T> , Comparator<Task<T>>
{
    // unless set a different priority from the constructor , the default is set to  "OTHER"
    public TaskType taskType;
    Callable<T> function;

    @Override
    public int compare(Task<T> t1, Task<T> t2)
    {
        if(t1.taskType.getPriorityValue() > t2.taskType.getPriorityValue())
        {   // if the priority of the first Task is greater than the second , we return 1
            return 1;
        }
        else if (t1.taskType.getPriorityValue() == t2.taskType.getPriorityValue())
        {   // if the priority of the two Tasks are equal we return 0
            return 0;
        }
        else
        {   // if the priority of the second Task is greater than the first , we return -1
            return -1;
        }
    }

    private Task(Callable<T> function)
    {
        this.function = function;
        taskType.setPriority(3);
    }

    private Task(Callable<T> function , TaskType type)
    {
        this.function = function;
        this.taskType.setPriority(type.getPriorityValue());
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
}
