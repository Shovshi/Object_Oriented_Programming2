package PartB;

import java.util.Comparator;
import java.util.concurrent.Callable;

/**
 * This class is custom "callable" class that implements callable and can be submitted to our customExecutor
 * each task has a priority used for scheduling, can return an object of type generic and throw an error
 * @param <T>
 */
public class Task<T> implements Callable<T> , Comparable<Task>
{
    public TaskType taskType;
    Callable<T> function;
    /**
     * First constructor - we take in an operation that can run asynchronously and set it as this class's callable
     * Object and set the default priority to "OTHER"
     * @param function - the callable we can run asynchronously
     */
    private Task(Callable<T> function)
    {
        this.function = function;
        taskType = taskType.OTHER;
    }
    /**
     * Second Constructor - We take in an operation that can run asynchronously as previous constructor
     * and a type that is used to set the tasks priority
     * @param function - the callable we can run asynchronously
     * @param type - the type of TaskType we set our class TaskType as
     */
    private Task(Callable<T> function , TaskType type)
    {
        this.function = function;
        this.taskType = type;
    }
    /**
     * Since we want our class to be callable we add a call function , we then inside a try and catch call our
     * callable object inside the class and call it, the reason we don't override is since that will create two
     * seperate threads and we only want one , that is created inside
     * @return
     */
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
    /**
     * Factory method for creating instance of the class
     * @param function - the callable asynchronous "task"
     * @return Task - we return an instance of the class
     */
    public static Task createTask(Callable function)
    {
        return new Task(function);
    }
    /**
     * Factory method for creating instance of the class
     * @param function - the callable asynchronous "task"
     * @param type - the type of TaskType we set our class TaskType as
     * @return Task - we return an instance of the class
     */
    public static Task createTask(Callable function, TaskType type)
    {
        return new Task(function , type);
    }
    /**
     * This method overrides the method from Comparable , we compare two objects of type "Task" based
     * on their task's priority
     * @param t the object of type Task we compare
     * @return - We return 0 if the two Tasks have same task priority , 1 if the Task we call from is greater
     * -1 if the Task we call from is less
     */
    @Override
    public int compareTo(Task t)
    {
        if (this.taskType.getPriorityValue() > t.taskType.getPriorityValue())
        {
            return 1;
        }
        else if (this.taskType.getPriorityValue() == t.taskType.getPriorityValue())
        {
            return 0;
        }
        return -1;
    }
}
