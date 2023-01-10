package PartB;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

    public class TaskAdapt<T> extends FutureTask<T>  implements Comparable<TaskAdapt<T>>
    {

        private final Task<T> task;

        public TaskAdapt(Callable<T> callable)
        {
            super(callable);
            this.task = (Task<T>)callable;
        }

        public TaskAdapt(Task task)
        {
            super(task);
            this.task = task;
        }

        public Callable<T> getCallable()
        {
            return this.task.function;
        }

        public int getPriority()
        {
            return ((task).taskType.getPriorityValue());
        }
        @Override
        public int compareTo(TaskAdapt o)
        {

            if (task.taskType.getPriorityValue() >
                    task.taskType.getPriorityValue())
            {
                return 1;
            }
            if (task.taskType.getPriorityValue() ==
                    task.taskType.getPriorityValue())
            {
                return 0;
            }

            return -1;
        }
    }

