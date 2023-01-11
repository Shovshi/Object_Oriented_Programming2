package PartA;

import java.io.*;
import java.util.Random;
import java.util.concurrent.*;

/**
 * This class includes all the methods required in our task.
 */

public class Ex2_1 {


    /**
     * This method creates n text files considering seed and bound to calculate the range of a possible number of lines in a certain file ,
     * and returns an array including the names of the files.
     * @param n
     * @param seed
     * @param bound
     * @return String array
     */
    public static String[] createTextFiles(int n,int seed,int bound)
    {
        Random rand = new Random(seed);
        String [] names= new String[n];
        boolean didSetLines = true;

        for (int i = 0; i < n ; i++)
        {
            int lines = rand.nextInt(bound);
            try
            {
                File file = new File("file_" +(i+1)+".txt");

                boolean didCreate = file.createNewFile();
                if (didCreate)
                {
                    names[i] = "file_"+(i+1);
                    didSetLines = setLines(file.getAbsolutePath(),lines);
                    if(!didSetLines)
                    {
                        System.out.println("did not succeed to set lines");
                        //return -1
                    }
                }
                else
                {
                    System.out.println("file "+ (i+1)+ " did not created");
                    return null;
                }

            }
            catch(IOException e)
            {
                System.err.println("Error");
            }
        }

        return names;

    }

    /**
     * This method writes to a given file a certain number of lines and returns true if it worked.
     * @param path
     * @param lines
     * @return boolean value
     */
    public static boolean setLines (String path, int lines)
    {
        try
        {
            FileWriter fw = new FileWriter(path);
            PrintWriter outs = new PrintWriter(fw);
            while(lines > 0)
            {
                outs.println("i love food");
                lines--;
            }
            outs.close();
            fw.close();
        }
        catch (IOException e)
        {
            System.err.println("Error");
        }
        return true;
    }

    /**
     * This method gets an array of file names and calculate total number of lines in all the files
     * by reading each line of each file and add it to the total number.
     * @param fileNames
     * @return integer number
     */
    public static int getNumOfLines(String[] fileNames)
    {
        int numOfLines = 0;
        int cnt = 0;
        for (int i = 0; i < fileNames.length ; i++)
        {
            cnt = 0;
            try
            {
                FileReader fr = new FileReader(fileNames[i]+".txt");
                BufferedReader br = new BufferedReader(fr);
                String line = "";
                while(line!=null)
                {
                    line= br.readLine();
                    cnt++;
                }
                cnt--; //the last line br read is null
                numOfLines += cnt;
                br.close();
                fr.close();
            }
            catch (IOException e)
            {
                System.err.println("Error");
            }
        }
        return numOfLines;
    }

    /**
     * This method calculate number of total lines in all files using Threads.
     * At first, we start each tread which related to each file respectively, and then we put them in a treads array
     * to maintain the thread idea. Then we get the number of lines in each file and add it to the total number
     * and eventually returns this number.
     * @param fileNames
     * @return integer number
     */
    public static int getNumOfLinesThreads(String[] fileNames)
    {
        FileThread [] threadsArr = new FileThread[fileNames.length];
        int totalNumOfLines = 0;
        for (int i = 0; i <fileNames.length ; i++)
        {
            FileThread ft = new FileThread(fileNames[i]);
            ft.start();
            threadsArr[i] = ft;
        }
        for (int i = 0; i < fileNames.length; i++)
        {
            try
            {
                threadsArr[i].join();
                totalNumOfLines += threadsArr[i].getLines();
            }
            catch (InterruptedException e)
            {
                System.err.println("ERROR");
                return -1;
            }

        }

        return totalNumOfLines;
    }


    /**
     * In this method we use ThreadPool and an array of future objects, so we can approach their "get" method and calculate the total
     * number of lines in all files, and eventually we shut down the pool.
     * @param fileNames
     * @return integer number
     * @throws ExecutionException
     * @throws InterruptedException
     */
   public static int getNumOfLinesThreadPool(String[] fileNames) throws ExecutionException, InterruptedException
   {
       int totalNumOfLines = 0;
       ExecutorService pool= Executors.newFixedThreadPool(fileNames.length);
       Future<Integer> [] futureArr =new Future[fileNames.length];

       for (int i = 0; i < fileNames.length ; i++)
       {
           FileCallable fc = new FileCallable(fileNames[i]);
           Future<Integer> future = pool.submit(fc);
           futureArr[i] = future;
       }

       for (int i = 0; i < fileNames.length ; i++)
       {
           try
           {
               totalNumOfLines += futureArr[i].get();
           }
           catch (InterruptedException e)
           {
               System.err.println("Error");
           }
       }
       
       pool.shutdown();
       return totalNumOfLines;
   }


}
