package PartB;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

    public class TaskAdapt<T> extends FutureTask<T>
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
    }

