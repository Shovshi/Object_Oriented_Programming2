package PartB;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class CustomExecutor
{
    PriorityBlockingQueue<Task> blockingQueue = new PriorityBlockingQueue<>();
}
