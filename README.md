# Object Oriented Programming Task 2
## OverView
In this task we have two parts which we will detail each of them later.
Generally, this task concentrating in Threads and ThreadPool and implementing their methods.

# Part A
This part focuses on three different methods to calculate number of lines in files we created.
One method, is the normal ,easy method to calculate this number. We use a while loop going thru all the lines in a specific file and count the number of lines .
The second method uses Threads so each thread serves as a task that works on each file separately and manipulate between them so we can calculate the number of lines in each file faster.
The third method uses ThreadPool that reuses previously created threads to execute current tasks , so our ThreadPool will hold the threads represents each file we need to calculate his number of lines .

## FileThread Class
This class extends Thread class and implements Two methods:
1. The run method which in our project will calculate the number of lines in the current thread working on a specipic file. 
2. The getLines method which will return the number of lines in the current file.

## FileCallable Class
This class implements Callable class and implements one main method : the call method which will also calculate the number of lines in the file and return this value.

## Comparison Of Times 
In this section we will compare the times of each method and draw conclusions about them.
After running our program , we got the following order in terms of speeds:
1. The fastest methos was the one implements the ThreadPool.
2. The second method was the one implements the Threads.
3. The slowest method was the regular one.

## Conclusions
Following these results we can conclude that when we use the ThreadPool our tasks are performed faster.
One of the reasons for this could be that in a single threaded application, only one thread can execute, meaning only one task can run at any point in time while Thread Pool is a collection of threads which can directly work on tasks assigned by us and also Threads are reused to work on tasks waiting in the queue.

## Diagram Class



# Part B 
