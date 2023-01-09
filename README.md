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
This class implements Callable Interface and implements one main method : the call method which will also calculate the number of lines in the file and return this value.

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

![DiagramClass](https://user-images.githubusercontent.com/117938983/211315814-9700642f-1904-4245-b0fa-8277765316d8.png)

# Part B 
This part purpose is to create a new type that represents an asynchronous task with priority and a new ThreadPool type that supports tasks with priority.
we will use Three classes which we will detail later to accomplish this goal.

## TaskType Class
This is an enum class describes the type of task (computational/access to O/I/unknown) and its priority based on the value
The number of the task type.

## Task Class
This class represent an operation that can be run asynchronously and can return a value of any type (generic).
This class implements Callable and Comparable Interfaces , and also implements few methods :
1. Two private constructors: one takes only a Callable object "function" and sets a default value for the pirority, and the other one takes a Callable object           "function" and also a taskType.
2. The call method: by this method we perform the task and return the relevant value.
3. Two public constructors called "createTask": one takes only a Callable object "function", and the other one takes a Callable object "function" and also a taskType.
4. CompareTo function: takes a Task and compare it by priority value with the current Task.

## CustomExecutor Class
This class represents a new type of ThreadPool that supports a queue of tasks with priority.
This class extends ThreadPoolExecutor class, and also implements a few main methods :
1. Constructor which access the base class to build a "ThreadPool".
2. Three different methods to submit a task: 
* One which takes a Task and uses the base class to submit.
* The second one takes a Callable object Task and also a TaskType and uses the first submit method to submit.
* The third one takes a Callable object Task and uses the first submit method to submit.
3. getCurrentMax method which return the highest priority of a found task now in line.
4. gracefullyTerminated method which will terminate the pool.
     
## Diagram Class

![DiagramClass_PartB](https://user-images.githubusercontent.com/117938983/211328284-fdf3ca64-3ad8-4818-a32f-9d6117d66f30.png)

