package PartB;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

    public class TaskAdapt<T> extends FutureTask<T>  implements Comparable<TaskAdapt> , Runnable
    {

        private Callable<T> callable;

        public TaskAdapt(Callable<T> callable)
        {
            super(callable);
            this.callable = callable;
        }

        public Callable<T> getCallable()
        {
            return this.callable;
        }

        @Override
        public int compareTo(TaskAdapt o)
        {

            if ( ((Task<T>)(this.callable)).taskType.getPriorityValue() >
                    ((Task<T>)(o.getCallable())).taskType.getPriorityValue())
            {
                return 1;
            }
            if ( ((Task<T>)(this.callable)).taskType.getPriorityValue() ==
                    ((Task<T>)(o.getCallable())).taskType.getPriorityValue())
            {
                return 0;
            }

            return -1;
        }
    }

