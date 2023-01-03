import java.io.*;
import java.util.Random;
import java.util.concurrent.*;
import java.util.random.*;
public class Ex2_1 {

    // SINCE OUR COMPUTERS USE DIFFERENT OPERATING SYSTEMS THE FIRST PATH IS FOR SHOVAL
    // THE SECOND PATH IS FOR ELI

    //static String path="C:\\\\Users\\\\User\\\\Desktop\\\\Eli\\"; //Check where we need to push the files
    static String path = "//home//eli//Desktop//github//files//";
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
                File file = new File(path+"file_" +(i+1)+".txt");

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

    public static int getNumOfLines(String[] fileNames)
    {
        int numOfLines = 0;
        int cnt = 0;
        for (int i = 0; i < fileNames.length ; i++)
        {
            cnt = 0;
            try
            {
                FileReader fr = new FileReader(path+fileNames[i]+".txt");
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

    public int getNumOfLinesThreads(String[] fileNames)
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

   public int getNumOfLinesThreadPool(String[] fileNames) throws ExecutionException, InterruptedException
   {
       int totalNumOfLines = 0;
       ExecutorService pool= Executors.newFixedThreadPool(fileNames.length);

       for (int i = 0; i < fileNames.length ; i++)
       {
           FileCallable fc = new FileCallable(fileNames[i]);
           Future<Integer> future =  pool.submit(fc);
           totalNumOfLines += future.get();
       }
       
       pool.shutdown();
       return totalNumOfLines;
   }

    public static String getPath()
    {
        return path;
    }
}
